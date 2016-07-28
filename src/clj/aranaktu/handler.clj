(ns aranaktu.handler
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.util.response :refer [response header status]]
            [clojure.core.async :refer [<!! go]]
            [aranaktu.db :as db]))

(defn generate-token [[result]] (let [{id :id} result] (when id (hash id))))
(defn validate-token [headers handler & params] (apply handler params))

(defn success [data] (response data))
(defn success-token [data token] (header (success data) "x-token" token))
(defn error [code] (status (response "") code))

(defroutes routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))

  (POST "/_ah/v1/login" {{username "username" password "password"} :body}
        (let [login (<!! (db/login username password))
              token (generate-token login)] (if token (success {:token token}) (error 401))))

  (POST "/_ah/v1/signin" {{username "username"
                           password "password"
                           confirm  "password-confirm"} :body} (db/signin username password))

;;   (GET "/agenda" {:keys headers :as request} (validate-token headers (fn [] ...)))
;;   (GET "/agenda/speech" {:keys headers :as request} (validate-token headers (fn [] ...)))
;;   (GET "/agenda/speech/:id" {:keys headers id :as request} (validate-token headers (fn [id] ...) id))
;;   (GET "/agenda/speech/:id/rating" {:keys headers id :as request} (validate-token headers (fn [id] ...) id))
;;   (POST "/agenda/speech/:id/rating" {:keys body id headers :as request} (validate-token headers (fn [body id] ...) body id))

;;   (GET "/cards" [] (resource-response "cards.html" {:root "public"}))
  (resources "/"))

(def app (-> routes
             wrap-json-body
             wrap-json-response))

(def dev-handler (-> #'app wrap-reload))

(def handler app)
