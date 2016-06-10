
(ns text-nodes.views
  (:require [reagent.core    :as rx]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [text-nodes.db :refer [conn]]
            [re-frame.core   :refer [register-sub subscribe dispatch register-handler]]
            [datascript.core :as db]
            [re-com.core   :as re-com :refer [h-box v-box box gap line scroller border h-split v-split title flex-child-style p]]
            [cljs.pprint     :refer [pprint]]
            [keybind.core :as key]
            [cljs.reader]
            [com.rpl.specter  :refer [ALL] :as s]
            [clojure.string  :as str])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform defprotocolpath]]
           [reagent.ratom :refer [reaction]]))



(def schema {
             :node/text             {:db/unique :db.unique/identity}
             :node/out-edge         {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
             :edge/to               {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}})





(register-sub
 :db-atoms
 (fn [_ [_ conn]]
   (q conn '[:find ?e ?attr ?val
             :where
             [?e ?attr ?val]])))



(register-sub
 :db-entities
 (fn [_ [_ conn]]
  (q conn '[:find ?e
             :where
             [?e]])))



(register-sub
 :e
 (fn [_ [_ conn eid]]
   (pull conn '[*] eid)))



(defn connview [conn]
  (let [datoms (subscribe [:db-atoms conn])]
    (fn []
      [:div
       (for
         [datom @datoms]
         [:div (pr-str datom)])])))





(defn pr-entity [conn eid]
  (let [e (subscribe [:e conn eid])]
    (fn []
      [:div (pr-str @e)])))




(defn entity-view [conn]
  (let [es (subscribe [:db-entities conn])]
    (fn []
      [:div
       (for [[e] @es]
         [pr-entity conn e])])))






(defn get-children [treesarray]

  (select [ALL (s/collect-one :node) :children ALL :node]
          treesarray))

(get-children (nodify test-struct))

(pprint (select [ALL]  (nodify test-struct)))






(register-handler
 :init
 (fn [db [_ conn]]
     (let [e  (subscribe [:db-entities conn])
           node1  {:db/id -1
                   :node/text "Hello Graphs"}
           layout {:db/id -2
                   :layout/x 50
                   :layout/y 50
                   :layout/height 500
                   :layout/width 500
                   :layout/nodeid (:db/id node1)}]
       (if (empty? @e)
         (do (db/transact! conn
                           [node1 layout])))
       (assoc db :testmap (nodify test-struct)))))


(register-sub
 :testmap
 (fn [db]
   (reaction (:testmap @db))))



(register-handler
 :change-text
 (fn [db [_ text]]
   (assoc db :text text)))

(register-sub
 :text
 (fn [db]
   (reaction (:text @db))))



(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))


(count-tabs "\t\t")


(defn tvalue [e]
  (-> e
      .-target
      .-value))


(defn parsed [text]
  (->> (str/split text #"\n")
       (map (juxt count-tabs str/trim))))



(register-sub
 :parsed-text
 (fn [db]
   (reaction (nodify (parsed (:text @db))))))



(register-handler
 :clear-text
 (fn [db [_ e end]]
   (let [text (:text db)]
     (js/console.log (pr-str e))
     (assoc db :text (str (subs text 0 e) "\t"  (subs text end))))))



(register-handler
 :fix-tree
 (fn [db]
   (let [tree @(subscribe [:parsed-text])]
     (assoc db :tree tree))))



(register-sub
 :tree
 (fn [db]
   (reaction (:tree @db))))





(defn tree-text []
  (let [text (subscribe [:text])
        p    (subscribe [:parsed-text])]
    (fn []
      [:div
       [:textarea {:style {:width 500 :height 500}
                   :on-change #(do
                                 (dispatch [:change-text (tvalue %)])
                                 (dispatch [:fix-tree]))
                   :on-key-down #(case (.-which %)
                                   9 (do
                                       (dispatch [:clear-text
                                                  (-> % .-target .-selectionStart)
                                                  (-> % .-target .-selectionEnd)])
                                       (.preventDefault %))
                                   :else)
                   :value @text}]])))









(defn tree [t]
  (let [visible? (rx/atom (:children-visible t))]
      (fn []
         [v-box
          :min-width  "40px"
          :size "auto"
          :gap "5px"
          :children
          [
           [v-box
            :align-self :center
            :gap "5px"
            :children [
                       [box
                        :align-self :center
                        :min-width  "40"
                        :style {:background-color "white"
                                :padding "5px"
                                :margin"20px 10px 0px 10px"
                                :border "2px solid blue"}

                        :child (:node t)]
                       [box
                        :align-self :center
                        :child
                        [:div
                         (if (< 0 (count (:children t)))
                           [:button {:on-click #(reset! visible? (not @visible?))}
                            (if @visible?
                              "-"
                              "+")])]]]]
           [box
            :child
            [h-box
             :justify :center
             :style {:display (if (not @visible?)
                               :none)}
             :children [
                        (for [child (:children t)]
                          ^{:key child}
                           [tree child])]]]]])))
                           
                           
                           

(defn tree-display []
  (let [tree-array (subscribe [:tree])]
    (fn []
      [h-box
       :style {:background-color "lightGrey"}
       :width "100%"
       :justify :center
       :gap   "2em"
       :children [
                  #_[:button {:on-click #(dispatch [:fix-tree])} "X"]
                  #_[:div (pr-str @tree-array)]
                  (for [t @tree-array]
                    ^{:key t} [tree t])]])))




(defn demo []
  [v-box
   :size "auto"
   :gap "10px"
   :children [
              [re-com/h-split
               :panel-1 [tree-text]
               :panel-2 [tree-display]]]])


(defn stuff [conn]
  (let [tm (subscribe [:testmap])]
   (fn []
    [:div
     [:button {:on-click #(dispatch [:init conn])} "start"]
;   [canvas conn]
     [demo]
     [entity-view conn]
     [:h1 (pr-str @tm)]])))



(defn main-panel []
    (fn []
      [stuff conn]))


