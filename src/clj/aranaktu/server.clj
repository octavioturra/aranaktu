(ns aranaktu.server
  (:require [aranaktu.handler :refer [handler]]
            [config.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

 (defn -main [& [port]]
   (let [port (Integer. (or port (env :port) 5000))]
     (run-jetty handler {:port port :join? false})))
