(ns text-nodes.views
  (:require [reagent.core    :as rx]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-sub subscribe dispatch register-handler]]
          ;  [re-frame.db :as rdb :refer [app-db]]
        ;    [history.reframe-db :as ls :refer [!>ls <!ls db->seq]]
       ;     [history.util  :refer [e-value]]
       ;     [hickory.core :refer [as-hiccup as-hickory]]
            [datascript.core :as db]
            [cljs.pprint     :refer [pprint]]
            [keybind.core :as key]
            [cljs.reader                ]
            [clojure.string  :as str    ])
  (:require-macros
            [reagent.ratom :refer [reaction]]))



(def schema {;:canvas/layouts        {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
             ;:layout/nodeid         {:db/valueType :db.type/ref}
             :node/out-edge         {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
             :edge/to               {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
          ;   :node/nodeId           {:db/valueType :db.type/long}
          ;   :node/content          {:db/valueType :db.type/uri}
           ;  :node/title            {:db/valueType :db.type/string}
             })






(defn nodify [nseq]
  (loop [result [] 
         s nseq]
    (let[sa (first s)
         r (rest s)
         [children siblings] (split-with #(< (first sa) (first %)) r)
         answer     {:node (second sa)}
         answer
         (if (< 0 (count children))
           (assoc answer :children (nodify children))
           (assoc answer :children children))]
      
      (if (< 0 (count siblings))
        (recur (conj result answer) siblings)
        (conj result answer)))))





(defonce conn
  (doto (db/create-conn schema)
        posh!))




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










(def test-struct  [[0  "a"]
                   [1  "b"]
                   [2  "c"]
                   [1  "d"]
                   [0  "1a"]
                   [1  "1b"]
                   [1  "1c"]])



(nodify test-struct)





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




(defn add-a []
  []
  (let [val (atom "")
        stop #(reset! val "")
        fire #(do
                (dispatch [:add-answer-1 @val])
                (stop)
                (js/alert @val))]
    (fn []
     [:div
       [:input {:value @val
                :on-change #(reset! val (-> %
                                            .-target
                                            .-value))
                :on-key-down #(case (.-which %)
                               13 (fire))}]

       [:button {:on-click fire} "Add Answer"]])))



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
   (reaction (pr-str (nodify (parsed (:text @db)))))))


(register-handler
 :clear-text
 (fn [db]
   (let [text (:text db)]
     (js/console.log text)
     (assoc db :text (str text "\t")))))


(defn tree-text []
  (let [text (subscribe [:text])
        p    (subscribe [:parsed-text])]
    (fn []
      [:div
       
       [:h1 @p]
       [:textarea {:style {:width 500 :height 500}
                   :on-change #(dispatch [:change-text (tvalue %)])
                   :on-key-down #(case (.-which %)
                                   9 (do
                                       (dispatch [:clear-text])
                                       (.preventDefault %))
                                   :else)
                   :value @text}]])))



(key/bind! "shift-space" ::prev #(dispatch [:change-text "blamo"]))




(defn stuff [conn]
  (let [tm (subscribe [:testmap])]
  (fn []
  [:div
   [:button {:on-click #(dispatch [:init conn])} "start"]
;   [canvas conn]
   [connview conn]
   [tree-text]
   [entity-view conn]
   [:h1 (pr-str @tm)]
])))





















(defn main-panel []
    (fn []
      [stuff conn]))







(comment 




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







(register-sub
 :layouts
 (fn [_ [_ conn]]
  (let [layouts (q conn '[:find ?layouts
                         :where
                         [_ :canvas/layouts ?layouts]])]
    (reaction 
     (for [[eid] @layouts]
       eid)))))
    



(def lillayouts
  (reaction @(subscribe [:layouts])))


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



(register-sub
 :layout
 (fn [db [_ eid conn]]
      (pull conn '[:layout/x :layout/y :layout/height :layout/width :layout/nodeid] eid)))


(register-sub
 :node
 (fn [db [_ eid conn]]
   (pull conn '[*] eid)))


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
         #_[:div (pr-str @node) ]]]])))
     
  

  
   


(defn hickory-printer [text]
   [:div 
   (pr-str (-> (as-hiccup (hickory.core/parse @text))
                   first
                   (get 3)
                   rest
                   rest))])


(defn svghickory []
  (let [text (rx/atom "")]
    (fn []
      [:div
      [hickory-printer text]
       [:textarea
        {:style {:border "5px solid red"}
         :on-change #(reset! text (e-value %))}]])))



(defn e-by-av [db a v]
  (-> (db/datoms db :avet a v) first :e))


@conn

(e-by-av @conn :layout/y 50)


@(q conn '[:find ?e
          :where
          [?e :layout/y 50]])
)
