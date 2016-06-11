;@+leo-ver=5-thin
;@+node:conor.20160605011953.3: * @file handlers.cljs
;@@language clojure
;@+others
;@+node:conor.20160610073301.1: ** @language clojure
(ns text-nodes.handlers
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


;@+node:conor.20160610152638.1: ** Depthvec
;@+node:conor.20160610152638.2: *3* specs
(s/def ::function fn?)

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



;@+node:conor.20160610152638.3: *3* (s/instrument #'depthvec->graph)


;@+node:conor.20160610152638.4: *3* depthvec->graph
(defn transform-depthvec [nodefn edgefn sibling-collector nseq]
  (loop [result []
         s nseq]
    (let[[pdepth ptitle] (first s)
         [children siblings] (split-with #(< pdepth (first %)) (rest s))
         answer   (nodefn ptitle)
         answer
         (if (seq children)
           (edgefn answer (transform-depthvec nodefn edgefn sibling-collector children))
           answer)]
      (if (seq siblings)
        (recur (sibling-collector result answer) siblings)
        (sibling-collector result answer)))))


(s/instrument #'transform-depthvec)

;@+node:conor.20160610152638.5: *3* depthvec->tree
;@+node:conor.20160610152638.6: *4* helpers
;@+node:conor.20160610152638.7: *5* (defn create-node  [title]
(defn create-node-map
  [title]
  {:node title})
;@+node:conor.20160610152638.8: *5* (defn connect-node [node children]
(defn connect-node [node children]
   (assoc node :children children :expanded true))




;@+node:conor.20160610152638.9: *4* (def depthvec->tree  (partial depthvec->graph
(def depthvec->tree
  (partial transform-depthvec create-node-map connect-node conj))



;@+node:conor.20160610073243.1: ** Parse-Text



;@+others
;@+node:conor.20160610164021.2: *3* (declarepath TOPSORT)






(declarepath TOPSORT)
(providepath TOPSORT
              (sp/stay-then-continue
               :children ALL TOPSORT))
;@+node:conor.20160610164021.3: *3* (def CHILDREN (comp-paths :children ALL))
(def CHILDREN (comp-paths :children ALL))

(defn get-leaves [tree]
  (select [ALL TOPSORT :node] tree))
;@+node:conor.20160610164021.4: *3* (defn get-edges [tree]  (select
(defn get-edges [tree]
  (select [ALL TOPSORT (sp/collect-one :node) CHILDREN :node] tree))


;@+node:conor.20160610164021.6: *3* (defn dbafter->eid [rv]  (->


(defn dbafter->eid [rv]
  (-> rv
    :tx-data
    ffirst))
;@+node:conor.20160610164021.7: *3* (defn create-ds-node [db text]

(defn create-ds-node [db text]
  (let [eid (d/q '[:find ?e
                    :in $ ?text
                    :where
                    [?e :coll/text ?text]]
                @db
                text)]
      (or (ffirst eid)
        (dbafter->eid (d/transact! db [{:db/id -1
                                        :coll/text text}])))))
;@+node:conor.20160610164021.8: *3* (defn tree->ds1 [tree]  (transform


(defn tree->ds1 [tree]
  (transform [ALL TOPSORT (sp/collect-one :node) :id (sp/subset #{})]
           (comp vector (partial create-ds-node conn))
           tree))


#_(s/fdef create-colls
        :args (s/coll-of string? [])
        :ret  (s/coll-of integer? []))



#_(setval [(sp/subselect ALL map?) (sp/subset :new-val)]
        1 [{:a 1}[:not :me 1]{:b 2}])


#_(->>  (transform [(sp/subselect ALL TOPSORT :id) (sp/view count)]
                 range
                 (:tree @app-db))
      (select [ALL TOPSORT :id])
      pprint)

(defn atom? [a] (instance? cljs.core/Atom a))





(defn plainent [conn ids]
  (let [ents (vec (for [[i t] ids] {:db/id i
                                     :coll/text  t}))]
    (->> (d/transact! conn ents)
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



(defn tree->ds [conn tree]
  (let [;tree (:tree @app-db)
        indexed-tree  (->>  (transform [(sp/subselect ALL TOPSORT :id)]
                                       (partial map-indexed (fn [i x] (- 0 (inc i))))
                                       tree))
        idmap (->> (select [ALL TOPSORT (sp/multi-path :id :node)] indexed-tree)
                   (partition 2)
                   (map vec)
                   vec
                   (plainent conn))]
    (transform [ALL TOPSORT :id] idmap indexed-tree)))





#_(transform [(sp/subselect ALL ALL)] reverse [#{1 2 3} [4 5 6]])

    ;     vec
    ;     (d/transact! conn)
    ;     :tempids
    ;     vals
    ;     drop-last]))


#_(defn tree->ds2 [tree]
  (transform [ALL TOPSORT (sp/collect-one :node) :id (sp/subset #{})]
           create-colls
           tree))


#_(pprint (tree->ds2 create-colls))



(defn get-edge-ids [tree]
  (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] tree))



(comment
  (def testmap (tree->ds1 (:tree @app-db)))

  (d/transact! conn [{:db/id 1}]
                    :edge/to #{2 3}))




(defn create-coll [collid children
                        {:db/id collid}
                        :edge/to children])



(defn merge-vectors [e]
  (->> (for [[k v] e
              {k #{v}}
              (apply merge-with clojure.set/union)])))



(defn create-edges [conn treemap]
  (let [e (select [ALL TOPSORT (sp/collect-one :id) CHILDREN :id] treemap)
        c (select [ALL] (merge-vectors e))]
    (d/transact!  conn (vec (for [[x y] c]
                              (create-coll x y))))))



(->> (:tree @app-db)
    (tree->ds conn)
    (create-edges conn)
    pprint)




(register-handler
 :tree->ds
 (fn [db [_ conn]]
   (let [newtree  (tree->ds conn (:tree db))]
     (do
       (create-edges conn newtree))
     (assoc db :tree newtree))))






(def mergable (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] testmap))






;@+node:conor.20160610164021.10: *3* (d/transact! conn [{:db/id [:node/text "Hello


(d/transact! conn [{:db/id [:node/text "Hello Graphs"]
                     :node/test "helllo"}])
;@+node:conor.20160610164021.11: *3* (d/transact! conn [{:db/id -1
(d/transact! conn [{:db/id -1
                    :node/text "Node B"
                    :edge/_to [:node/text "Hello Graphs"]}])
;@+node:conor.20160610164021.12: *3* (d/q '[:find ?e
(d/q '[:find ?e
       :in $
       :where [?e :node/text "Hello Graphs"]]
     @conn)

(d/pull @conn '[*] 3)

(->
 (d/pull-many @conn '[*] (select [ALL ALL] (d/q '[:find ?e :in $ :where [?e]] @conn)))
 pprint)
;@+node:conor.20160610164021.13: *3* (d/q '[:find [(pull ?e [*])

(d/q '[:find [(pull ?e [*]) ?e]
             :in $
              :where [?e]]
      @conn)

;@-others
;@-others

;@-leo
