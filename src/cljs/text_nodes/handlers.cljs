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
            [datascript.core :as d]
            [cljs.pprint     :refer [pprint]]
            [cljs.reader]
            [com.rpl.specter  :refer [ALL] :as sp]
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


(d/transact! conn [{:db/id [:node/text "Hello Graphs"]
                     :node/test "helllo"}])


(d/transact! conn [{:db/id -1
                    :node/text "Node B"
                    :edge/_to [:node/text "Hello Graphs"]}])


(d/q '[:find ?e
       :in $
       :where [?e :node/text "Hello Graphs"]]
     @conn)

(d/pull @conn '[*] 3)

(->
 (d/pull-many @conn '[*] (select [ALL ALL] (d/q '[:find ?e :in $ :where [?e]] @conn)))
 pprint)



(d/q '[:find [(pull ?e [*]) ?e]
             :in $
              :where [?e]]
      @conn)

;@-others

;@-leo
