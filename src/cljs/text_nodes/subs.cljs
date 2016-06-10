;@+leo-ver=5-thin
;@+node:conor.20160605011953.2: * @file subs.cljs
;@@language clojure
;@+others
;@+node:conor.20160608034748.1: ** @language clojure
;@@language clojure
(ns text-nodes.subs
  (:require [reagent.core    :as rx]
            [posh.core       :as posh  :refer [pull posh! q transact!]]
            [text-nodes.db :refer [conn]]
            [re-frame.core   :refer [register-sub subscribe dispatch register-handler]]
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
;@+node:conor.20160610003346.1: ** (defn count-tabs  [string]  

(defn count-tabs
  [string]
  (count (take-while #{\tab} string)))

(count-tabs "\t\t")
;@+node:conor.20160610003314.1: ** (defn tvalue [e]  (-> 

(defn tvalue [e]
  (-> e
      .-target
      .-value))
  
  
  

(defn parsed [text]
  (->> (str/split text #"\n")
       (map (juxt count-tabs str/trim))))
;@+node:conor.20160608034749.2: ** (defn nodify [nseq]  (loop 
(defn nodify [nseq]
  (loop [result [] 
         s nseq]
    (let[sa (first s)
         r (rest s)
         [children siblings] (split-with #(< (first sa) (first %)) r)
         answer     {:node (second sa)
                     :children-visible true}
         answer
         (if (< 0 (count children))
           (assoc answer :children (nodify children))
           (assoc answer :children children))]
      
      (if (< 0 (count siblings))
        (recur (conj result answer) siblings)
        (conj result answer)))))
;@+node:conor.20160608034749.7: ** (register-sub :e (fn [_ [_ 
(register-sub
 :e
 (fn [_ [_ conn eid]]
   (pull conn '[*] eid)))
;@+node:conor.20160610165924.1: ** text

(register-sub
 :text
 (fn [db]
   (reaction (:text @db))))
;@+node:conor.20160608034749.5: ** (register-sub :db-atoms (fn [_ [_ 


(register-sub
 :db-atoms
 (fn [_ [_ conn]]
   (q conn '[:find ?e ?attr ?val
             :where 
             [?e ?attr ?val]])))
;@+node:conor.20160608034749.6: ** (register-sub :db-entities (fn [_ [_ 


(register-sub
 :db-entities
 (fn [_ [_ conn]]
  (q conn '[:find ?e
             :where 
             [?e]])))
;@+node:conor.20160608034750.10: ** (register-sub :tree (fn [db]  
(register-sub
 :tree
 (fn [db]
   (reaction (:tree @db))))


;@+node:conor.20160608034750.3: ** (register-sub :testmap (fn [db]  


(register-sub
 :testmap
 (fn [db]
   (reaction (:testmap @db))))
;@+node:conor.20160608034750.7: **   (register-sub :parsed-text (fn 
  

(register-sub
 :parsed-text
 (fn [db]
   (reaction (nodify (parsed (:text @db))))))
;@-others
;@-leo
