;@+leo-ver=5-thin
;@+node:conor.20160605011953.3: * @file handlers.cljs
;@@language clojure
;@+others
;@+node:conor.20160610073301.1: ** @language clojure
;@@language clojure
(ns text-nodes.handlers
  (:require [reagent.core    :as rx]
            [text-nodes.db :refer [conn]]
            [text-nodes.specs :as mys]
            [cljs.spec        :as s]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [re-frame.core   :refer [register-sub subscribe dispatch register-handler]]
            [datascript.core :as db]
            [re-com.core   :as re-com :refer [h-box v-box box gap line scroller border h-split v-split title flex-child-style p]]
            [cljs.pprint     :refer [pprint]]
            [keybind.core :as key]
            [cljs.reader                ]
            [com.rpl.specter  :refer [ALL] :as sp]
            [clojure.string  :as str    ])
  (:require-macros
           [com.rpl.specter.macros  :refer [select transform defprotocolpath]]
           [reagent.ratom :refer [reaction]]))
;@+node:conor.20160610073243.1: ** Parse-Text

    
(s/describe ::mys/trigger)
   
;@-others

;@-leo
