;@+leo-ver=5-thin
;@+node:conor.20160606161843.1: * @file specter-test.clj
;@+others
;@+node:conor.20160606161918.1: ** namespace
(ns text-nodes.core
  (:require [com.rpl.specter  :as sp :refer [ALL FIRST LAST STAY collect-one putval VAL]]
                [clojure.spec        :as s]
                [clojure.string      :as str]
                [clojure.pprint       :refer [pprint]]
                [datascript.core    :as db])
  (:use 
   [com.rpl.specter.macros 
         :only [select transform defprotocolpath setval declarepath providepath
                extend-protocolpath]]))
;@+node:conor.20160606062941.1: ** sampletext
(def sampletext "This is the first goal\n\tThis is it's first child\n\t\t@person Conor @role superhero\nThis is another goal\n\tThis is another child 1\n\tThis is another child 1 again")


;@+node:conor.20160606073225.2: ** (defn count-tabs  [string]  
(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))
  
;@+node:conor.20160606162219.1: ** keypaths
;@+node:conor.20160606162227.1: *3* misisng value
#_(transform [ALL :a even?] inc
    [{:a 1}{:b 2}{:a 2}{:a 4}])
    
;; this will return an error, since even? needs an integer to work on


(transform [ALL :a even?] inc [{:a 1}{:a 2}])
;; will return  [{:a 1}{:a 3}]

;@+node:conor.20160606163609.1: ** all all
(select [ALL ALL ALL #(= 0 (mod % 3))]
   [[[]] [[0 2 3 4 12]][][1 2 3]])
;@+node:conor.20160606170827.1: ** walker
(select (sp/walker number?) 
             {1 [2 3 [4 5]
              :b 6
              :c {:a 7
                    :d [8 9}})
;@+node:conor.20160606171224.1: *3* walker map
;;only first level

(select (sp/walker #(:a %))
    {:a [{:b 1 :a {:c 2 :a {:a [1 2 3]}}}] :c {:a 5}})


;;second level too

(select [(sp/walker #(:a %)) ALL (sp/walker :a)]
    [{:b 1 :a {:c 2 :a {:a [1 2 3]}}} 
     {:c {:a 5}}])
;@+node:conor.20160608031859.1: ** deptharray-stuff
;@+node:conor.20160608033808.1: *3* (defn parsed [text]  (->> 


(defn parsed [text]
  (->> (str/split text #"\n")
       (map (juxt count-tabs str/trim))))


(pprint (first (parsed sampletext)))

;@+node:conor.20160608031845.1: *3* deptharray->graph

(defn deptharray->graph [nodefn edgefn sibling-collector nseq]
  (loop [result [] 
         s nseq]
    (let[[pdepth ptitle] (first s)
         [children siblings] (split-with #(< pdepth (first %)) (rest s))
         answer   (nodefn ptitle)                     
         answer
         (if (seq children)
           (edgefn answer (deptharray->graph nodefn edgefn sibling-collector children))
           answer)]
      (if (seq siblings)
        (recur (sibling-collector result answer) siblings)
        (sibling-collector result answer)))))




(defn create-node 
  [title]
   {:node title})

(s/fdef create-node
        :args (s/cat :x string?)
        :ret  map?)


(s/instrument #'create-node)

  
(defn connect-node [node children]
   (assoc node :children children :expanded true))



(def deptharray->nestedmap 
  (partial deptharray->graph create-node connect-node conj))


(def samparray (parsed sampletext))


(def sampmap (deptharray->nestedmap samparray))



(select [ALL (sp/collect-one :node) :children ALL :node] sampmap)



(def samplemap [{:node :a :children 
                 [{:node :aa :children 
                   [{:node :aaa :children 
                     [{:node :aaaa}]}]} 
                  {:node :ab :children
                   [{:node :aba}]}]}
                {:node :b}])

;@+others
;@+node:conor.20160608060237.1: *4* newHeadline


(declarepath  REMAP)

(s/def ::node-name (s/or :s string?
                         :k keyword?))


(s/def ::node-edges (s/* ::node))

(s/def ::node (s/keys 
               ::req [::node-name]
               ::opts [::node-edges]))

(providepath REMAP
             (sp/if-path #(s/valid? ::node  %)
                      [:children ALL KIDS]
                      sp/STAY))




(declarepath KIDS)









(declarepath TREE)

(providepath TREE
             (sp/if-path vector?
                         [ALL TREE]
                         STAY))


(select [TREE number?] [1 1 2 [3 [[][][4 5]]]])


(declarepath MAPTREE)

(providepath MAPTREE 
             (sp/if-path map?
                         [ALL ALL MAPTREE]
                         STAY))



(providepath KIDS 
             (sp/multi-path 
              :children
              [:children ALL KIDS]
              :node))

(pprint (select [ALL KIDS keyword?] samplemap))



(declarepath KID-EDGES)

(providepath KID-EDGES
             (sp/multi-path
              [:children ALL :node]
              [(sp/collect :node) :children ALL KID-EDGES]
              [:children nil?]
              :node))

(pprint (select [ALL KID-EDGES] samplemap))








(pprint samplemap)



;@-others

(defn select-edges [maparray]
   (loop [results [] current-level maparray]
     (let [edges (select 
                  [ALL (sp/collect-one :node) :children ALL :node] 
                  current-level)
           r (apply conj results edges)
           next-gen (select 
                     [ALL :children (sp/selected? :children) ALL] 
                     current-level)]
       (if (empty? next-gen)
         r
         (recur r next-gen)))))


(select-edges samplemap)


(select (sp/walker #(:node %)) samplemap)


(pprint (deptharray->graph str vector conj (parsed sampletext)))



;;; incs all the depths
(transform [ALL sp/FIRST] inc (parsed sampletext))

;; get the depth of the last node and add it to all the node depths

(transform [(sp/collect-one sp/LAST sp/FIRST) ALL sp/FIRST] + (parsed sampletext))



(def FIRSTDEPTH (sp/comp-paths (sp/collect-one sp/LAST sp/FIRST)))


(setval [FIRSTDEPTH ALL sp/END] (parsed sampletext))





;;; test to try out -- rather than associng the child-node -- return an vector that is justthe pairs
;@+node:conor.20160608034218.1: *3* new workspace









;@+node:conor.20160608031807.1: *3* vals-between

(defn vals-between [resetfn s]
  (->> s
       (reduce (fn [{c :c r :r :as m} x]
                 (if (resetfn x)
                   (assoc m :c [x] :r (if (empty? c)
                                        r
                                        (conj r c)))
                   (assoc m :c (conj c x))))
               {:c [] :r []})
       ((fn [{c :c r :r}] (conj r c)))))


;@+node:conor.20160608032047.1: *3* tim's approach
(defn nodify3 [rows]
  ;; find the entities
  (let [entities (into {}
                       (for [[idx _ text] rows]
                         [idx text]))
        ;; find the relationships
        relations (into {}
                        (for [[idx indent] rows]
                          [idx
                           (map (comp - first)
                                (take-while
                                  #(= (inc indent) (second %))
                                  (drop (inc idx) rows)))]))
        total (+ (count relations) (count entities))]
    (apply
      concat
      (for [[idx text] entities
            :let [children (relations idx)]]
        (cons {:node/text text
               :node/children (map - children (repeat total))
               :db/id (- idx)}
              (map-indexed
                (fn [idx2 cidx]
                  {:db/id (- cidx total)
                   :edge/order idx2
                   :edge/to cidx})
                children))))))
;@-others
;@-leo
