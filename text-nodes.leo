<?xml version="1.0" encoding="utf-8"?>
<!-- Created by Leo: http://leoeditor.com/leo_toc.html -->
<leo_file xmlns:leo="http://leoeditor.com/namespaces/leo-python-editor/1.1" >
<leo_header file_format="2" tnodes="0" max_tnode_index="0" clone_windows="0"/>
<globals body_outline_ratio="0.5" body_secondary_ratio="0.5">
	<global_window_position top="50" left="50" height="500" width="700"/>
	<global_log_window_position top="0" left="0" height="0" width="0"/>
</globals>
<preferences/>
<find_panel_settings/>
<vnodes>
<v t="conor.20160605011037.1" a="E"><vh>@path ~/orgmap/leo/text-nodes</vh>
<v t="conor.20160605011510.1" a="E"><vh>@auto log.org</vh></v>
<v t="conor.20160605013539.1" a="E"><vh>@auto specter.org</vh></v>
</v>
<v t="conor.20160605013539.1" a="E"></v>
<v t="conor.20160605013848.1"><vh>@auto datascript.org</vh></v>
<v t="conor.20160605010926.1" a="E"><vh>@path src/</vh>
<v t="conor.20160605011023.2" a="EM"><vh>/clj/</vh>
<v t="conor.20160605011025.1" a="EM"><vh>/text_nodes/</vh>
<v t="conor.20160605012522.1" a="EM"><vh>@auto core.clj</vh>
<v t="conor.20160605013418.1" a="E"><vh>namespace -- datascript and specter</vh>
<v t="conor.20160605013418.3"><vh>(deptharray-&gt;graph identity vector conj test-struct)</vh></v>
<v t="conor.20160605013418.4"><vh>(defn dbafter-&gt;eid [rv]  (-&gt; </vh></v>
</v>
</v>
<v t="conor.20160605011026.1" a="M"><vh>css.clj</vh></v>
</v>
</v>
<v t="conor.20160605011023.1" a="EM"><vh>/cljs/</vh>
<v t="conor.20160605011952.1" a="EM"><vh>/text_nodes/</vh>
<v t="conor.20160605011953.6" a="M"><vh>config.cljs</vh></v>
<v t="conor.20160605011953.5" a="M"><vh>@auto core.cljs</vh></v>
<v t="conor.20160605011953.4" a="M"><vh>@auto db.cljs</vh></v>
<v t="conor.20160605011953.3" a="M"><vh>@auto handlers.cljs</vh></v>
<v t="conor.20160605011953.2" a="M"><vh>@auto subs.cljs</vh></v>
<v t="conor.20160605011953.1" a="EM"><vh>@auto views.cljs</vh>
<v t="conor.20160605012304.1" a="E"><vh>@language unknown_language</vh>
<v t="conor.20160605012305.1" a="E"><vh>(def schema {;:canvas/layouts   </vh>
<v t="conor.20160605012305.2" a="E"><vh> </vh>
<v t="conor.20160605012305.3" a="E"><vh>(defn nodify [nseq]  (loop </vh>
<v t="conor.20160605012305.4"><vh> </vh></v>
<v t="conor.20160605012305.5"><vh>(defonce conn  (doto (db/create-conn </vh></v>
<v t="conor.20160605012305.6"><vh>(register-sub :db-atoms (fn [_ [_ </vh></v>
<v t="conor.20160605012305.7"><vh>(register-sub :db-entities (fn [_ [_ </vh></v>
<v t="conor.20160605012305.8"><vh>(register-sub :e (fn [_ [_ </vh></v>
<v t="conor.20160605012305.9"><vh>(defn connview [conn]  (let </vh></v>
<v t="conor.20160605012305.10"><vh>(defn pr-entity [conn eid]  </vh></v>
<v t="conor.20160605012305.11"><vh>(defn entity-view [conn]  (let </vh></v>
<v t="conor.20160605012305.12"><vh> </vh></v>
<v t="conor.20160605012305.13"><vh> </vh></v>
<v t="conor.20160605012305.14"><vh>(def test-struct  [[0  </vh></v>
<v t="conor.20160605012305.15"><vh>(select ALL test-struct)</vh></v>
<v t="conor.20160605012305.16"><vh>(defn get-children [treesarray]   </vh></v>
<v t="conor.20160605012305.17"><vh>(pprint (select [ALL]  (nodify </vh></v>
<v t="conor.20160605012306.1"><vh>(register-handler :init (fn [db [_ </vh></v>
<v t="conor.20160605012305.18"><vh>(defn seperate-graph-map [graph-map-array]  (map </vh></v>
<v t="conor.20160605012306.2"><vh>(register-sub :testmap (fn [db]  </vh></v>
<v t="conor.20160605012306.3"><vh>(register-handler :change-text (fn [db [_ </vh></v>
<v t="conor.20160605012306.4"><vh>(defn count-tabs  [string]  </vh></v>
<v t="conor.20160605012306.5"><vh>(defn tvalue [e]  (-&gt; </vh></v>
<v t="conor.20160605012306.6"><vh>  (register-sub :parsed-text (fn </vh></v>
<v t="conor.20160605012306.7"><vh>(register-handler :clear-text (fn [db [_ </vh></v>
<v t="conor.20160605012306.8"><vh>(register-handler :fix-tree (fn [db]  </vh></v>
<v t="conor.20160605012306.9" a="E"><vh>(register-sub :tree (fn [db]  </vh>
<v t="conor.20160605012306.10"><vh>box sample</vh></v>
</v>
<v t="conor.20160605012306.11"><vh>(defn tree [t]  (let </vh></v>
<v t="conor.20160605012306.12" a="E"><vh>     </vh>
<v t="conor.20160605012306.13" a="E"><vh>(defn tree-display []  (let </vh>
<v t="conor.20160605012307.1"><vh> </vh></v>
<v t="conor.20160605012307.2" a="E"><vh>(defn demo []  [v-box </vh>
<v t="conor.20160605012307.3" a="E"><vh> </vh>
<v t="conor.20160605012307.4" a="E"><vh>(defn demo2 []  [h-box </vh>
<v t="conor.20160605012307.5" a="E"><vh>(defn stuff [conn]  (let </vh>
<v t="conor.20160605012307.6" a="E"><vh> </vh>
<v t="conor.20160605012307.7" a="E"><vh> </vh>
<v t="conor.20160605012307.8" a="E"><vh> </vh>
<v t="conor.20160605012307.9" a="E"><vh> </vh>
<v t="conor.20160605012307.10" a="E"><vh> </vh>
<v t="conor.20160605012307.11" a="E"><vh> </vh>
<v t="conor.20160605012307.12" a="E"><vh>(defn main-panel []   </vh>
<v t="conor.20160605012307.13" a="E"><vh> </vh>
<v t="conor.20160605012307.14" a="E"><vh>(comment  </vh>
<v t="conor.20160605012308.1" a="E"><vh>(register-handler :initialize-db (fn [_ [_ </vh>
<v t="conor.20160605012308.2" a="E"><vh> </vh>
<v t="conor.20160605012308.3" a="E"><vh>(register-sub :layouts (fn [_ [_ </vh>
<v t="conor.20160605012308.4" a="E"><vh>(def lillayouts  (reaction @(subscribe </vh>
<v t="conor.20160605012308.5" a="E"><vh>(declare render-layout)</vh>
<v t="conor.20160605012308.6" a="E"><vh>(register-sub :layout (fn [db [_ </vh>
<v t="conor.20160605012308.7" a="E"><vh>(register-sub :node (fn [db [_ </vh>
<v t="conor.20160605012308.8" a="E"><vh>(defn render-layout [eid conn]  </vh>
<v t="conor.20160605012308.9" a="E"><vh>(defn svghickory []  (let </vh>
<v t="conor.20160605012308.10" a="E"><vh>(defn e-by-av [db a v] </vh>
<v t="conor.20160605012309.1" a="E"><vh>@conn</vh>
<v t="conor.20160605012309.2"><vh>@(q conn '[:find ?e  </vh></v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</v>
</vnodes>
<tnodes>
<t tx="conor.20160605010926.1"></t>
<t tx="conor.20160605011023.1">@path cljs</t>
<t tx="conor.20160605011023.2">@path clj</t>
<t tx="conor.20160605011025.1">@path text_nodes</t>
<t tx="conor.20160605011026.1"></t>
<t tx="conor.20160605011037.1"></t>
<t tx="conor.20160605011952.1">@path text_nodes</t>
<t tx="conor.20160605011953.1">@language unknown_language
(ns text-nodes.views
  (:require [reagent.core    :as rx]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-sub subscribe dispatch register-handler]]
          ;  [re-frame.db :as rdb :refer [app-db]]
        ;    [history.reframe-db :as ls :refer [!&gt;ls &lt;!ls db-&gt;seq]]
       ;     [history.util  :refer [e-value]]
       ;     [hickory.core :refer [as-hiccup as-hickory]]
            [datascript.core :as db]
            [re-com.core   :as re-com :refer [h-box v-box box gap line scroller border h-split v-split title flex-child-style p]]
            [cljs.pprint     :refer [pprint]]
            [keybind.core :as key]
            [cljs.reader                ]
            [com.rpl.specter  :refer [ALL] :as s]
            [clojure.string  :as str    ])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform defprotocolpath]]
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
         [children siblings] (split-with #(&lt; (first sa) (first %)) r)
         answer     {:node (second sa)
                     :children-visible true}
         answer
         (if (&lt; 0 (count children))
           (assoc answer :children (nodify children))
           (assoc answer :children children))]
      
      (if (&lt; 0 (count siblings))
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


(select ALL test-struct)

;this gives me the first layer


(defn get-children [treesarray]
  
  (select [ALL (s/collect-one :node) :children ALL :node]
          treesarray))

(get-children (nodify test-struct))


(pprint (select [ALL]  (nodify test-struct)))

;;[["a" "b"] ["a" "d"] ["1a" "1b"] ["1a" "1c"]]
;;[["a" "b"] ["a" "d"] ["1a" "1b"] ["1a" "1c"]]




(defn seperate-graph-map [graph-map-array] 
  (map (partial tree-seq :node :children) graph-map-array))



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
  (-&gt; e
      .-target
      .-value))
  

(defn parsed [text]
  (-&gt;&gt; (str/split text #"\n")
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
                                                  (-&gt; % .-target .-selectionStart)
                                                  (-&gt; % .-target .-selectionEnd)])
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
                         (if (&lt; 0 (count (:children t)))
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
               :panel-2 [tree-display]
               ]]])






(defn demo2 []
  [h-box
   :height "100px"
   :justify :center
   :children [
              [box
               :child "Box1"
               :style {:background-color "blue"}
               ]
              [box
               :child "b"
               :style {:background-color "green"}
               ]
              [box
               :child "b"
               :align-self :center
               ]]])



(defn stuff [conn]
  (let [tm (subscribe [:testmap])]
  (fn []
  [:div
   #_[:button {:on-click #(dispatch [:init conn])} "start"]
;   [canvas conn]
   [demo]
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
   (pr-str (-&gt; (as-hiccup (hickory.core/parse @text))
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
  (-&gt; (db/datoms db :avet a v) first :e))


@conn

(e-by-av @conn :layout/y 50)


@(q conn '[:find ?e
          :where
          [?e :layout/y 50]])
)
</t>
<t tx="conor.20160605011953.6"></t>
<t tx="conor.20160605012304.1">@language unknown_language
(ns text-nodes.views
  (:require [reagent.core    :as rx]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-sub subscribe dispatch register-handler]]
          ;  [re-frame.db :as rdb :refer [app-db]]
        ;    [history.reframe-db :as ls :refer [!&gt;ls &lt;!ls db-&gt;seq]]
       ;     [history.util  :refer [e-value]]
       ;     [hickory.core :refer [as-hiccup as-hickory]]
            [datascript.core :as db]
            [re-com.core   :as re-com :refer [h-box v-box box gap line scroller border h-split v-split title flex-child-style p]]
            [cljs.pprint     :refer [pprint]]
            [keybind.core :as key]
            [cljs.reader                ]
            [com.rpl.specter  :refer [ALL] :as s]
            [clojure.string  :as str    ])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform defprotocolpath]]
           [reagent.ratom :refer [reaction]]))</t>
<t tx="conor.20160605012305.1">
(def schema {;:canvas/layouts        {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
             ;:layout/nodeid         {:db/valueType :db.type/ref}
             :node/out-edge         {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
             :edge/to               {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
          ;   :node/nodeId           {:db/valueType :db.type/long}
          ;   :node/content          {:db/valueType :db.type/uri}
           ;  :node/title            {:db/valueType :db.type/string}
             })</t>
<t tx="conor.20160605012305.10">

(defn pr-entity [conn eid]
  (let [e (subscribe [:e conn eid])]
    (fn []
      [:div (pr-str @e)])))</t>
<t tx="conor.20160605012305.11">
(defn entity-view [conn]
  (let [es (subscribe [:db-entities conn])]
    (fn []
      [:div
       (for [[e] @es]
         [pr-entity conn e])])))</t>
<t tx="conor.20160605012305.12"></t>
<t tx="conor.20160605012305.13"></t>
<t tx="conor.20160605012305.14">

(def test-struct  [[0  "a"]
                   [1  "b"]
                   [2  "c"]
                   [1  "d"]
                   [0  "1a"]
                   [1  "1b"]
                   [1  "1c"]])</t>
<t tx="conor.20160605012305.15">(select ALL test-struct)

;this gives me the first layer</t>
<t tx="conor.20160605012305.16">(defn get-children [treesarray]
  
  (select [ALL (s/collect-one :node) :children ALL :node]
          treesarray))

(get-children (nodify test-struct))</t>
<t tx="conor.20160605012305.17">(pprint (select [ALL]  (nodify test-struct)))

;;[["a" "b"] ["a" "d"] ["1a" "1b"] ["1a" "1c"]]
;;[["a" "b"] ["a" "d"] ["1a" "1b"] ["1a" "1c"]]</t>
<t tx="conor.20160605012305.18">

(defn seperate-graph-map [graph-map-array] 
  (map (partial tree-seq :node :children) graph-map-array))</t>
<t tx="conor.20160605012305.2"></t>
<t tx="conor.20160605012305.3">(defn nodify [nseq]
  (loop [result [] 
         s nseq]
    (let[sa (first s)
         r (rest s)
         [children siblings] (split-with #(&lt; (first sa) (first %)) r)
         answer     {:node (second sa)
                     :children-visible true}
         answer
         (if (&lt; 0 (count children))
           (assoc answer :children (nodify children))
           (assoc answer :children children))]
      
      (if (&lt; 0 (count siblings))
        (recur (conj result answer) siblings)
        (conj result answer)))))</t>
<t tx="conor.20160605012305.4"></t>
<t tx="conor.20160605012305.5">(defonce conn
  (doto (db/create-conn schema)
        posh!))</t>
<t tx="conor.20160605012305.6">

(register-sub
 :db-atoms
 (fn [_ [_ conn]]
   (q conn '[:find ?e ?attr ?val
             :where 
             [?e ?attr ?val]])))</t>
<t tx="conor.20160605012305.7">

(register-sub
 :db-entities
 (fn [_ [_ conn]]
  (q conn '[:find ?e
             :where 
             [?e]])))</t>
<t tx="conor.20160605012305.8">(register-sub
 :e
 (fn [_ [_ conn eid]]
   (pull conn '[*] eid)))</t>
<t tx="conor.20160605012305.9">(defn connview [conn]
  (let [datoms (subscribe [:db-atoms conn])]
    (fn []
      [:div
       (for
         [datom @datoms]
         [:div (pr-str datom)])])))</t>
<t tx="conor.20160605012306.1">
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
         (assoc db :testmap (nodify test-struct)))))</t>
<t tx="conor.20160605012306.10">                                 ;; [box
                                 ;;  :min-width "50px"
                                 ;;  :align-self :center
                                 ;;  :style {:background-color "white"
                                 ;;          :margin "10px"
                                 ;;          :border "2px solid blue"}
                                 ;;  :child</t>
<t tx="conor.20160605012306.11">
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
                         (if (&lt; 0 (count (:children t)))
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

                                  [tree child])]]]]])))</t>
<t tx="conor.20160605012306.12"></t>
<t tx="conor.20160605012306.13">
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
           ^{:key t} [tree t])]])))</t>
<t tx="conor.20160605012306.2">

(register-sub
 :testmap
 (fn [db]
   (reaction (:testmap @db))))</t>
<t tx="conor.20160605012306.3">(register-handler
 :change-text
 (fn [db [_ text]]
   (assoc db :text text)))

(register-sub
 :text
 (fn [db]
   (reaction (:text @db))))</t>
<t tx="conor.20160605012306.4">
(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))

(count-tabs "\t\t")</t>
<t tx="conor.20160605012306.5">
(defn tvalue [e]
  (-&gt; e
      .-target
      .-value))
  

(defn parsed [text]
  (-&gt;&gt; (str/split text #"\n")
       (map (juxt count-tabs str/trim))))</t>
<t tx="conor.20160605012306.6">  

(register-sub
 :parsed-text
 (fn [db]
   (reaction (nodify (parsed (:text @db))))))</t>
<t tx="conor.20160605012306.7">(register-handler
 :clear-text
 (fn [db [_ e end]]
   (let [text (:text db)]
     (js/console.log (pr-str e))
     (assoc db :text (str (subs text 0 e) "\t"  (subs text end))))))</t>
<t tx="conor.20160605012306.8">
(register-handler
 :fix-tree
 (fn [db]
   (let [tree @(subscribe [:parsed-text])]
     (assoc db :tree tree))))</t>
<t tx="conor.20160605012306.9">
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
                                                  (-&gt; % .-target .-selectionStart)
                                                  (-&gt; % .-target .-selectionEnd)])
                                       (.preventDefault %))
                                   :else)
                   :value @text}]])))</t>
<t tx="conor.20160605012307.1"></t>
<t tx="conor.20160605012307.10"></t>
<t tx="conor.20160605012307.11"></t>
<t tx="conor.20160605012307.12">
(defn main-panel []
    (fn []
      
      [stuff conn]))</t>
<t tx="conor.20160605012307.13"></t>
<t tx="conor.20160605012307.14">

(comment </t>
<t tx="conor.20160605012307.2">(defn demo []
  [v-box
   :size "auto"
   :gap "10px"
   :children [
              [re-com/h-split   
               :panel-1 [tree-text]
               :panel-2 [tree-display]
               ]]])</t>
<t tx="conor.20160605012307.3"></t>
<t tx="conor.20160605012307.4">
(defn demo2 []
  [h-box
   :height "100px"
   :justify :center
   :children [
              [box
               :child "Box1"
               :style {:background-color "blue"}
               ]
              [box
               :child "b"
               :style {:background-color "green"}
               ]
              [box
               :child "b"
               :align-self :center
               ]]])</t>
<t tx="conor.20160605012307.5">
(defn stuff [conn]
  (let [tm (subscribe [:testmap])]
  (fn []
  [:div
   #_[:button {:on-click #(dispatch [:init conn])} "start"]
;   [canvas conn]
   [demo]
   [entity-view conn]
   [:h1 (pr-str @tm)]
])))</t>
<t tx="conor.20160605012307.6"></t>
<t tx="conor.20160605012307.7"></t>
<t tx="conor.20160605012307.8"></t>
<t tx="conor.20160605012307.9"></t>
<t tx="conor.20160605012308.1">

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
             @conn))))</t>
<t tx="conor.20160605012308.10">
(defn e-by-av [db a v]
  (-&gt; (db/datoms db :avet a v) first :e))</t>
<t tx="conor.20160605012308.2"></t>
<t tx="conor.20160605012308.3">

(register-sub
 :layouts
 (fn [_ [_ conn]]
  (let [layouts (q conn '[:find ?layouts
                         :where
                         [_ :canvas/layouts ?layouts]])]
    (reaction 
     (for [[eid] @layouts]
       eid)))))
    </t>
<t tx="conor.20160605012308.4">
(def lillayouts
  (reaction @(subscribe [:layouts])))</t>
<t tx="conor.20160605012308.5">(declare render-layout)

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
        [render-layout l conn])))))</t>
<t tx="conor.20160605012308.6">
(register-sub
 :layout
 (fn [db [_ eid conn]]
      (pull conn '[:layout/x :layout/y :layout/height :layout/width :layout/nodeid] eid)))</t>
<t tx="conor.20160605012308.7">(register-sub
 :node
 (fn [db [_ eid conn]]
   (pull conn '[*] eid)))</t>
<t tx="conor.20160605012308.8">(defn render-layout [eid conn]
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
   (pr-str (-&gt; (as-hiccup (hickory.core/parse @text))
                   first
                   (get 3)
                   rest
                   rest))])</t>
<t tx="conor.20160605012308.9">(defn svghickory []
  (let [text (rx/atom "")]
    (fn []
      [:div
      [hickory-printer text]
       [:textarea
        {:style {:border "5px solid red"}
         :on-change #(reset! text (e-value %))}]])))</t>
<t tx="conor.20160605012309.1">@conn

(e-by-av @conn :layout/y 50)</t>
<t tx="conor.20160605012309.2">@(q conn '[:find ?e
          :where
          [?e :layout/y 50]])
)
</t>
<t tx="conor.20160605012522.1">@language clojure
(ns text-nodes.core
  (:require [com.rpl.specter :as s
             :refer [ALL]]
            [clojure.pprint :refer [pprint]]
            [datascript.core  :as db])
  (:use [com.rpl.specter.macros 
         :only [select transform defprotocolpath
                extend-protocolpath]]))



(def test-struct [[0 :a] [1 :b] [2 :c] [0 :d] [1 :e] [1 :f]])
(comment  

                    (defprotocolpath TreeWalker [])

                    (extend-protocolpath TreeWalker
                      Object nil
                      clojure.lang.PersistentVector [s/ALL TreeWalker])

                    (select [TreeWalker number?] [:a 1 [2 [[[3]]] :e] [4 5 [6 7]]])
                    (select [TreeWalker :a] 
                               
                    [:a 1 [2 [[[3 {:a "b"}]]] :e] [4 {:a '({} {:b {:c :d}})} 5 [6 7]]])
                    
                    
                    


(deptharray-&gt;graph identity vector conj test-struct)

(deptharray-&gt;graph identity vector a test-struct)

(def deptharray-&gt;nestedmap 
  (partial deptharray-&gt;graph create-node connect-node conj))

(pprint (deptharray-&gt;nestedmap test-struct))




(defn dbafter-&gt;eid [rv]
  (-&gt; rv
     :tx-data
     ffirst))

(def fake-db (db/create-conn))

(defn create-ds-node [db text]
  (let [eid (db/q '[:find ?e
                    :in $ ?text
                    :where
                    [?e :node/text ?text]] 
                  @db
                  text)]
    (or (ffirst eid)
        (dbafter-&gt;eid (db/transact! db [{:db/id -1
                                         :node/text text}])))))

(def rv (create-ds-node fake-db "l"))


@fake-db

</t>
<t tx="conor.20160605013418.1">@language clojure
(ns text-nodes.core
  (:require [com.rpl.specter :as s
             :refer [ALL]]
            [clojure.pprint :refer [pprint]]
            [datascript.core  :as db])
  (:use [com.rpl.specter.macros 
         :only [select transform defprotocolpath
                extend-protocolpath]]))</t>
<t tx="conor.20160605013418.3">(deptharray-&gt;graph identity vector conj test-struct)

(deptharray-&gt;graph identity vector a test-struct)

(def deptharray-&gt;nestedmap 
  (partial deptharray-&gt;graph create-node connect-node conj))

(pprint (deptharray-&gt;nestedmap test-struct))</t>
<t tx="conor.20160605013418.4">

(defn dbafter-&gt;eid [rv]
  (-&gt; rv
     :tx-data
     ffirst))

(def fake-db (db/create-conn))

(defn create-ds-node [db text]
  (let [eid (db/q '[:find ?e
                    :in $ ?text
                    :where
                    [?e :node/text ?text]] 
                  @db
                  text)]
    (or (ffirst eid)
        (dbafter-&gt;eid (db/transact! db [{:db/id -1
                                         :node/text text}])))))

(def rv (create-ds-node fake-db "l"))</t>
</tnodes>
</leo_file>
