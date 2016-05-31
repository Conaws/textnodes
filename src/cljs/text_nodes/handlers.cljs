(ns text-nodes.handlers
  (:require [re-frame.core :as re-frame]
            [text-nodes.db :as db]
            [clojure.string :as str])
  (:require-macros
    [cljs.test :refer :all]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))


(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))

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

(deftest text-nodes.handlers
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
