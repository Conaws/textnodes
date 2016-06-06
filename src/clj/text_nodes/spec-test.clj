(ns text-nodes.core
  (:require 
            [com.rpl.specter :as sp :refer [ALL]]
            [clojure.spec :as s] 
            [clojure.pprint :refer [pprint]]
            [datascript.core  :as d])
  (:use [com.rpl.specter.macros 
         :only [select transform defprotocolpath
                extend-protocolpath]]))

