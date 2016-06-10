;@+leo-ver=5-thin
;@+node:conor.20160605011953.3: * @file handlers.cljs
;@@language clojure
;@+others
;@+node:conor.20160610073301.1: ** @language clojure
;@@language clojure
(ns text-nodes.handlers
  (:require [reagent.core    :as r]
            [text-nodes.db :refer [conn]]
            [text-nodes.specs :as mys]
            [cljs.spec        :as s]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-handler]]
            [datascript.core :as d]
            [cljs.pprint     :refer [pprint]]
            [cljs.reader                ]
            [com.rpl.specter  :refer [ALL] :as sp]
            [clojure.string  :as str])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform declarepath providepath]]
           [reagent.ratom :refer [reaction]]))
;@+node:conor.20160610073243.1: ** Parse-Text

    
(s/describe ::mys/trigger)

@conn

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


;; works
(d/q '[:find [(pull ?e [*]) ?e]
              :in $
              :where [?e]]
       @conn)
   
;@-others

;@-leo
