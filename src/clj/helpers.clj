;@+leo-ver=5-thin
;@+node:conor.20160610150037.1: * @file helpers.clj
;@@language clojure

(ns text-nodes.helpers
  (:require [com.rpl.specter  :as sp :refer [ALL]]
            [clojure.spec                   :as s]
            [clojure.string               :as str]
            [clojure.spec.gen             :as gen]
            [clojure.pprint       :refer [pprint]]
            [datascript.core                :as d])
  (:use
   [com.rpl.specter.macros
         :only [select transform declarepath providepath defprotocolpath
                extend-protocolpath]]))

;@+others
;@+node:conor.20160610150348.1: ** datascript helpers
;@+node:conor.20160610142318.9: *3* (defn dbafter->eid [rv]  (-> 
  (defn dbafter->eid [rv]
    (-> rv
      :tx-data
      ffirst))
;@-others
;@-leo
