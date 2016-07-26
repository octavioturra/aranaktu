(ns aranaktu.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defroutes routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (GET "/cards" [] (resource-response "cards.html" {:root "public"}))
  (resources "/"))

(def dev-handler (-> #'routes wrap-reload))

(def handler routes)
