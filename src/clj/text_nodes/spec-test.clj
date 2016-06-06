;@+leo-ver=5-thin
;@+node:conor.20160606052632.1: * @file spec-test.clj
;@+others
;@+node:conor.20160606053224.1: ** spec-test
;@+node:conor.20160605013418.1: *3* namespace
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
;@+node:conor.20160606065151.1: *4* Edge Spec
;@+node:conor.20160606065211.1: *5* person spec
(s/def ::person (s/and string? #(str/starts-with?  %  ":person")))
                                   
;@+node:conor.20160606070318.1: *5* role spec
(s/def ::role  (s/and string?  #(re-matches #":role" %)))

;@+node:conor.20160606074329.1: *5* edge
(s/def ::edge 
    (s/or 
        :person ::person
        :role ::role))
;@+node:conor.20160606064711.1: *5* edges
(s/def ::edges (s/or 
                :edge   ::edge
                :node   string?))

;@+node:conor.20160606073225.1: *4* splitting the string with types
;@+others
;@+node:conor.20160606073225.2: *5* (defn count-tabs  [string]  
(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))
  
;@+node:conor.20160606075341.1: *5* using split-with


(def trigger #{"@person" "@role"})

(s/def ::trigger trigger)


(s/def ::string string?)

(s/def ::even-parse  (s/* 
                      (s/cat 
                       :t        ::trigger
                       :string   string?)))




(defn check-edges [edgeset string]
  (let [words (str/split string #"\s")]
    (->> (split-with #(edgeset %) words)
        (partition 2)
        first
        ((juxt #(ffirst %) #(str/join " " (second %)))))))



(s/explain ::even-parse (check-edges trigger "@person Conor"))
(s/conform ::even-parse (check-edges trigger "@person Conor @role King of the World"))

(s/explain ::even-parse [[":abcd" "this is all the text"][":edf"  "that follos"]])






;@+node:conor.20160606094026.1: *6* (def trigger #{"@person" "@role"})


(def trigger #{"@person" "@role"})

(s/def ::trigger trigger)
;@+node:conor.20160606094026.2: *6* (s/def ::string string?)
(s/def ::string string?)

(s/def ::even-parse  (s/* 
                      (s/cat 
                       :t        ::trigger
                       :string   string?)))
;@+node:conor.20160606094026.3: *6* (defn check-edges [edgeset string]  


(defn check-edges [edgeset string]
  (let [words (str/split string #"\s")]
    (->> (split-with #(edgeset %) words)
        (partition 2)
        first
        ((juxt #(ffirst %) #(str/join " " (second %)))))))
;@+node:conor.20160606094026.4: *6* (s/explain ::even-parse (check-edges trigger "@person 

(s/explain ::even-parse (check-edges trigger "@person Conor"))
(s/conform ::even-parse (check-edges trigger "@person Conor @role King of the World"))

(s/explain ::even-parse [[":abcd" "this is all the text"][":edf"  "that follos"]])
;@+node:conor.20160606073225.3: *5* (defn parsed [text]  (->> 

(defn parsed [text]
  (->> (str/split text #"\n")
       (map (juxt count-tabs str/trim))))

;@-others
;@+node:conor.20160606070500.1: *4* Test the Edgespec
;@+node:conor.20160606070517.1: *5* returning the conformed value
(for [[i t] (parsed sampletext)]
    [i (s/conform ::edges t)])
    
    
;@-others
;@-leo
