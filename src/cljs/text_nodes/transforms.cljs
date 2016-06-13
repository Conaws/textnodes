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

(defn parsed-with-index [text]
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




(defn nodify2 [rows]
          ;; find the entities
  (let [entities (into {}
                       (for [[idx _ text] rows]
                         [idx text]))
                ;; find the relationships
        relations (into {}
                        (for [[idx indent] rows]
                          [idx
                           (map (comp - first)
                                (filter
                                  #(= (inc indent) (second %))
                                  (drop idx rows)))]))
        offset (+ 1 (count relations) (count entities))]
    (apply
      concat
      (for [[idx text] entities
            :let [children (relations idx)]]
        (cons {:node/text text
               :node/children (map - children (repeat offset))
               :db/id (- idx)}
              (map-indexed
                (fn [idx2 cidx]
                  {:db/id (- cidx offset)
                   :edge/order idx2
                   :edge/to cidx})
                children))))))


(defn parsed2 [text]
  (map-indexed
    (fn [idx entity]
      [(inc idx) (count-tabs entity) (str/trim entity)])
    (str/split text #"\n")))

#_(deftest text-nodes.handlers
   (is (= (nodify3 (parsed2
                    "this is
        \tsome text
        \t\tsome other text
        \t\tmore child
        \tthis"))
          ({:node/text "this is", :node/children (-13 -14 -16), :db/id -1}
           {:db/id -13, :edge/order 0, :edge/to -2}
           {:db/id -14, :edge/order 1, :edge/to -3}
           {:db/id -16, :edge/order 2, :edge/to -5}
           {:node/text "some text", :node/children (-15), :db/id -2}
           {:db/id -15, :edge/order 0, :edge/to -4}
           {:node/text "some other text", :node/children (-15), :db/id -3}
           {:db/id -15, :edge/order 0, :edge/to -4}
           {:node/text "more child", :node/children (), :db/id -4}
           {:node/text "this", :node/children (), :db/id -5}))))








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




(defn all-ents [conn]
  (-> (d/pull-many @conn '[*]
        (select [ALL ALL]
                (d/q '[:find ?e :in $ :where [?e]] @conn)))
      pprint))
