
(ns text-nodes.transactions
  (:require [reagent.core                                       :as r]
            [text-nodes.db                              :refer [conn]]
            [text-nodes.transforms                              :as t]
            [text-nodes.specs                                 :as mys]
            [cljs.spec                                          :as s]
            [posh.core      :as posh  :refer [pull posh! q transact!]]
            [re-frame.core        :refer [register-handler subscribe]]
            [re-frame.db                              :refer [app-db]]
            [datascript.core                                    :as d]
            [cljs.pprint                              :refer [pprint]]
            [com.rpl.specter                            :as sp  :refer
             [ALL STAY LAST stay-then-continue collect-one comp-paths]]
            [clojure.string                                   :as str])
  (:require-macros
           [com.rpl.specter.macros  :refer [select setval transform
                                            declarepath providepath]]
           [reagent.ratom :refer [reaction]]))

(declare all-ents)

(def parent
;; ?a is a parent of ?b
 '[(parent ?a ?b)
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
  (let [edges (d/q '[:find ?ancestor
                   :in $ % ?changling
                   :where [[ancestor ?ancestor ?changling]]]
                 db
                 rules
                 id-of-changing-entity)]
    edges))


(all-ents @conn)

(d/q '[:find ?a
       :in $ 
       :where [?a :edge/to 6]] @conn parent)


(ancestor-of-eid @conn 6)



(declarepath TOPSORT)
(providepath TOPSORT
              (sp/stay-then-continue
               :children ALL TOPSORT))
(def CHILDREN (comp-paths :children ALL))


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
