(ns text-nodes.txns
  (:require [datascript.core    :as d]
            [com.rpl.specter    :as sp
                                :refer [ALL LAST MAP-VALS FIRST subselect
                                        stay-then-continue collect
                                        collect-one if-path]]
            [clojure.pprint     :refer [pprint]]
            [clojure.string     :as string])
  (:use
   [com.rpl.specter.macros
         :only [select transform setval declarepath providepath]]))



(declare all-ents)

(def parent
;; ?a is a parent of ?b
 '[[(parent ?a ?b)]
   [?a :edge/to ?b]])

(def ancestor
; ?a is an anscestor of ?b
 '[[(ancestor ?a ?b)
    [parent ?a ?b]]
   [(ancestor ?a ?b)
    [parent ?a ?x]
    [ancestor ?x ?b]]])

(def rules (concat parent ancestor))



(defn ancestor-of-eid [db id-of-changing-entity]
  (let [edges (q '[:find [?ancestor ?direct-child]
                   :in $ % ?changling
                   :where [[ancestor ?ancestor ?changling]
                           [ancestor ?direct-child ?changling]
                           [parent ?ancestor  ?direct-child]]]
                 db
                 rules
                 id-of-changing-entity)]
    edges))


(all-ents @conn)

(def sampmap
  [{:a "foo"
    :b "bar"
    :c {:this "other thing"}}])



;; create a tempid for everything

(declarepath TOPSORT2)
(providepath TOPSORT2
             (if-path map?
               (stay-then-continue
                [MAP-VALS TOPSORT2])))

(def s2
  (transform [(subselect ALL TOPSORT2 :tempid) (sp/view count)]
            #(range (- %) 0) 
            sampmap))


;; replace nested maps with the tempid


(def s3 (select [ALL TOPSORT2] s2))


(transform [ALL MAP-VALS]
           (fn [v] (if (:tempid v)
                     (:tempid v)
                      v))
           s3)



(defn nestedmaps->ds [mapvec]
  (->> mapvec
   (transform [(subselect ALL TOPSORT2 :tempid) (sp/view count)]
            #(range (- %) 0))
   (select [ALL TOPSORT2])
   (transform [ALL MAP-VALS]
           (fn [v] (if (:tempid v)
                     (:tempid v)
                      v)))))


(nestedmaps->ds sampmap)





(defn add-tempids-to-tree [tree]
   (transform [(sp/subselect ALL TOPSORT :tempid) (sp/view count)]
              (fn [c] (range (- c) 0))
              tree))




(add-tempids-to-tree sampmap)



(defn plainent [conn text title ids]
  (let [pvec [{:db/id -1000
                     :node/child-text text
                     :node/title title}]
        ents (apply conj pvec
              (for [[i t] ids] {:db/id i
                                :coll/text  t}))]
    (->> (d/transact! conn ents)
         :tempids)))


(s/fdef plainent
        :args (s/cat :db mys/atom?
                     :entvecs (s/coll-of  (s/spec
                                           (s/cat
                                            :id integer?
                                            :text string?))
                                          [])
                     :text string?
                     :title string?))


(defn tree->ds [conn tree text title]
  (let [indexed-tree  (->>  (transform [(sp/subselect ALL TOPSORT :id)]
                                       (partial map-indexed (fn [i x] (- 0 (inc i))))
                                       tree))
        idmap (->> (select [ALL TOPSORT (sp/collect-one :id) :node] indexed-tree)
                   (plainent conn text title))]
    (transform [ALL TOPSORT :id] idmap indexed-tree)))


(defn create-coll [collid children]
                  {:db/id collid
                   :edge/to children})

(defn merge-vectors [e]
  (->> (for [[k v] e]
            {k #{v}})
       (apply merge-with clojure.set/union)))

(defn create-edges [conn treemap]
  (let [e (select [ALL TOPSORT (sp/collect-one :id) CHILDREN :id] treemap)
        c (select [ALL] (merge-vectors e))]
    (d/transact!  conn (vec (for [[x y] c]
                              (create-coll x y))))))




(defn all-ents [db]
  (-> (d/pull-many db '[*]
        (select [ALL ALL]
                (d/q '[:find ?e :in $ :where [?e]] db)))
      pprint))


#_(register-handler
     :tree->ds
     (fn [db [_ conn]]
       (let [text (:text db)
             title (:title db)
             newtree  (t/tree->ds conn (:tree db) (:text db) (:title db))]
         (do
           (t/create-edges conn newtree))
         (->> (assoc db :tree [] :text "" :title "New Map")
              (setval [:nodes (sp/subset #{})] [text title])))))









;@-others

;@-leo
