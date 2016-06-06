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
<v t="conor.20160606053544.1" a="E"><vh>Files to write</vh>
<v t="conor.20160605011037.1"><vh>@path ~/orgmap/leo/text-nodes</vh>
<v t="conor.20160605011510.1"><vh>@auto log.org</vh></v>
<v t="conor.20160605013539.1"><vh>@auto specter.org</vh></v>
</v>
<v t="conor.20160605013848.1"><vh>@auto datascript.org</vh></v>
<v t="conor.20160605010926.1" a="E"><vh>@path src/</vh>
<v t="conor.20160605011023.2" a="EM"><vh>/clj/</vh>
<v t="conor.20160605011025.1" a="EM"><vh>/text_nodes/</vh>
<v t="conor.20160606052632.1" a="E"
expanded="conor.20160606053224.1,conor.20160606062933.1,conor.20160606063153.1,"><vh>@file spec-test.clj</vh></v>
<v t="conor.20160605012522.1" a="M"><vh>@auto core.clj</vh>
<v t="conor.20160605013418.1"><vh>namespace -- datascript and specter</vh></v>
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
<v t="conor.20160605011953.1" a="M"><vh>@auto views.cljs</vh></v>
</v>
</v>
</v>
</v>
<v t="conor.20160606044649.1" a="E"><vh>Mon Jun  6 </vh>
<v t="conor.20160606053804.1"><vh>break</vh></v>
<v t="conor.20160606055117.1"><vh>Discovery  05:55:43</vh>
<v t="conor.20160606055120.1"><vh>Working with Leo and Emacs</vh>
<v t="conor.20160606055158.1"><vh>changing a file in emacs will collapse leo heirarch</vh></v>
</v>
</v>
<v t="conor.20160606055555.1"><vh>create timestamp  --05:57:57</vh></v>
<v t="conor.20160606055940.1"><vh>break 05:59:42--06:35:58</vh></v>
<v t="conor.20160606063559.1"><vh>splitting strings 06:36:09--</vh>
<v t="conor.20160606063703.1"><vh>tangent -- processing cljs files in leo</vh></v>
</v>
</v>
<v t="conor.20160606053224.1" a="E"><vh>spec-test</vh>
<v t="conor.20160605013418.1"></v>
<v t="conor.20160606062933.1" a="E"><vh>string spec</vh>
<v t="conor.20160606062941.1"><vh>sampletext</vh></v>
<v t="conor.20160606063153.1" a="E"><vh>splitting the string with types</vh>
<v t="conor.20160606064205.1"><vh>(defn count-tabs  [string]  </vh></v>
<v t="conor.20160606064205.2"><vh>(defn parsed [text]  (-&gt;&gt; </vh></v>
<v t="conor.20160606064219.1"><vh>(parsed sampletext)</vh></v>
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
<t tx="conor.20160605011953.6"></t>
<t tx="conor.20160605012522.1">@language clojure

(ns text-nodes.core
  (:require [com.rpl.specter :as s
             :refer [ALL]]
            [clojure.pprint :refer [pprint]]
            [datascript.core  :as db])
  (:use [com.rpl.specter.macros 
         :only [select transform defprotocolpath
                extend-protocolpath]]))

(defprotocolpath TreeWalker [])



(extend-protocolpath TreeWalker
  Object nil
  clojure.lang.PersistentVector [s/ALL TreeWalker])



(select [TreeWalker number?] [:a 1 [2 [[[3]]] :e] [4 5 [6 7]]])


(select [TreeWalker :a] 
           
[:a 1 [2 [[[3 {:a "b"}]]] :e] [4 {:a '({} {:b {:c :d}})} 5 [6 7]]])




(def test-struct [[0 :a] [1 :b] [2 :c] [0 :d] [1 :e] [1 :f]])






(defn deptharray-&gt;graph [nodefn edgefn sibling-collector nseq]
  (loop [result [] 
         s nseq]
    (let[[pdepth ptitle] (first s)
         [children siblings] (split-with #(&lt; pdepth (first %)) (rest s))
         answer   (nodefn ptitle)                     
         answer
         (if (seq children)
           (edgefn answer (nodify nodefn edgefn children))
           answer)]
      (if (seq siblings)
        (recur (sibling-collector result answer) siblings)
        (sibling-collector result answer)))))


;;  the fns
;;  nodefn  takes a node's text, returns a nodeid (or the node itself)
;;  edgefn  takes a nodeid and an index of childid, returns a nodeid (or node object)
;;  siblingfn  takes  the current siblings of a node, and the node, returns a vector of all x at that level

;; challenge, create the associations with order to them...









(defn create-node 
  [title]
   {:node title})

  
(defn connect-node [node children]
   (assoc node :children children :expanded true))




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


(keys rv)

;test test

@fake-db
</t>
<t tx="conor.20160605013418.1">(ns text-nodes.core
  (:require [com.rpl.specter  :as sp :refer [ALL]]
                [clojure.spec        :as s]
                [clojure.string      :as str]
                [clojure.pprint       :refer [pprint]]
                [datascript.core    :as db])
  (:use 
   [com.rpl.specter.macros 
         :only [select transform defprotocolpath
                extend-protocolpath]]))
</t>
<t tx="conor.20160606044649.1">04:46:54 2016

adding in new clojure
currently figwheel is working fine

Mon Jun  6 04:35:04 2016
shower

Mon Jun  6 05:00:52 2016  
realized that won't be able to use spec in cljs
instead will pull down learnspecter and work off that project first

Mon Jun  6 05:15:15 2016
Alex did not leave me with a version that works

Mon Jun  6 05:24:02 2016
back to text nodes

</t>
<t tx="conor.20160606053224.1"></t>
<t tx="conor.20160606053544.1"></t>
<t tx="conor.20160606053804.1">start --  Mon Jun  6 05:38:14 2016

end -- Mon Jun  6 05:51:13 2016

start</t>
<t tx="conor.20160606055117.1"></t>
<t tx="conor.20160606055120.1"></t>
<t tx="conor.20160606055158.1">fixed this via getting rid of the hook for save-all on leave focus within my emacs

its currently commented out

</t>
<t tx="conor.20160606055555.1">In textexpander

%H:%M:%S
for time;;
</t>
<t tx="conor.20160606055940.1"></t>
<t tx="conor.20160606062933.1"></t>
<t tx="conor.20160606062941.1">(def sampletext "This is the first goal\n\tThis is it's first child\n\t\t:assigned-to Conor")
</t>
<t tx="conor.20160606063153.1">@others</t>
<t tx="conor.20160606063559.1"></t>
<t tx="conor.20160606063703.1">
note -- working with @file's in leo is way nicer than alternatives

seeing the sentinals is actually pretty great -- at least within the clj files

need to find a way to do that
</t>
<t tx="conor.20160606064205.1">(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))
  </t>
<t tx="conor.20160606064205.2">
(defn parsed [text]
  (-&gt;&gt; (str/split text #"\n")
       (map (juxt count-tabs str/trim))))

</t>
<t tx="conor.20160606064219.1">(parsed sampletext)


</t>
</tnodes>
</leo_file>
