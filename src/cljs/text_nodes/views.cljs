
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
            [cljs.spec  :as s]
            [com.rpl.specter  :refer [ALL] :as sp]
            [clojure.string  :as str])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform defprotocolpath]]
           [reagent.ratom :refer [reaction]]))





(defn tvalue [e]
  (-> e
      .-target
      .-value))




(defn connview [conn]
  (let [datoms (subscribe [:datoms conn])]
    (fn []
      [:div
       (for
         [datom @datoms]
         ^{:key datom }[:div (pr-str datom)])])))



(defn pr-entity [conn eid]
  (let [e (subscribe [:e conn eid])]
    (fn []
      [:div (pr-str @e)])))



(defn entity-view [conn]
  (let [es (subscribe [:db-entities conn])]
    (fn []
      [:div
       (for [[e] @es]
         ^{:key e }[pr-entity conn e])])))



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
  (let [visible? (rx/atom (:expanded t))]
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

                        :child (:node/title t)]
                       [box
                        :align-self :center
                        :child
                        [:div
                         (if (< 0 (count (:edge/to t)))
                           [:button {:on-click #(do
                                                  (reset! visible? (not @visible?)))}

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
                        (for [child (:edge/to t)]
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




(register-handler
 :assoc-in-path
 (fn [db [_ p v]]
   (assoc-in db p v)))


(defn focus-append [this]
  (doto (.getDOMNode this)
    (.focus)
    (.setSelectionRange 100000 100000)))

(defn focus-append-input [m]
  (rx/create-class
   {:display-name "focus-append-component"
    :component-did-mount focus-append
    :reagent-render
    (fn focus-append-input-render [m]
      [:input
       (merge
        {:type "text"
         :name "text"
         :style {:width "100%"}}
        m)])}))



(def key-code-name
  {13 "ENTER"
   27 "ESC"
   46 "DELETE"
   8 "BACKSPACE"})

(defn save [path editing write e]
  (.preventDefault e)
  (write path (.. e -target -value))
  (dispatch [:assoc-in-path [:editing] nil]))

(register-sub
 :editing
 (fn [db]
   (reaction (:editing @db))))


(defn editable-string
  ([path]
   (editable-string path
                    (fn update-model [p v]
                      (dispatch [:assoc-in-path p v]))))
  ([path write]
   (let [editing (subscribe [:editing])
         dv      (subscribe [:title])]
     (fn []
       (if (= path @editing)
         [focus-append-input
          {:default-value @dv
           :on-blur
           (fn editable-string-blur [e]
             (save path editing write e))
           :on-key-down
           (fn editable-string-key-down [e]
             (case (key-code-name (.-keyCode e))
               "ESC" (dispatch [:assoc-in-path [:editing] nil])
               "ENTER" (save path editing write e)
               nil))}]
         [:div.editable
          {:style {:width "100%"
                   :cursor "pointer"}
           :on-click
           (fn editable-string-click [e]
             (dispatch [:assoc-in-path [:editing] path]))}
          @dv
          [:span.glyphicon.glyphicon-pencil.edit]])))))

(defn title1 []
  (fn []
    [editable-string [:title]]))

#_(defn title1 []
  (let [t (subscribe [:title])
        editing? (rx/atom true)]
    (fn []
      [:div
        (if (or @editing? (not (< 0 (count @t))))
          [:span
            [:input {:value @t
                     :on-mouse-leave #(reset! editing? false)
                     :on-change #(dispatch [:edit-title  (tvalue %)])}]
            [:button {:on-click #(reset! editing? false)} "X"]]
          [:div [:strong {:style {:font-size 50}
                          :on-click #(reset! editing? true)} @t]
                [:strong "click to edit me"]])])))




(defn demo []
  [v-box
   :size "auto"
   :gap "10px"
   :children [
              [re-com/h-split
               :panel-1 [tree-text]
               :panel-2 [tree-display]]]])


(defn nodes []
  (let [nodes (subscribe [:nodes])]
    (fn []
      [:div
       (for [n @nodes]
        [:button (pr-str n)])])))

(defn stuff [conn]
   (fn []
    [:div
     [title1]
     [:button {:on-click #(dispatch [:tree->ds conn])} "Convert"]
     [demo]
     [nodes]
     [entity-view conn]]))


(defn main-panel []
    (fn []
      [stuff conn]))
