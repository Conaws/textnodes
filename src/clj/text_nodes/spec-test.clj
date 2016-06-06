;@+leo-ver=5-thin
;@+node:conor.20160606052632.1: * @file spec-test.clj
;@+others
;@+node:conor.20160606053224.1: ** spec-test
;@+node:conor.20160605013418.1: *3* namespace -- datascript and specter
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
;@+node:conor.20160606062933.1: *3* string spec
;@+node:conor.20160606062941.1: *4* sampletext
(def sampletext "This is the first goal\n\tThis is it's first child\n\t\t:person Conor")
;@+node:conor.20160606063153.1: *4* splitting the string with types
;@+others
;@+node:conor.20160606064205.1: *5* (defn count-tabs  [string]  
(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))
  
;@+node:conor.20160606064205.2: *5* (defn parsed [text]  (->> 

(defn parsed [text]
  (->> (str/split text #"\n")
       (map (juxt count-tabs str/trim))))

;@+node:conor.20160606064219.1: *5* (parsed sampletext)
(parsed sampletext)


;@+node:conor.20160606065151.1: *5* Edge Spec
;@+node:conor.20160606065211.1: *6* person spec
(s/def ::person (s/and string? #(str/starts-with?  %  ":person")))
                                   
;@+node:conor.20160606070318.1: *6* role spec
(s/def ::role  (s/and string?  #(re-matches #":role" %)))

;@+node:conor.20160606064711.1: *6* edges
(s/def ::edges (s/or 
                :person ::person
                :role   ::role
                :node   string?))

;@+node:conor.20160606070500.1: *6* Test the Edgespec
;@+node:conor.20160606070517.1: *7* newHeadline
(for [[i t] (parsed sampletext)]
    [i (s/conform ::edges t)])
    
    
;@-others
;@-others
;@-leo
