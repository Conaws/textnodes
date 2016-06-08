;@+leo-ver=5-thin
;@+node:conor.20160606161843.1: * @file specter-test.clj
;@+others
;@+node:conor.20160606161918.1: ** namespace
(ns text-nodes.core
  (:require [com.rpl.specter  :as sp :refer [ALL]]
                [clojure.spec        :as s]
                [clojure.string      :as str]
                [clojure.pprint       :refer [pprint]]
                [datascript.core    :as db])
  (:use 
   [com.rpl.specter.macros 
         :only [select transform defprotocolpath
                extend-protocolpath]]))
;@-others
;@-leo
