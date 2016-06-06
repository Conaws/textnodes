(ns text-nodes.handlers
    (:require [re-frame.core :as re-frame]
              [text-nodes.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

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

(defn parsed2 [text]
  (map-indexed
    (fn [idx entity]
      [idx (count-tabs entity) (str/trim entity)])
    (str/split text #"\n")))
