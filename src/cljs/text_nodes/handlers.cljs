;@+leo-ver=5-thin
;@+node:conor.20160605011953.3: * @file handlers.cljs
;@@language clojure

(ns text-nodes.handlers
  (:require [reagent.core    :as r]
            [text-nodes.db :refer [conn]]
            [text-nodes.transforms :as t]
            [text-nodes.specs :as mys]
            [cljs.spec        :as s]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-handler subscribe]]
            [re-frame.db     :refer [app-db]]
            [datascript.core :as d]
            [cljs.pprint     :refer [pprint]]
            [cljs.reader          ]
            [com.rpl.specter  :refer [ALL STAY LAST stay-then-continue collect-one comp-paths] :as sp]
            [clojure.string  :as str])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform declarepath providepath]]
           [reagent.ratom :refer [reaction]]))


;@+others
;@+node:conor.20160610073301.1: ** handlers


(register-handler
 :change-text
 (fn [db [_ text]]
   (assoc db :text text)))


(register-handler
 :clear-text
 (fn [db [_ e end]]
   (let [text (:text db)]
     (js/console.log (pr-str e))
     (assoc db :text (str (subs text 0 e) "\t"  (subs text end))))))



(register-handler
 :fix-tree
 (fn [db]
   (let [tree @(subscribe [:parsed-text])]
     (assoc db :tree tree))))




(register-handler
     :tree->ds
     (fn [db [_ conn]]
       (let [newtree  (t/tree->ds conn (:tree db))]
         (do
           (t/create-edges conn newtree))
         (assoc db :tree newtree))))








;@-others

;@-leo
