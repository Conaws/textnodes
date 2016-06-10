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
         :only [select transform defprotocolpath setval declarepath providepath defnav
                extend-protocolpath]]))
;@+node:conor.20160606062941.1: ** sampletext
(def sampletext "This is the first goal\n\tThis is it's first child\n\t\t@person Conor @role superhero\nThis is another goal\n\tThis is another child 1\n\tThis is another child 1 again")


;@+node:conor.20160606073225.2: ** (defn count-tabs  [string]  
(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))

;@+node:conor.20160608033808.1: ** (defn parsed [text]  (->> 


(defn parsed [text]
  (->> (str/split text #"\n")
       (map (juxt count-tabs str/trim))))


(def t (parsed sampletext))

;@+node:conor.20160608031859.1: ** deptharray-stuff
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


(select-edges samplemap)


(select (sp/walker #(:node %)) samplemap)


(pprint (deptharray->graph str vector conj (parsed sampletext)))



;@+node:conor.20160609111939.1: *3* Node and Edges


(declarepath TOPSORT)

(providepath TOPSORT
             (sp/stay-then-continue
              :children ALL TOPSORT))



(setval :a  1 {:b 2})


(pprint
(setval [ALL TOPSORT :open] true  samplemap))


(def CHILDREN (sp/comp-paths :children ALL))


(comment

(defn node-path [name]
   (fn [n] (= (:node n) name)))



(select [ALL TOPSORT (collect-one :node) CHILDREN :node] samplemap)


(transform [ALL (sp/continue-then-stay :children ALL (collect-one :node)) :parents] 
         (fn [& xs] (vector xs))   samplemap)


)




(declarepath NODE)

(providepath NODE 
             (sp/multi-path 
              [:children ALL NODE]
              :node))

(pprint (select [ALL NODE] samplemap))



(declarepath EDGES)

(providepath EDGES
             (sp/multi-path
              [(sp/collect-one :node) :children ALL :node]
              (sp/if-path [:children ALL :node]
                          [:children ALL EDGES])))

(pprint (select [ALL EDGES] samplemap))



(defnav MERGED-MAPS []
  (select* [this structure next-fn]
    ;;TODO: fill this in
    )
  (transform* [this structure next-fn]
    (apply merge-with (fn [& vals] (next-fn vals)) structure)
    ))


(defn sum [vals] (reduce + vals))



(def data
 {:1000 {:a {:sends 1}}
  :2000 {:a {:clicks 1 :opens 1 :sends 1}}
  :3000 {:b {:sends 1 :opens 1}}})

(transform [MERGED-MAPS MERGED-MAPS] sum (vals data))



;; given a vector of vectors, reverse all numbers without changing the length of any vector


(transform [(sp/subselect ALL ALL)] reverse [[1 2 3] [4 5] [6] [7] [8 9 10]])


(s/def ::stringvec (s/+ (s/spec (s/+  string?))))



(defn joinup [stringvec]
  ([(apply str stringvec)]))


(s/fdef joinup :args ::stringvec
        :ret string?)

(s/instrument 'joinup)

(transform [(sp/continuous-subseqs string?)] (fn [strseq] [(apply str strseq)])
            ["hello " "a " "w" 1 2 3 "a " "b" ])



(setval [:a (sp/subset #{})] #{1} {:b 1})


(setval [ALL #(< 10 (:a %)) :b] true [{:a 3} {:a 5} {:a 11}])


(setval [ALL (sp/if-path [:a #(< 10 %)] :b)] true [{:a 3} {:a 5} {:a 11}])







;@-others
;@-leo
