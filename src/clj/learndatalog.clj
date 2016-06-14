
(ns user.learn-datalog-today
  (:require [datascript.core :as d]
            [clojure.pprint     :refer [pprint]]
            [clojure.string :as string]))


(def data-url "https://raw.githubusercontent.com/jonase/learndatalogtoday/master/resources/db/data.edn")

(def schema-url "https://raw.githubusercontent.com/jonase/learndatalogtoday/master/resources/db/schema.edn")


(def data
  (-> data-url
      slurp
      (string/replace ,,, #"#db/id \[:db\.part/user ([-\d]+)\]" "$1")
      read-string))

; download schema & slice and dice it into the format DataScript wants

(def schema
  (-> schema-url
      slurp
      ; DataScript doesn't need :db/id in as attributes
      (string/replace ,,, #":db/id #db/id \[:db\.part/db\]" "")
      ; it definitely doesn't need to install attributes
      (string/replace ,,, #":db\.install/_attribute :db\.part/db" "")
      ; it doesn't like being told about any value types other than refs
      (string/replace ,,, #":db/valueType :db\.type/(string|long|instant)" "")
      read-string
      ; instead of a vector of schemas, DataScript wants a nested map
      (->> ,,,
           (map (fn [{ident :db/ident :as schema}]
                  (let [others (dissoc schema :db/ident)]
                    {ident others}))
                ,,,)
           (apply merge ,,,))))





(def conn (d/create-conn schema))


(d/transact! conn data)

; try a query
(d/q '[:find ?title
       :where
       [_ :movie/title ?title]]
     @conn)
; #{["First Blood"] ["Terminator 2: Judgment Day"] ["The Terminator"] …


(d/q '[:find ?e
       :where
       [?e :person/name "Ridley Scott"]]
     @conn)
; #{[38]}


(d/q '[:find ?title
       :where
       [?e :movie/title ?title]
       [?e :movie/year 1987]]
     @conn)
; #{["Lethal Weapon"] ["RoboCop"] ["Predator"]}



(d/q '[:find ?name
       :where
       [?m :movie/title "Lethal Weapon"]
       [?m :movie/cast ?p]
       [?p :person/name ?name]]
     @conn)
; #{["Danny Glover"] ["Gary Busey"] ["Mel Gibson"]}




; Find directors who have directed Arnold Schwarzenegger in a movie.

; Find movie title by year

;Given a list of movie titles, find the title and the year that movie was released.
; input ["Lethal Weapon" "Lethal Weapon 2" "Lethal Weapon 3"]


;  Find all movie ?titles where the ?actor and the ?director has worked together
;  "Michael Biehn"
;  "James Cameron"



(def ratings
  [["Die Hard" 8.3]
   ["Alien" 8.5]
   ["Lethal Weapon" 7.6]
   ["Commando" 6.5]
   ["Mad Max Beyond Thunderdome" 6.1]
   ["Mad Max 2" 7.6]
   ["Rambo: First Blood Part II" 6.2]
   ["Braveheart" 8.4]
   ["Terminator 2: Judgment Day" 8.6]
   ["Predator 2" 6.1]
   ["First Blood" 7.6]
   ["Aliens" 8.5]
   ["Terminator 3: Rise of the Machines" 6.4]
   ["Rambo III" 5.4]
   ["Mad Max" 7.0]
   ["The Terminator" 8.1]
   ["Lethal Weapon 2" 7.1]
   ["Predator" 7.8]
   ["Lethal Weapon 3" 6.6]
   ["RoboCop" 7.5]])


   ;; Write a query that, given an actor name and a relation with movie-title/rating,
   ;; finds the movie titles and corresponding rating for which that actor was a cast member.
   ;; "Mel Gibson"
   ;; input2 ratings


(d/q '[:find ?title ?ratings
       :in $ ?actor [[?title ?ratings]]
       :where
        [?m :movie/title ?title]
        [?m :movie/cast ?p]
        [?p :person/name ?actor]]
     @conn
     "Mel Gibson"
     ratings)




;; what attributes are associated with a given movie
;; input "Commando"




;; Find the names of all people associated with a particular movie (i.e. both the actors and the directors)
;;

(d/q
 '[:find [(pull ?m [*])]
   :in $ ?title [?attr ...]
   :where
    [?m :movie/title ?title]
    [?m ?attr ?p]
    [?p :person/name ?name]]
  @conn
  "Die Hard"
  [:movie/cast :movie/director])





(d/q
 '[:find ?name
   :in $ ?title [?attr ...]
   :where
    [?m :movie/title ?title]
    [?m ?attr ?p]
    [?p :person/name ?name]]
  @conn
  "Die Hard"
  [:movie/cast :movie/director])
