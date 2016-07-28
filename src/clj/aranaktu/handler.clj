(ns aranaktu.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [ring.middleware.reload :refer [wrap-reload]])
            [ring.middleware.json :refer [wrap-json-params]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response header]])

(defn generate-token [uid] ())

(defn validate-token [headers handler & params] (apply handler params))

(defn success [data] (response data))
(defn success-token [data token] (header (success data) "x-token" token))

(defroutes routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))

  (POST "/login" {:keys body :as request} (let [username (:username body)
                                                  password (:password body)] ()))
  (POST "/signin" {:keys body :as request} (let [username (:username body)
                                                  password (:password body)] ()))

  (GET "/agenda" {:keys headers :as request} (validate-token headers (fn [] ...)))
  (GET "/agenda/speech" {:keys headers :as request} (validate-token headers (fn [] ...)))
  (GET "/agenda/speech/:id" {:keys headers id :as request} (validate-token headers (fn [id] ...) id))
  (GET "/agenda/speech/:id/rating" {:keys headers id :as request} (validate-token headers (fn [id] ...) id))
  (POST "/agenda/speech/:id/rating" {:keys body id headers :as request} (validate-token headers (fn [body id] ...) body id))

;;   (GET "/cards" [] (resource-response "cards.html" {:root "public"}))
  (resources "/"))

(def app (-> handler
      wrap-json-response
      wrap-json-params))

(def dev-handler (-> #'routes wrap-reload))

(def handler routes)
