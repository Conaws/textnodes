;@+leo-ver=5-thin
;@+node:conor.20160605011953.4: * @file db.cljs
;@@language clojure
;@+others
;@+node:conor.20160610001423.1: ** @language clojure
;@@language clojure

(ns text-nodes.db
  (:require [reagent.core    :as rx]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-sub subscribe dispatch register-handler]]
          ;  [re-frame.db :as rdb :refer [app-db]]
        ;    [history.reframe-db :as ls :refer [!>ls <!ls db->seq]]
       ;     [history.util  :refer [e-value]]
       ;     [hickory.core :refer [as-hiccup as-hickory]]
            [datascript.core :as db]
            [re-com.core   :as re-com :refer [h-box v-box box gap line scroller border h-split v-split title flex-child-style p]]
            [cljs.pprint     :refer [pprint]]
            [keybind.core :as key]
            [cljs.reader                ]
            [com.rpl.specter  :refer [ALL] :as s]
            [clojure.string  :as str    ])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform defprotocolpath]]
           [reagent.ratom :refer [reaction]]))
           
           
;@+node:conor.20160608034748.2: ** (def schema {;:canvas/layouts   

(def schema {
             :node/out-edge         {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}
             :edge/to               {:db/valueType :db.type/ref :db/cardinality :db.cardinality/many}

             })
;@+node:conor.20160608034749.4: ** (defonce conn  (doto (db/create-conn 
(defonce conn
  (doto (db/create-conn schema)
        posh!))
;@-others
;@-leo
