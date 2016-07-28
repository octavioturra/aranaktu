(ns aranaktu.db
  (:require [postgres.async :refer :all]
            [aranaktu.config :as config]))

(def db (open-db config/database-config))

(defn signin [username password] (insert! db {:table "users" :returning "id"} [{:email username :password password}]))

(defn login [username password] (query! db ["select id from users where email=$1 and password=$2" username password]))

;; (defn query [query data] (apply sql/query (concat [query] data)))
