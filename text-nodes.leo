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
<v t="conor.20160605011037.1" a="E"><vh>@path ~/orgmap/leo/text-nodes</vh>
<v t="conor.20160605011510.1"><vh>@auto log.org</vh></v>
<v t="conor.20160605013539.1" a="E"><vh>@auto specter.org</vh></v>
</v>
<v t="conor.20160605013848.1"><vh>@auto datascript.org</vh></v>
<v t="conor.20160605010926.1" a="E"><vh>@path src/</vh>
<v t="conor.20160605011023.2" a="E"><vh>/clj/</vh>
<v t="conor.20160610142118.2" a="E"><vh>@file core.clj</vh></v>
<v t="conor.20160610142118.1" a="E"><vh>@file spec-test.clj</vh></v>
<v t="conor.20160605011025.1" a="E"><vh>/text_nodes/</vh>
<v t="conor.20160606161843.1"
expanded="conor.20160608031859.1,"><vh>@file specter-test.clj</vh></v>
<v t="conor.20160606052632.1"
expanded="conor.20160606073225.1,"><vh>@file spec-test.clj</vh></v>
<v t="conor.20160605011026.1"><vh>css.clj</vh></v>
</v>
</v>
<v t="conor.20160605011023.1" a="E"><vh>/cljs/</vh>
<v t="conor.20160605011952.1" a="E"><vh>/text_nodes/</vh>
<v t="conor.20160610082638.1" a="E"><vh>@file specs.cljs</vh></v>
<v t="conor.20160605011953.6"><vh>config.cljs</vh></v>
<v t="conor.20160605011953.5"><vh>@auto core.cljs</vh></v>
<v t="conor.20160605011953.4" a="E"><vh>@file db.cljs</vh></v>
<v t="conor.20160605011953.3" a="E"><vh>@file handlers.cljs</vh></v>
<v t="conor.20160605011953.2" a="E"><vh>@file subs.cljs</vh></v>
<v t="conor.20160605011953.1"><vh>@file views.cljs</vh></v>
</v>
</v>
</v>
</v>
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
</vnodes>
<tnodes>
<t tx="conor.20160605010926.1"></t>
<t tx="conor.20160605011023.1">@path cljs</t>
<t tx="conor.20160605011023.2">@path clj</t>
<t tx="conor.20160605011025.1">@path text_nodes</t>
<t tx="conor.20160605011026.1"></t>
<t tx="conor.20160605011037.1"></t>
<t tx="conor.20160605011952.1">@path text_nodes</t>
<t tx="conor.20160605011953.1">@language clojure
@others

  

       
</t>
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
<t tx="conor.20160610112520.1">http://spootnik.org/entries/2015/03/10_simple-materialized-views-in-kafka-and-clojure.html

Tags: 

spootnik.org %u2013 Simple materialized views in kafka and clojure

Collected: Fri Jun 10 11:25:20 2016

</t>
</tnodes>
</leo_file>
