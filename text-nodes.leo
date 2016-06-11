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
<v t="conor.20160610112520.1"><vh>spootnik.org %u2013 Simple materialized views in kafka and clojure</vh></v>
<v t="conor.20160606053544.1" a="E"><vh>Files to write</vh>
<v t="conor.20160605011037.1"><vh>@path ~/orgmap/leo/text-nodes</vh>
<v t="conor.20160605011510.1"><vh>@auto log.org</vh></v>
<v t="conor.20160605013539.1" a="E"><vh>@auto specter.org</vh></v>
</v>
<v t="conor.20160605013848.1"><vh>@auto datascript.org</vh></v>
<v t="conor.20160605010926.1" a="E"><vh>@path src/</vh>
<v t="conor.20160605011023.2"><vh>/clj/</vh>
<v t="conor.20160610142118.2" a="E"
expanded="conor.20160610145855.1,conor.20160610150430.1,"><vh>@file core.clj</vh></v>
<v t="conor.20160610150037.1"
expanded="conor.20160610150348.1,"><vh>@file helpers.clj</vh></v>
<v t="conor.20160610142118.1"><vh>@file spec-test.clj</vh></v>
<v t="conor.20160605011025.1"><vh>/text_nodes/</vh>
<v t="conor.20160606161843.1" a="E"
expanded="conor.20160608031859.1,"><vh>@file specter-test.clj</vh></v>
<v t="conor.20160606052632.1" a="E"
expanded="conor.20160606073225.1,"><vh>@file spec-test.clj</vh></v>
<v t="conor.20160605011026.1"><vh>css.clj</vh></v>
</v>
</v>
<v t="conor.20160605011023.1" a="E"><vh>/cljs/</vh>
<v t="conor.20160605011952.1" a="E"><vh>/text_nodes/</vh>
<v t="conor.20160610151345.1"><vh>@clean views.cljs</vh></v>
<v t="conor.20160610082638.1"><vh>@file specs.cljs</vh></v>
<v t="conor.20160605011953.6"><vh>config.cljs</vh></v>
<v t="conor.20160605011953.5"><vh>@auto core.cljs</vh></v>
<v t="conor.20160605011953.4"><vh>@file db.cljs</vh></v>
<v t="conor.20160605011953.3" a="E"
expanded="conor.20160610152638.1,conor.20160610152638.5,conor.20160610152638.6,conor.20160610073243.1,"><vh>@file handlers.cljs</vh></v>
<v t="conor.20160605011953.2" a="E"><vh>@file subs.cljs</vh></v>
</v>
</v>
</v>
</v>
<v t="conor.20160610164523.1" a="E"><vh>lessons</vh>
<v t="conor.20160608034752.3"><vh>(comment  </vh>
<v t="conor.20160608034752.4"><vh>(register-handler :initialize-db (fn [_ [_</vh></v>
<v t="conor.20160608034752.5"><vh> </vh></v>
<v t="conor.20160608034752.6"><vh>(register-sub :layouts (fn [_ [_ </vh></v>
<v t="conor.20160608034752.7"><vh>(def lillayouts  (reaction @(subscribe </vh></v>
<v t="conor.20160608034752.8"><vh>(declare render-layout)</vh></v>
<v t="conor.20160608034752.9"><vh>(register-sub :layout (fn [db [_ </vh></v>
<v t="conor.20160608034752.10"><vh>(register-sub :node (fn [db [_ </vh></v>
<v t="conor.20160608034753.1"><vh>(defn render-layout [eid conn]  </vh></v>
<v t="conor.20160608034753.2"><vh>(defn svghickory []  (let </vh></v>
<v t="conor.20160608034753.3"><vh>(defn e-by-av [db a v] </vh></v>
<v t="conor.20160608034753.4"><vh>@conn</vh></v>
<v t="conor.20160608034753.5"><vh>@(q conn '[:find ?e  </vh></v>
</v>
<v t="conor.20160610170215.1"><vh>notes from before </vh></v>
<v t="conor.20160610151933.1"><vh>demo of re-com</vh></v>
<v t="conor.20160610162904.1"><vh>specter lessons</vh>
<v t="conor.20160610162909.1"><vh>specter transform</vh></v>
</v>
<v t="conor.20160610160233.1"><vh>Resurrected Nodes</vh>
<v t="conor.20160610160233.2"><vh>From @file handlers.cljs</vh>
<v t="conor.20160610152638.10"><vh>test</vh></v>
</v>
<v t="conor.20160610164428.1"><vh>From @file handlers.cljs</vh>
<v t="conor.20160610164021.5"><vh>(get-edges (:tree @app-db)) </vh></v>
</v>
</v>
<v t="conor.20160610183452.1"><vh>favorites</vh></v>
<v t="conor.20160610183437.1"><vh>(defn tree-&gt;ds1 [tree]  (transform </vh></v>
</v>
<v t="conor.20160610164540.1" a="E"><vh>Active</vh>
<v t="conor.20160610073243.1" a="E"><vh>Parse-Text</vh>
<v t="conor.20160610164021.2"><vh>(declarepath TOPSORT)</vh></v>
<v t="conor.20160610164021.3"><vh>(def CHILDREN (comp-paths :children ALL))</vh></v>
<v t="conor.20160610164021.4"><vh>(defn get-edges [tree]  (select </vh></v>
<v t="conor.20160610164021.6"><vh>(defn dbafter-&gt;eid [rv]  (-&gt; </vh></v>
<v t="conor.20160610164021.7"><vh>(defn create-ds-node [db text]  </vh></v>
<v t="conor.20160610164021.8"><vh>(defn tree-&gt;ds1 [tree]  (transform </vh></v>
<v t="conor.20160610164021.10"><vh>(d/transact! conn [{:db/id [:node/text "Hello </vh></v>
<v t="conor.20160610164021.11"><vh>(d/transact! conn [{:db/id -1  </vh></v>
<v t="conor.20160610164021.12"><vh>(d/q '[:find ?e   </vh></v>
<v t="conor.20160610164021.13"><vh>(d/q '[:find [(pull ?e [*]) </vh></v>
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
<t tx="conor.20160605011953.6"></t>
<t tx="conor.20160606053544.1"></t>
<t tx="conor.20160608034752.10">(register-sub
 :node
 (fn [db [_ eid conn]]
   (pull conn '[*] eid)))
</t>
<t tx="conor.20160608034752.3">

(comment 
</t>
<t tx="conor.20160608034752.4">

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
</t>
<t tx="conor.20160608034752.5"></t>
<t tx="conor.20160608034752.6">

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
<t tx="conor.20160608034752.7">
(def lillayouts
  (reaction @(subscribe [:layouts])))
</t>
<t tx="conor.20160608034752.8">(declare render-layout)

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
</t>
<t tx="conor.20160608034752.9">
(register-sub
 :layout
 (fn [db [_ eid conn]]
      (pull conn '[:layout/x :layout/y :layout/height :layout/width :layout/nodeid] eid)))
</t>
<t tx="conor.20160608034753.1">(defn render-layout [eid conn]
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
</t>
<t tx="conor.20160608034753.2">(defn svghickory []
  (let [text (rx/atom "")]
    (fn []
      [:div
      [hickory-printer text]
       [:textarea
        {:style {:border "5px solid red"}
         :on-change #(reset! text (e-value %))}]])))
</t>
<t tx="conor.20160608034753.3">
(defn e-by-av [db a v]
  (-&gt; (db/datoms db :avet a v) first :e))
</t>
<t tx="conor.20160608034753.4">@conn

(e-by-av @conn :layout/y 50)
</t>
<t tx="conor.20160608034753.5">@(q conn '[:find ?e
          :where
          [?e :layout/y 50]])
)
</t>
<t tx="conor.20160610073243.1">


@others
</t>
<t tx="conor.20160610112520.1">http://spootnik.org/entries/2015/03/10_simple-materialized-views-in-kafka-and-clojure.html

Tags: 

spootnik.org %u2013 Simple materialized views in kafka and clojure

Collected: Fri Jun 10 11:25:20 2016

</t>
<t tx="conor.20160610151345.1">@language clojure

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














(register-handler
 :change-text
 (fn [db [_ text]]
   (assoc db :text text)))



(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))


(count-tabs "\t\t")


(defn tvalue [e]
  (-&gt; e
      .-target
      .-value))



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
               :panel-2 [tree-display]]]])


(defn stuff [conn]
  (let [tm (subscribe [:testmap])]
   (fn []
    [:div
     [:button {:on-click #(dispatch [:init conn])} "start"]
;   [canvas conn]
     [demo]
     #_[entity-view conn]
     #_[:h1 (pr-str @tm)]])))



(defn main-panel []
    (fn []
      [stuff conn]))
</t>
<t tx="conor.20160610151933.1">(defn demo []
  [v-box
   :size "auto"
   :gap "10px"
   :children [
              [re-com/h-split
               :panel-1 [tree-text]
               :panel-2 [tree-display]]]])


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
</t>
<t tx="conor.20160610152638.10">(pprint (depthvec-&gt;tree ts))
</t>
<t tx="conor.20160610160233.1"></t>
<t tx="conor.20160610160233.2"></t>
<t tx="conor.20160610162904.1"></t>
<t tx="conor.20160610162909.1">
(transform [ALL TOPSORT (sp/collect-one :node) :a (sp/subset #{})] (fn [x] #{x})  (:tree @app-db)) 
</t>
<t tx="conor.20160610164021.10">

(d/transact! conn [{:db/id [:node/text "Hello Graphs"]
                     :node/test "helllo"}])
</t>
<t tx="conor.20160610164021.11">(d/transact! conn [{:db/id -1
                    :node/text "Node B"
                    :edge/_to [:node/text "Hello Graphs"]}])
</t>
<t tx="conor.20160610164021.12">(d/q '[:find ?e
       :in $
       :where [?e :node/text "Hello Graphs"]]
     @conn)

(d/pull @conn '[*] 3)

(-&gt;
 (d/pull-many @conn '[*] (select [ALL ALL] (d/q '[:find ?e :in $ :where [?e]] @conn)))
 pprint)
</t>
<t tx="conor.20160610164021.13">
(d/q '[:find [(pull ?e [*]) ?e]
             :in $
              :where [?e]]
      @conn)

</t>
<t tx="conor.20160610164021.2">

(declarepath TOPSORT)
(providepath TOPSORT
              (sp/stay-then-continue
               :children ALL TOPSORT))
</t>
<t tx="conor.20160610164021.3">(def CHILDREN (comp-paths :children ALL))

(defn get-leaves [tree]
  (select [ALL TOPSORT :node] tree))
</t>
<t tx="conor.20160610164021.4">(defn get-edges [tree]
  (select [ALL TOPSORT (sp/collect-one :node) CHILDREN :node] tree))


</t>
<t tx="conor.20160610164021.5">(get-edges (:tree @app-db))</t>
<t tx="conor.20160610164021.6">

(defn dbafter-&gt;eid [rv]
  (-&gt; rv
    :tx-data
    ffirst))
</t>
<t tx="conor.20160610164021.7">
(defn create-ds-node [db text]
  (let [eid (d/q '[:find ?e
                    :in $ ?text
                    :where
                    [?e :coll/text ?text]]
                @db
                text)]
      (or (ffirst eid)
        (dbafter-&gt;eid (d/transact! db [{:db/id -1
                                        :coll/text text}])))))
</t>
<t tx="conor.20160610164021.8">

(defn tree-&gt;ds1 [tree]
  (transform [ALL TOPSORT (sp/collect-one :node) :id (sp/subset #{})]
           (comp vector (partial create-ds-node conn))
           tree))





(defn get-edge-ids [tree]
  (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] tree))


(-&gt; (:tree @app-db)
    tree-&gt;ds1
    get-edge-ids
    pprint)


(def testmap (tree-&gt;ds1 (:tree @app-db)))

(d/transact! conn [{:db/id 1
                    :edge/to #{2 3}}])




(defn create-coll [collid children]
  {:db/id collid
   :edge/to children})



(defn merge-vectors [e]
  (-&gt;&gt; (for [[k v] e]
              {k #{v}})
              (apply merge-with clojure.set/union)))



(defn create-edges [treemap]
  (let [e (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] treemap)
        c (select [ALL] (merge-vectors e))]
    (d/transact!  conn (vec (for [[x y] c]
                              (create-coll x y))))))





(def mergable (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] testmap))






</t>
<t tx="conor.20160610164428.1"></t>
<t tx="conor.20160610164523.1"></t>
<t tx="conor.20160610164540.1"></t>
<t tx="conor.20160610170215.1">
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
</t>
<t tx="conor.20160610183437.1">

(defn tree-&gt;ds1 [tree]
  (transform [ALL TOPSORT (sp/collect-one :node) :id (sp/subset #{})]
           (comp vector (partial create-ds-node conn))
           tree))





(defn get-edge-ids [tree]
  (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] tree))


(-&gt; (:tree @app-db)
    tree-&gt;ds1
    get-edge-ids
    pprint)


(def testmap (tree-&gt;ds1 (:tree @app-db)))

(d/transact! conn [{:db/id 1
                    :edge/to #{2 3}}])




(defn create-coll [collid children]
  {:db/id collid
   :edge/to children})



(defn merge-vectors [e]
  (-&gt;&gt; (for [[k v] e]
              {k #{v}})
              (apply merge-with clojure.set/union)))



(defn create-edges [treemap]
  (let [e (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] treemap)
        c (select [ALL] (merge-vectors e))]
    (d/transact!  conn (vec (for [[x y] c]
                              (create-coll x y))))))





(def mergable (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] testmap))






</t>
<t tx="conor.20160610183452.1"></t>
</tnodes>
</leo_file>
