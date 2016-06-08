
(ns text-nodes.core
  (:require [com.rpl.specter  :as sp :refer [ALL]]
                [clojure.spec        :as s]
                [clojure.string      :as str]
                [clojure.pprint       :refer [pprint]]
                [datascript.core    :as db])
  (:use 
   [com.rpl.specter.macros 
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






(defn deptharray->graph [nodefn edgefn sibling-collector nseq]
  (loop [result [] 
         s nseq]
    (let[[pdepth ptitle] (first s)
         [children siblings] (split-with #(< pdepth (first %)) (rest s))
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




(deptharray->graph identity vector conj test-struct)


(deptharray->graph identity vector a test-struct)


(def deptharray->nestedmap 
  (partial deptharray->graph create-node connect-node conj))


(pprint (deptharray->nestedmap test-struct))


(defn dbafter->eid [rv]
  (-> rv
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
        (dbafter->eid (db/transact! db [{:db/id -1
                                         :node/text text}])))))



(def rv (create-ds-node fake-db "l"))


(keys rv)

;test test

@fake-db
