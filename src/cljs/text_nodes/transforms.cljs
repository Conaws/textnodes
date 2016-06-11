(ns text-nodes.transforms
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



(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))




(defn parsed [text]
    (->> (str/split text #"\n")
         (map (juxt count-tabs str/trim))))

(defn parsed2 [text]
  (->> (str/split text #"\n")
       (map-indexed (juxt (fn [i x] (count-tabs x))
                          (fn [i x] [i (str/trim x)])))))



(defn nodify [nseq]
  (loop [result []
         s nseq]
    (let[sa (first s)
         r (rest s)
         [children siblings] (split-with #(< (first sa) (first %)) r)
         answer     {:node (second sa)
                     :children-visible true}
         answer
         (if (< 0 (count children))
           (assoc answer :children (nodify children))
           (assoc answer :children children))]

      (if (< 0 (count siblings))
        (recur (conj result answer) siblings)
        (conj result answer)))))


(->> (str/split "this\n\tis\n\n\t\tmy baby" #"\n")
     (filter #(not (empty? %)))
     pprint)


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

(defn create-node-map
  [title]
  {:node title})
(defn connect-node [node children]
   (assoc node :children children :expanded true))




(def depthvec->tree
  (partial transform-depthvec create-node-map connect-node conj))






(declarepath TOPSORT)
(providepath TOPSORT
              (sp/stay-then-continue
               :children ALL TOPSORT))
(def CHILDREN (comp-paths :children ALL))

(defn get-edges [tree]
  (select [ALL TOPSORT (sp/collect-one :node) CHILDREN :node] tree))




(defn dbafter->eid [rv]
  (-> rv
    :tx-data
    ffirst))

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


#_(pprint (select [ALL TOPSORT (sp/collect-one :id):node] indexed-tree))

(defn tree->ds3 [conn tree]
  (let [indexed-tree  (->>  (transform [(sp/subselect ALL TOPSORT :id)]
                                       (partial map-indexed (fn [i x] (- 0 (inc i))))
                                       tree))
        idmap (->> (select [ALL TOPSORT (sp/collect-one :id) :node] indexed-tree)
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





(->> (:tree @app-db)
    (tree->ds conn)
    (create-edges conn)
    pprint)







#_(def mergable (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] (:tree @app-db)))








(defn tree->ds1 [tree]
  (transform [ALL TOPSORT (sp/collect-one :node) :id (sp/subset #{})]
           (comp vector (partial create-ds-node conn))
           tree))
#_(s/fdef create-colls
        :args (s/coll-of string? [])
        :ret  (s/coll-of integer? []))
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
(defn tree->ds3 [conn tree]
  (let [indexed-tree  (->>  (transform [(sp/subselect ALL TOPSORT :id)]
                                       (partial map-indexed (fn [i x] (- 0 (inc i))))
                                       tree))
        idmap (->> (select [ALL TOPSORT (sp/collect-one :id) :node] indexed-tree)
                   (plainent conn))]
    (transform [ALL TOPSORT :id] idmap indexed-tree)))

(defn get-edge-ids [tree]
  (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] tree))


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


#_(def mergable (select [ALL TOPSORT (sp/collect-one :id LAST) CHILDREN :id LAST] (:tree @app-db)))


(defn all-ents [conn]
  (-> (d/pull-many @conn '[*]
        (select [ALL ALL]
                (d/q '[:find ?e :in $ :where [?e]] @conn)))
      pprint))
