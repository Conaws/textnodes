;@+leo-ver=5-thin
;@+node:conor.20160605011953.2: * @file subs.cljs
;@@language clojure

(ns text-nodes.subs
  (:require [reagent.core    :as rx]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [text-nodes.transforms :as t]
            [text-nodes.db :refer [conn]]
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
;@+node:conor.20160608034749.7: ** :e
(register-sub
 :e
 (fn [_ [_ conn eid]]
   (pull conn '[*] eid)))
;@+node:conor.20160610165924.1: ** :text
(register-sub
 :text
 (fn [db]
   (reaction (:text @db))))
;@+node:conor.20160608034749.5: ** datoms
(register-sub
 :db-atoms
 (fn [_ [_ conn]]
   (q conn '[:find ?e ?attr ?val
             :where
             [?e ?attr ?val]])))
;@+node:conor.20160608034749.6: ** :db-entities
(register-sub
 :db-entities
 (fn [_ [_ conn]]
  (q conn '[:find ?e
             :where
             [?e]])))
;@+node:conor.20160608034750.10: ** :tree
(register-sub
 :tree
 (fn [db]
   (reaction (:tree @db))))


;@+node:conor.20160608034750.3: ** :testmap


(register-sub
 :testmap
 (fn [db]
   (reaction (:testmap @db))))
;@+node:conor.20160608034750.7: ** :parsed-text
(register-sub
 :parsed-text
 (fn [db]
   (reaction (t/nodify (t/parsed (:text @db))))))

;@-others
;@-leo
