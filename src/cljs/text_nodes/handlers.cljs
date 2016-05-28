(ns text-nodes.handlers
    (:require [re-frame.core :as re-frame]
              [text-nodes.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))
