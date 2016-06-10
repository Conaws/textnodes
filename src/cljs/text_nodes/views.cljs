;@+leo-ver=5-thin
;@+node:conor.20160605011953.1: * @file views.cljs
;@@language clojure
;@+others
;@+node:conor.20160610073335.1: ** @language clojure
;@@language clojure
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
;@+node:conor.20160608034748.2: ** (def schema {;:canvas/layouts

(def schema {
             :node/text             {:db/unique :db.unique/identity}
             :node/out-edge         {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
             :edge/to               {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}})


;@+node:conor.20160608034749.1: **
;@+node:conor.20160608034749.2: ** (defn nodify [nseq]  (loop
(defn nodify [nseq]
  (loop [result []
         s nseq]
    (let[sa (first s)
         r (rest s)
         [children siblings] (split-with #(< (first sa) (first %)) r)
         answer     {:node (second sa)
                     :children-visible true}
         answer
         (if (< 0 (count children))
           (assoc answer :children (nodify children))
           (assoc answer :children children))]

      (if (< 0 (count siblings))
        (recur (conj result answer) siblings)
        (conj result answer)))))
;@+node:conor.20160608034749.3: **
;@+node:conor.20160608034749.5: ** (register-sub :db-atoms (fn [_ [_


(register-sub
 :db-atoms
 (fn [_ [_ conn]]
   (q conn '[:find ?e ?attr ?val
             :where
             [?e ?attr ?val]])))
;@+node:conor.20160608034749.6: ** (register-sub :db-entities (fn [_ [_


(register-sub
 :db-entities
 (fn [_ [_ conn]]
  (q conn '[:find ?e
             :where
             [?e]])))
;@+node:conor.20160608034749.7: ** (register-sub :e (fn [_ [_
(register-sub
 :e
 (fn [_ [_ conn eid]]
   (pull conn '[*] eid)))
;@+node:conor.20160608034749.8: ** (defn connview [conn]  (let
(defn connview [conn]
  (let [datoms (subscribe [:db-atoms conn])]
    (fn []
      [:div
       (for
         [datom @datoms]
         [:div (pr-str datom)])])))
;@+node:conor.20160608034749.9: ** (defn pr-entity [conn eid]


(defn pr-entity [conn eid]
  (let [e (subscribe [:e conn eid])]
    (fn []
      [:div (pr-str @e)])))
;@+node:conor.20160608034749.10: ** (defn entity-view [conn]  (let

(defn entity-view [conn]
  (let [es (subscribe [:db-entities conn])]
    (fn []
      [:div
       (for [[e] @es]
         [pr-entity conn e])])))
;@+node:conor.20160608034749.11: **
;@+node:conor.20160608034749.12: **
;@+node:conor.20160608034749.13: ** (def test-struct  [[0


(def test-struct  [[0  "a"]
                   [1  "b"]
                   [2  "c"]
                   [1  "d"]
                   [0  "1a"]
                   [1  "1b"]
                   [1  "1c"]])
;@+node:conor.20160608034749.14: ** (select ALL test-struct)
(select ALL test-struct)

;this gives me the first layer
;@+node:conor.20160608034749.15: ** (defn get-children [treesarray]
(defn get-children [treesarray]

  (select [ALL (s/collect-one :node) :children ALL :node]
          treesarray))

(get-children (nodify test-struct))
;@+node:conor.20160608034749.16: ** (pprint (select [ALL]  (nodify
(pprint (select [ALL]  (nodify test-struct)))

;;[["a" "b"] ["a" "d"] ["1a" "1b"] ["1a" "1c"]]
;;[["a" "b"] ["a" "d"] ["1a" "1b"] ["1a" "1c"]]
;@+node:conor.20160608034750.1: ** (defn seperate-graph-map [graph-map-array]  (map


(defn seperate-graph-map [graph-map-array]
  (map (partial tree-seq :node :children) graph-map-array))
;@+node:conor.20160608034750.2: ** (register-handler :init (fn [db [_

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
;@+node:conor.20160608034750.3: ** (register-sub :testmap (fn [db]


(register-sub
 :testmap
 (fn [db]
   (reaction (:testmap @db))))
;@+node:conor.20160608034750.4: ** (register-handler :change-text (fn [db [_
(register-handler
 :change-text
 (fn [db [_ text]]
   (assoc db :text text)))

(register-sub
 :text
 (fn [db]
   (reaction (:text @db))))
;@+node:conor.20160608034750.5: ** (defn count-tabs  [string]

(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))

(count-tabs "\t\t")
;@+node:conor.20160608034750.6: ** (defn tvalue [e]  (->

(defn tvalue [e]
  (-> e
      .-target
      .-value))


(defn parsed [text]
  (->> (str/split text #"\n")
       (map (juxt count-tabs str/trim))))
;@+node:conor.20160608034750.7: **   (register-sub :parsed-text (fn


(register-sub
 :parsed-text
 (fn [db]
   (reaction (nodify (parsed (:text @db))))))
;@+node:conor.20160608034750.8: ** (register-handler :clear-text (fn [db [_
(register-handler
 :clear-text
 (fn [db [_ e end]]
   (let [text (:text db)]
     (js/console.log (pr-str e))
     (assoc db :text (str (subs text 0 e) "\t"  (subs text end))))))
;@+node:conor.20160608034750.9: ** (register-handler :fix-tree (fn [db]
(register-handler
 :fix-tree
 (fn [db]
   (let [tree @(subscribe [:parsed-text])]
     (assoc db :tree tree))))
;@+node:conor.20160608034750.10: ** (register-sub :tree (fn [db]
(register-sub
 :tree
 (fn [db]
   (reaction (:tree @db))))


;@+node:conor.20160608034750.11: ** tree-text
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






                                 ;; [box
                                 ;;  :min-width "50px"
                                 ;;  :align-self :center
                                 ;;  :style {:background-color "white"
                                 ;;          :margin "10px"
                                 ;;          :border "2px solid blue"}
                                 ;;  :child
;@+node:conor.20160608034750.12: ** (defn tree [t]  (let
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
;@+node:conor.20160608034751.1: **
                          ^{:key child}

                           [tree child])]]]]])))
;@+node:conor.20160608034751.2: ** (defn tree-display []  (let

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
;@+node:conor.20160608034751.3: **
;@+node:conor.20160608034751.4: ** (defn demo []  [v-box
(defn demo []
  [v-box
   :size "auto"
   :gap "10px"
   :children [
              [re-com/h-split
               :panel-1 [tree-text]
               :panel-2 [tree-display]]]])

;@+node:conor.20160608034751.6: ** (defn demo2 []  [h-box

(defn demo2 []
  [h-box
   :height "100px"
   :justify :center
   :children [
              [box
               :child "Box1"
               :style {:background-color "blue"}]

              [box
               :child "b"
               :style {:background-color "green"}]

              [box
               :child "b"
               :align-self :center]]])

;@+node:conor.20160608034751.7: ** (defn stuff [conn]  (let

(defn stuff [conn]
  (let [tm (subscribe [:testmap])]
   (fn []
    [:div
     [:button {:on-click #(dispatch [:init conn])} "start"]
;   [canvas conn]
     [demo]
     [entity-view conn]
     [:h1 (pr-str @tm)]])))

;@+node:conor.20160608034752.1: ** (defn main-panel []

(defn main-panel []
    (fn []

      [stuff conn]))
;@+node:conor.20160608034752.3: ** (comment


(comment
;@+node:conor.20160608034752.4: *3* (register-handler :initialize-db (fn [_ [_


 (register-handler
  :initialize-db
  (fn [_ [_ conn]]
      (let [node1  {:db/id -1
                    :node/text "Hello Graphs"}
            layout {:db/id -2
                    :layout/x 50
                    :layout/y 50
                    :layout/height 500
                    :layout/width 500
                    :layout/nodeid (:db/id node1)}
            canvas {:db/id -3
                    :db/ident :canvas
                    :canvas/layouts #{(:db/id layout)}}]
        (do (db/transact! conn
                         [node1 layout canvas])
            @conn))))
;@+node:conor.20160608034752.5: *3*
;@+node:conor.20160608034752.6: *3* (register-sub :layouts (fn [_ [_


 (register-sub
  :layouts
  (fn [_ [_ conn]]
   (let [layouts (q conn '[:find ?layouts
                           :where
                           [_ :canvas/layouts ?layouts]])]
     (reaction
      (for [[eid] @layouts]
        eid)))))

;@+node:conor.20160608034752.7: *3* (def lillayouts  (reaction @(subscribe

 (def lillayouts
   (reaction @(subscribe [:layouts])))
;@+node:conor.20160608034752.8: *3* (declare render-layout)
 (declare render-layout)

 (defn canvas [conn]
   (let [layouts (subscribe [:layouts conn])]
     (fn [conn]
       (into
        [:svg
         {:height 1000
          :width 1000
          :style {:border "1px solid black"}}]
        (for
            [l @layouts]
         [render-layout l conn])))))
;@+node:conor.20160608034752.9: *3* (register-sub :layout (fn [db [_

 (register-sub
  :layout
  (fn [db [_ eid conn]]
      (pull conn '[:layout/x :layout/y :layout/height :layout/width :layout/nodeid] eid)))
;@+node:conor.20160608034752.10: *3* (register-sub :node (fn [db [_
 (register-sub
  :node
  (fn [db [_ eid conn]]
    (pull conn '[*] eid)))
;@+node:conor.20160608034753.1: *3* (defn render-layout [eid conn]
 (defn render-layout [eid conn]
   (let [{x :layout/x id :layout/nodeid y :layout/y h :layout/height w :layout/width} @(subscribe [:layout eid conn])
         node (subscribe [:node (:db/id id) conn conn])]
     (fn []
       [:g
        [:rect
         {:x x
          :y y
          :height h
          :width w
          :fill :blue}]
        [:rect
         {:x x
          :y y
          :stroke "black"
          :stroke-width 1
          :height h
          :width (/ w 2)
          :fill :white}]
        [:rect
         {:x (+ x (/ w 2 3))
          :y (+ y (/ w 3))
          :stroke "black"
          :stroke-width 1
          :height (/ w 3)
          :width (/ w 2 3)
          :fill :grey}]
        [:rect
         {:x x
          :y y
          :stroke "black"
          :stroke-width 1
          :height (/ h 6)
          :width w
          :fill :white}]
        [:foreignObject
         {:x x
          :y y
          :width w
          :height (/ h 6)}
         [:div.hbox.hcenter
          {:style {:width w}}
          [:h1 (:node/text @node)]
          #_[:div (pr-str @node)]]]])))



 (defn hickory-printer [text]
    [:div
     (pr-str (-> (as-hiccup (hickory.core/parse @text))
                 first
                     (get 3)
                     rest
                     rest))])
;@+node:conor.20160608034753.2: *3* (defn svghickory []  (let
 (defn svghickory []
   (let [text (rx/atom "")]
     (fn []
       [:div
        [hickory-printer text]
        [:textarea
         {:style {:border "5px solid red"}
          :on-change #(reset! text (e-value %))}]])))
;@+node:conor.20160608034753.3: *3* (defn e-by-av [db a v]

 (defn e-by-av [db a v]
   (-> (db/datoms db :avet a v) first :e))
;@+node:conor.20160608034753.4: *3* @conn
 @conn

 (e-by-av @conn :layout/y 50)
;@+node:conor.20160608034753.5: *3* @(q conn '[:find ?e
 @(q conn '[:find ?e
            :where
            [?e :layout/y 50]]))

;@-others




;@-leo
