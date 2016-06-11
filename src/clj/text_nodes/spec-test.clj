;@+leo-ver=5-thin
;@+node:conor.20160606052632.1: * @file spec-test.clj
;@+others
;@+node:conor.20160605013418.1: ** namespace
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
;@+node:conor.20160606062941.1: ** sampletext
(def sampletext "This is the first goal\n\tThis is it's first child\n\t\t@person Conor @role superhero\nThis is another goal\n\tThis is another child 1\n\tThis is another child 1 again")


;@+node:conor.20160606073225.1: ** splitting the string with types
;@+others
;@+node:conor.20160606113823.1: *3* Edgeparse Specs

(def trigger #{"@person" "@role"})


(s/def ::trigger (s/or :deftrig trigger
                       :trig  #(str/starts-with? % "@")))

(s/def ::not-trigger (s/and string? #(not (trigger %))))


(s/def ::edgeparse (s/cat
                    :type  ::trigger
                    :val   (s/* ::not-trigger)))


(s/def ::even-parse  (s/*
                      (s/or :edge  ::edgeparse
                            :node (s/spec (s/+ ::not-trigger)))))




(defn atom? [a] (instance? cljs.core/Atom a))
;@-others
;@+node:conor.20160606123700.1: ** vals-between

(defn vals-between [resetfn s]
  (->> s
       (reduce (fn [{c :c r :r :as m} x]
                 (if (resetfn x)
                   (assoc m :c [x] :r (if (empty? c)
                                        r
                                        (conj r c)))
                   (assoc m :c (conj c x))))
               {:c [] :r []})
       ((fn [{c :c r :r}] (conj r c)))))


;@+node:conor.20160606073225.2: ** (defn count-tabs  [string]
(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))

;@+node:conor.20160606073225.3: ** (defn parsed [text]  (->>


(defn check-for-edges [string]
  (->  (str/trim string)
       (str/split #"\s+")
       (->> (vals-between #(s/valid? ::trigger %))
            (s/conform ::even-parse))))

(defn parsed [text]
  (->> (str/split text #"\n")
       (map (juxt count-tabs check-for-edges))))


(pprint (parsed sampletext))

;@-others
;@-leo
