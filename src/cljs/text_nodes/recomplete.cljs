(ns text-nodes.recomplete
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-complete.core :as re-complete]
            [re-complete.dictionary :as dictionary]
            [reagent.core :as reagent]
            [re-frame.core :refer [register-handler
                                   dispatch
                                   dispatch-sync
                                   register-sub
                                   subscribe]]
            [clojure.string :as string]))


(defn tvalue [e]
  (-> e
      .-target
      .-value))



;; Initial state

#_(def initial-state {})

;; --- Handlers ---

#_(register-handler
 :initialize
 (fn
   [db _]
   (merge db initial-state)))


(comment
(register-handler
 :selected-item
 (fn [db [_ linked-component-key hovered-item]]
   (let [suggestion-list (get-in db [:re-complete :linked-components linked-component-key :completions])
         suggestion-indexed-vector (map-indexed vector suggestion-list)
         hovered-item-with-index (first (filter (comp (partial = hovered-item) second) suggestion-indexed-vector))]
     (assoc-in db [:re-complete :linked-components linked-component-key :selected-item] hovered-item-with-index))))






(defn items-to-complete
  "List of the items to autocomplete by given input and list of the all items"
  [case-sensitive? dictionary input]
  (if (= input nil)
    []
    (filter #(case-sensitivity case-sensitive? % input) dictionary)))



(defn opening-excluded-chars [word excluded-chars]
  (if ((set (map #(= (first word) %) excluded-chars)) true)
    (opening-excluded-chars (apply str (rest word)) excluded-chars)
    word))


(defn closing-excluded-chars [word excluded-chars]
  (if ((set (map #(= (last word) %) excluded-chars)) true)
    (closing-excluded-chars (apply str (butlast word)) excluded-chars)
    word))


(defn completions [word dictionary {:keys [trim-chars case-sensitive?]}]
  (let [new-text (-> word
                     (opening-excluded-chars trim-chars)
                     (closing-excluded-chars trim-chars))]
    (items-to-complete case-sensitive? dictionary new-text)))

)


(register-handler
 :add-item-to-list
 (fn
   [db [_ list-name input]]
   (update-in db [(keyword list-name) :added-items] #(vec (conj % input)))))


(register-handler
 :clear-input
 (fn
   [db [_ linked-component-key]]
   (assoc-in db [:re-complete :linked-components (keyword linked-component-key) :text] "")))

;; --- Subscription Handlers ---

(register-sub
 :get-list
 (fn
   [db [_ list-name]]
   (reaction (get-in @db [(keyword list-name) :added-items]))))


;; --- VIEW --- ;;

(def my-lists [["vegetable" (sort dictionary/vegetables) {:trim-chars "[]()"
                                                          :keys-handling {:visible-items 4
                                                                          :item-height 20}}]
               ["fruit" (sort-by count dictionary/fruits) {:trim-chars "?"
                                                           :case-sensitive? true}]
               ["grain" dictionary/grains]])

(defn list-view [items]
  (map (fn [item]
         ^{:key item}
         [:li.item item])
       items))

(defn render-list
  ([list-name dictionary]
   (render-list list-name dictionary nil))
  ([list-name dictionary options]
   (let [get-input (subscribe [:get-previous-input list-name])
         get-list (subscribe [:get-list list-name])]
     (dispatch [:options list-name options])
     (dispatch [:dictionary list-name dictionary])
     (fn []
       [:div {:className (str list-name " my-list")}
        [:div {:className "panel panel-default re-complete"}
         [:div {:className "panel-heading"}
          [:h1 (string/capitalize (str list-name "s"))]]
         [:div.panel-body
          [:ul.checklist
           [:li.input
            [:input {:type "text"
                     :className "form-control input-field"
                     :placeholder (str list-name " name")
                     :value @get-input
                     :on-change (fn [event]
                                  (dispatch [:input list-name (.. event -target -value)]))
                     :on-focus #(dispatch [:focus list-name true])
                     :on-blur #(dispatch [:focus list-name false])}]
            [:button {:type "button"
                      :className "btn btn-default button-ok"
                      :on-click #(do (dispatch [:add-item-to-list list-name @get-input])
                                     (dispatch [:clear-input list-name]))}
             [:span {:className "glyphicon glyphicon-ok check"}]]]
           (list-view @get-list)]]
         [:div
          [re-complete/completions list-name]]]]))))



#_(register-handler
 :mouse-on-suggestion-list
 (fn [db [_ item]]
   (js/alert "WOOT" item)))


(dispatch [:dictionary "veg" '("aaa" "bbbb" "aabbcc" "Salami")])

#_(register-handler
 :console-set-autocompleted-text
 (fn console-set-text [db [_ console-key]]
   (rc-app/set-console-text db console-key (get-in db [:re-complete :linked-components (keyword console-key) :text]))))


(defn rec [list-name]
  (let [text (subscribe [:text])]
    (fn []
      [:div
       [:div {:className (str list-name " my-list")}
        [:div {:className "panel panel-default re-complete"}
         [:div {:className "panel-heading"}
          [:h1 (string/capitalize (str list-name "s"))]]
         [:div.panel-body
          [:ul.checklist
           [:li.input
            [:input {:type "text"
                     :className "form-control input-field"
                     :placeholder (str list-name " name")
                     :value @text
                     :on-change (fn [event]
                                  (dispatch [:input list-name (.. event -target -value)]))
                     :on-focus #(dispatch [:focus list-name true])
                     :on-blur #(dispatch [:focus list-name false])}]
            [:button {:type "button"
                      :className "btn btn-default button-ok"
                      :on-click #(do (dispatch [:add-item-to-list list-name @text])
                                     (dispatch [:clear-input list-name]))}
             [:span {:className "glyphicon glyphicon-ok check"}]]]
           (list-view @text)]]
         [:div.re-completion-list-part
          [re-complete/completions list-name]]]]])))






(defn recomplete-demo []
  (fn []
   [rec "veg"]
  #_(into [:div.my-app]
        (map #(into [render-list] %) my-lists))))

;; --- Main app fn ---

#_(defn ^:export main []
  (dispatch-sync [:initialize])
  (reagent/render [my-app] (.getElementById js/document "app")))
