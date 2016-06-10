;@+leo-ver=5-thin
;@+node:conor.20160610082638.1: * @file specs.cljs
;@@language clojure
;@+others
;@+node:conor.20160610082951.1: ** Spec namespace
(ns text-nodes.specs
  (:require [com.rpl.specter  :as sp :refer [ALL]]
            [cljs.spec        :as s]
            [clojure.string      :as str]
            [cljs.pprint       :refer [pprint]]
            [datascript.core    :as db]))
;@+node:conor.20160606113823.1: ** Edgeparse Specs

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



;@-others
;@-leo
