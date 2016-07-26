(ns aranaktu.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [devtools.core :as devtools]
              [aranaktu.handlers]
              [aranaktu.subs]
              [aranaktu.routes :as routes]
              [aranaktu.views :as views]
              [aranaktu.config :as config]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")
    (devtools/install!)))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
