;@+leo-ver=5-thin
;@+node:conor.20160605011953.4: * @file db.cljs
;@@language clojure

(ns text-nodes.db
  (:require [reagent.core    :as rx]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-sub subscribe dispatch register-handler]]
            [datascript.core :as db]
            [re-com.core   :as re-com :refer [h-box v-box box gap line scroller border h-split v-split title flex-child-style p]]
            [cljs.pprint     :refer [pprint]]
            [keybind.core :as key]
            [cljs.reader]
            [com.rpl.specter  :refer [ALL] :as s]
            [clojure.string  :as str])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform defprotocolpath]]
           [reagent.ratom :refer [reaction]]))


;@+others
;@+node:conor.20160608034748.2: ** Schema

(def schema {
             :node/text  {:db/unique :db.unique/identity}
             :node/out-edge         {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
             :edge/to               {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}})


;@+node:conor.20160608034749.4: ** Conn
(defonce conn
  (doto (db/create-conn schema)
        posh!))
;@-others
;@-leo
