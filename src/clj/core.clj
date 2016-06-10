;@+leo-ver=5-thin
;@+node:conor.20160610142118.2: * @file core.clj
;@@language clojure

(ns text-nodes.core
  (:require [com.rpl.specter  :as sp :refer [ALL]]
            [clojure.spec        :as s]
            [clojure.string      :as str]
            [clojure.spec.gen :as gen]
            [clojure.pprint       :refer [pprint]]
            [datascript.core    :as db])
  (:use
   [com.rpl.specter.macros
         :only [select transform defprotocolpath
                extend-protocolpath]]))

;@+others
;@+node:conor.20160610142317.2: ** (def ts [[0 :a] [1 
(def ts [[0 :a] [1 :b] [2 :c] [0 :d] [1 :e] [2 :b] [1 :f]])


;@+node:conor.20160610142317.3: ** (s/def ::function clojure.test/function?)
(s/def ::function clojure.test/function?)

(s/def ::depthvec
  (s/cat :depth integer?
         :text  (s/or
                  :s string?
                  :k keyword?)))

(s/def ::depthvecs (s/+ ::depthvec))

(gen/sample (s/gen ::depthvec))

(s/fdef depthvec->graph
        :args ::s/any
          #_(s/cat :nodefn ::function
                     :edgefn ::function
                     :sibling-collector ::function
                     :nseq  (s/+
                              ::depthvec))
        :ret ::s/any)



;@+node:conor.20160610142317.4: ** (s/explain ::depthvec ts) 
(s/explain ::depthvec ts)



;@+node:conor.20160610142318.1: ** (defn depthvec->graph [nodefn edgefn sibling-collector 
(defn depthvec->graph [nodefn edgefn sibling-collector nseq]
  (loop [result []
         s nseq]
    (let[[pdepth ptitle] (first s)
         [children siblings] (split-with #(< pdepth (first %)) (rest s))
         answer   (nodefn ptitle)
         answer
         (if (seq children)
           (edgefn answer (depthvec->graph nodefn edgefn children))
           answer)]
      (if (seq siblings)
        (recur (sibling-collector result answer) siblings)
        (sibling-collector result answer)))))



;@+node:conor.20160610142318.2: ** (s/instrument #'depthvec->graph) 

(s/instrument #'depthvec->graph)
;@+node:conor.20160610142318.3: ** (defn create-node  [title]  
(defn create-node
  [title]
  {:node title})
;@+node:conor.20160610142318.4: ** (defn connect-node [node children]  
(defn connect-node [node children]
   (assoc node :children children :expanded true))




;@+node:conor.20160610142318.5: ** (depthvec->graph identity vector conj test-struct) 


(depthvec->graph identity vector conj test-struct)



;@+node:conor.20160610142318.6: ** (depthvec->graph identity vector conj test-struct) 
(depthvec->graph identity vector conj test-struct)


;@+node:conor.20160610142318.7: ** (def depthvec->tree  (partial depthvec->graph 
(def depthvec->tree
  (partial depthvec->graph create-node connect-node conj))
;@+node:conor.20160610142318.8: ** (pprint (depthvec->tree test-struct)) 
(pprint (depthvec->tree test-struct))
;@+node:conor.20160610142318.9: ** (defn dbafter->eid [rv]  (-> 
(defn dbafter->eid [rv]
  (-> rv
     :tx-data
     ffirst))
;@+node:conor.20160610142318.10: ** (def fake-db (db/create-conn)) 
(def fake-db (db/create-conn))
;@+node:conor.20160610142318.11: ** (defn create-ds-node [db text]  
(defn create-ds-node [db text]
  (let [eid (db/q '[:find ?e
                    :in $ ?text
                    :where
                    [?e :node/text ?text]]
                  @db
                  text)]
    (or (ffirst eid)
        (dbafter->eid (db/transact! db [{:db/id -1
                                         :node/text text}])))))
;@+node:conor.20160610142318.12: ** (def rv (create-ds-node fake-db "l")) 

(def rv (create-ds-node fake-db "l"))
;@+node:conor.20160610142318.13: ** (keys rv)
(keys rv)

;test test

@fake-db
;@-others
;@-leo
