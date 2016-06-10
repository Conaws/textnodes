
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


(def ts [[0 :a] [1 :b] [2 :c] [0 :d] [1 :e] [1 :f]])


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


(s/explain ::depthvec ts)


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



(s/instrument #'depthvec->graph)


(defn create-node
  [title]
  {:node title})


(defn connect-node [node children]
   (assoc node :children children :expanded true))




(depthvec->graph identity vector conj test-struct)


(depthvec->graph identity vector conj test-struct)


(def depthvec->tree
  (partial depthvec->graph create-node connect-node conj))


(pprint (depthvec->tree test-struct))


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
