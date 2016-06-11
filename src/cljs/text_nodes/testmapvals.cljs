(ns text-nodes.testmapvals
  (:require [com.rpl.specter :as s])
  (:require-macros [com.rpl.specter.macros :refer [select transform]]))



(transform [s/MAP-VALS (s/collect s/ALL (s/pred :foo))]
           (fn [a _] (count a))
           {:a [{:foo 1} {:bar 3} {:foo 4}]
            :b [{:foo 1} {:bar 3 :foo 4} {:foo 4}]})

;;  returns 0


(transform [s/MAP-VALS] inc {:a 1 :b 2})

;;  "{:a 1, :b 2}1"
