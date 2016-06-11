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
<v t="conor.20160605013539.1"><vh>@auto specter.org</vh></v>
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
<v t="conor.20160611204221.1" a="E"><vh>@clean transforms.cljs</vh>
<v t="conor.20160608034748.1" a="E"><vh>Original Text-&gt;Tree</vh>
<v t="conor.20160610003346.1"><vh>count-tabs</vh></v>
<v t="conor.20160610003314.1"><vh>parsed -- split + count tabs</vh></v>
<v t="conor.20160608034749.2"><vh>(defn nodify [nseq]  (loop </vh></v>
</v>
<v t="conor.20160610152638.1"><vh>Text-&gt;Tree</vh>
<v t="conor.20160610152638.2"><vh>specs</vh></v>
<v t="conor.20160610152638.4"><vh>depthvec-&gt;graph</vh></v>
<v t="conor.20160610152638.5"><vh>depthvec-&gt;tree</vh>
<v t="conor.20160610152638.6"><vh>helpers</vh>
<v t="conor.20160610152638.7"><vh>(defn create-node  [title]</vh></v>
<v t="conor.20160610152638.8"><vh>(defn connect-node [node children]</vh></v>
</v>
<v t="conor.20160610152638.9"><vh>(def depthvec-&gt;tree  (partial depthvec-&gt;graph</vh></v>
</v>
</v>
<v t="conor.20160610073243.1" a="E"><vh>Tree-&gt;DS</vh>
<v t="conor.20160610164021.2"><vh>TOPSORT</vh></v>
<v t="conor.20160610164021.3"><vh>CHILDREN</vh></v>
<v t="conor.20160610164021.4"><vh>(defn get-edges [tree]  (select </vh></v>
<v t="conor.20160610164021.6"><vh>dbafter-&gt;eid </vh></v>
<v t="conor.20160610164021.7"><vh>(defn create-ds-node [db text]  </vh></v>
<v t="conor.20160610164021.8" a="E"><vh>(defn tree-&gt;ds1 [tree]  (transform </vh>
<v t="conor.20160611211018.1"><vh>tree-&gt;ds1</vh></v>
<v t="conor.20160611211018.2"><vh>#_(s/fdef create-colls    </vh></v>
<v t="conor.20160611211018.4"><vh>#_(-&gt;&gt;  (transform [(sp/subselect ALL </vh></v>
<v t="conor.20160611211018.6"><vh>(defn plainent [conn ids]  </vh>
<v t="conor.20160611211018.7"><vh>(s/fdef plainent    </vh></v>
<v t="conor.20160611211018.8"><vh>(s/instrument #'plainent) </vh></v>
</v>
<v t="conor.20160611211018.10"><vh>tree-&gt;ds</vh></v>
<v t="conor.20160611211019.1"><vh>tree-&gt;ds3</vh></v>
<v t="conor.20160611211019.5"><vh>(defn get-edge-ids [tree]  (select </vh></v>
<v t="conor.20160611211019.7"><vh>(defn create-coll [collid children]  </vh></v>
<v t="conor.20160611211019.8"><vh>(defn merge-vectors [e]  (-&gt;&gt; </vh></v>
<v t="conor.20160611211019.9"><vh>(defn create-edges [conn treemap]  </vh></v>
<v t="conor.20160611211019.13"><vh>#_(def mergable (select [ALL TOPSORT </vh></v>
</v>
<v t="conor.20160610164021.12"><vh>All Entites</vh></v>
</v>
</v>
<v t="conor.20160610151345.1"><vh>@clean views.cljs</vh></v>
<v t="conor.20160610082638.1"><vh>@file specs.cljs</vh></v>
<v t="conor.20160605011953.5"><vh>@auto core.cljs</vh></v>
<v t="conor.20160605011953.4" a="E"><vh>@file db.cljs</vh></v>
<v t="conor.20160605011953.3" a="E"><vh>@file handlers.cljs</vh></v>
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
<v t="conor.20160610162904.1" a="E"><vh>specter lessons</vh>
<v t="conor.20160611211442.1"><vh>#_(setval [(sp/subselect ALL map?) (sp/subset </vh></v>
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
<v t="conor.20160610073243.1" a="E"></v>
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
<t tx="conor.20160606053544.1"></t>
<t tx="conor.20160608034748.1">@language clojure
</t>
<t tx="conor.20160608034749.2">(defn nodify [nseq]
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


(-&gt;&gt; (str/split "this\n\tis\n\n\t\tmy baby" #"\n")
     (filter #(not (empty? %)))
     pprint)


</t>
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
<t tx="conor.20160610003314.1">

(defn parsed [text]
    (-&gt;&gt; (str/split text #"\n")
         (map (juxt count-tabs str/trim))))

(defn parsed2 [text]
  (-&gt;&gt; (str/split text #"\n")
       (map-indexed (juxt (fn [i x] (count-tabs x))
                          (fn [i x] [i (str/trim x)])))))



</t>
<t tx="conor.20160610003346.1">
(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))


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





(defn tvalue [e]
  (-&gt; e
      .-target
      .-value))




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
     [:button {:on-click #(dispatch [:tree-&gt;ds conn])} "Convert"]
;   [canvas conn]
     [demo]
     [entity-view conn]
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
<t tx="conor.20160610152638.1"></t>
<t tx="conor.20160610152638.10">(pprint (depthvec-&gt;tree ts))
</t>
<t tx="conor.20160610152638.2">(s/def ::function fn?)

(s/def ::depthvec
  (s/cat :depth integer?
         :text  (s/or
                  :s string?
                  :k keyword?)))

(s/def ::depthvecs (s/+ ::depthvec))

(s/fdef transform-depthvec
        :args ::s/any
          #_(s/cat :nodefn ::function
                     :edgefn ::function
                     :sibling-collector ::function
                     :nseq  (s/+
                              ::depthvec))
        :ret ::s/any)



</t>
<t tx="conor.20160610152638.4">(defn transform-depthvec [nodefn edgefn sibling-collector nseq]
  (loop [result []
         s nseq]
    (let[[pdepth ptitle] (first s)
         [children siblings] (split-with #(&lt; pdepth (first %)) (rest s))
         answer   (nodefn ptitle)
         answer
         (if (seq children)
           (edgefn answer (transform-depthvec nodefn edgefn sibling-collector children))
           answer)]
      (if (seq siblings)
        (recur (sibling-collector result answer) siblings)
        (sibling-collector result answer)))))


(s/instrument #'transform-depthvec)

</t>
<t tx="conor.20160610152638.5"></t>
<t tx="conor.20160610152638.6"></t>
<t tx="conor.20160610152638.7">(defn create-node-map
  [title]
  {:node title})
</t>
<t tx="conor.20160610152638.8">(defn connect-node [node children]
   (assoc node :children children :expanded true))




</t>
<t tx="conor.20160610152638.9">(def depthvec-&gt;tree
  (partial transform-depthvec create-node-map connect-node conj))



</t>
<t tx="conor.20160610160233.1"></t>
<t tx="conor.20160610160233.2"></t>
<t tx="conor.20160610162904.1"></t>
<t tx="conor.20160610162909.1">
(transform [ALL TOPSORT (sp/collect-one :node) :a (sp/subset #{})] (fn [x] #{x})  (:tree @app-db)) 
</t>
<t tx="conor.20160610164021.12">

(defn all-ents [conn]
    (-&gt; (d/pull-many @conn '[*] (select [ALL ALL] (d/q '[:find ?e :in $ :where [?e]] @conn)))
    pprint)
</t>
<t tx="conor.20160610164021.2">(declarepath TOPSORT)
(providepath TOPSORT
              (sp/stay-then-continue
               :children ALL TOPSORT))
</t>
<t tx="conor.20160610164021.3">(def CHILDREN (comp-paths :children ALL))

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


#_(s/fdef create-colls
        :args (s/coll-of string? [])
        :ret  (s/coll-of integer? []))



#_(setval [(sp/subselect ALL map?) (sp/subset :new-val)]
        1 [{:a 1}[:not :me 1]{:b 2}])


#_(-&gt;&gt;  (transform [(sp/subselect ALL TOPSORT :id) (sp/view count)]
                 range
                 (:tree @app-db))
      (select [ALL TOPSORT :id])
      pprint)

(defn atom? [a] (instance? cljs.core/Atom a))





(defn plainent [conn ids]
  (let [ents (vec (for [[i t] ids] {:db/id i
                                     :coll/text  t}))]
    (-&gt;&gt; (d/transact! conn ents)
         :tempids)))


(s/fdef plainent
        :args (s/cat :db atom?
                     :entvecs (s/coll-of  (s/spec
                                           (s/cat
                                            :id integer?
                                            :text string?))
                                          [])))



(s/instrument #'plainent)



#_(pprint (:tree @app-db))



(defn tree-&gt;ds [conn tree]
  (let [;tree (:tree @app-db)
        indexed-tree  (-&gt;&gt;  (transform [(sp/subselect ALL TOPSORT :id)]
                                       (partial map-indexed (fn [i x] (- 0 (inc i))))
                                       tree))
        idmap (-&gt;&gt; (select [ALL TOPSORT (sp/multi-path :id :node)] indexed-tree)
                   (partition 2)
                   (map vec)
                   vec
                   (plainent conn))]
    (transform [ALL TOPSORT :id] idmap indexed-tree)))


#_(pprint (select [ALL TOPSORT (sp/collect-one :id):node] indexed-tree))

(defn tree-&gt;ds3 [conn tree]
  (let [indexed-tree  (-&gt;&gt;  (transform [(sp/subselect ALL TOPSORT :id)]
                                       (partial map-indexed (fn [i x] (- 0 (inc i))))
                                       tree))
        idmap (-&gt;&gt; (select [ALL TOPSORT (sp/collect-one :id) :node] indexed-tree)
                   (plainent conn))]
    (transform [ALL TOPSORT :id] idmap indexed-tree)))



#_(transform [(sp/subselect ALL ALL)] reverse [#{1 2 3} [4 5 6]])

    ;     vec
    ;     (d/transact! conn)
    ;     :tempids
    ;     vals
    ;     drop-last]))


#_(defn tree-&gt;ds2 [tree]
    (transform [ALL TOPSORT (sp/collect-one :node) :id (sp/subset #{})]
               create-colls
               tree))



#_(pprint (tree-&gt;ds2 create-colls))



(defn get-edge-ids [tree]
  (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] tree))



(comment
  (def testmap (tree-&gt;ds1 (:tree @app-db)))

  (d/transact! conn [{:db/id 1}]
                    :edge/to #{2 3}))




(defn create-coll [collid children]
                  {:db/id collid
                   :edge/to children})



(defn merge-vectors [e]
  (-&gt;&gt; (for [[k v] e]
            {k #{v}})
       (apply merge-with clojure.set/union)))



(defn create-edges [conn treemap]
  (let [e (select [ALL TOPSORT (sp/collect-one :id) CHILDREN :id] treemap)
        c (select [ALL] (merge-vectors e))]
    (d/transact!  conn (vec (for [[x y] c]
                              (create-coll x y))))))





(-&gt;&gt; (:tree @app-db)
    (tree-&gt;ds conn)
    (create-edges conn)
    pprint)







#_(def mergable (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] (:tree @app-db)))






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
<t tx="conor.20160611204221.1">@language clojure
(ns text-nodes.transforms
  (:require [reagent.core    :as r]
            [text-nodes.db :refer [conn]]
            [text-nodes.specs :as mys]
            [cljs.spec        :as s]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-handler]]
            [re-frame.db     :refer [app-db]]
            [datascript.core :as d]
            [cljs.pprint     :refer [pprint]]
            [cljs.reader]
            [com.rpl.specter  :refer [ALL STAY LAST stay-then-continue collect-one comp-paths] :as sp]
            [clojure.string  :as str])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform declarepath providepath]]
           [reagent.ratom :refer [reaction]]))


@others
</t>
<t tx="conor.20160611211018.1">

(defn tree-&gt;ds1 [tree]
  (transform [ALL TOPSORT (sp/collect-one :node) :id (sp/subset #{})]
           (comp vector (partial create-ds-node conn))
           tree))</t>
<t tx="conor.20160611211018.10">
(defn tree-&gt;ds [conn tree]
  (let [;tree (:tree @app-db)
        indexed-tree  (-&gt;&gt;  (transform [(sp/subselect ALL TOPSORT :id)]
                                       (partial map-indexed (fn [i x] (- 0 (inc i))))
                                       tree))
        idmap (-&gt;&gt; (select [ALL TOPSORT (sp/multi-path :id :node)] indexed-tree)
                   (partition 2)
                   (map vec)
                   vec
                   (plainent conn))]
    (transform [ALL TOPSORT :id] idmap indexed-tree)))</t>
<t tx="conor.20160611211018.2">#_(s/fdef create-colls
        :args (s/coll-of string? [])
        :ret  (s/coll-of integer? []))</t>
<t tx="conor.20160611211018.4">#_(-&gt;&gt;  (transform [(sp/subselect ALL TOPSORT :id) (sp/view count)]
                 range
                 (:tree @app-db))
      (select [ALL TOPSORT :id])
      pprint)

(defn atom? [a] (instance? cljs.core/Atom a))</t>
<t tx="conor.20160611211018.6">(defn plainent [conn ids]
  (let [ents (vec (for [[i t] ids] {:db/id i
                                     :coll/text  t}))]
    (-&gt;&gt; (d/transact! conn ents)
         :tempids)))</t>
<t tx="conor.20160611211018.7">(s/fdef plainent
        :args (s/cat :db atom?
                     :entvecs (s/coll-of  (s/spec
                                           (s/cat
                                            :id integer?
                                            :text string?))
                                          [])))</t>
<t tx="conor.20160611211018.8">
(s/instrument #'plainent)</t>
<t tx="conor.20160611211019.1">(defn tree-&gt;ds3 [conn tree]
  (let [indexed-tree  (-&gt;&gt;  (transform [(sp/subselect ALL TOPSORT :id)]
                                       (partial map-indexed (fn [i x] (- 0 (inc i))))
                                       tree))
        idmap (-&gt;&gt; (select [ALL TOPSORT (sp/collect-one :id) :node] indexed-tree)
                   (plainent conn))]
    (transform [ALL TOPSORT :id] idmap indexed-tree)))</t>
<t tx="conor.20160611211019.13">

#_(def mergable (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] (:tree @app-db)))</t>
<t tx="conor.20160611211019.5">
(defn get-edge-ids [tree]
  (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] tree))</t>
<t tx="conor.20160611211019.7">

(defn create-coll [collid children]
                  {:db/id collid
                   :edge/to children})</t>
<t tx="conor.20160611211019.8">
(defn merge-vectors [e]
  (-&gt;&gt; (for [[k v] e]
            {k #{v}})
       (apply merge-with clojure.set/union)))</t>
<t tx="conor.20160611211019.9">
(defn create-edges [conn treemap]
  (let [e (select [ALL TOPSORT (sp/collect-one :id) CHILDREN :id] treemap)
        c (select [ALL] (merge-vectors e))]
    (d/transact!  conn (vec (for [[x y] c]
                              (create-coll x y))))))</t>
<t tx="conor.20160611211442.1">
#_(setval [(sp/subselect ALL map?) (sp/subset :new-val)]
        1 [{:a 1}[:not :me 1]{:b 2}])</t>
</tnodes>
</leo_file>
