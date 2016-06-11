(ns text-nodes.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [text-nodes.handlers]
              [text-nodes.subs]
              [text-nodes.views :as views]
              [text-nodes.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  #_(re-frame/dispatch-sync [:initialize-db])
  (mount-root))
