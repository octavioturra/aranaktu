(ns aranaktu.config
  (:require [aranaktu.locals :as locals]))

(def database-config {:hostname "elmer.db.elephantsql.com"
                  :port 5432 ; default
                  :database "lulcbcpp"
                  :username "lulcbcpp"
                  :password locals/database-password
                  :pool-size 25})

(def database-url (str "postgres://lulcbcpp:"
  locals/database-password
  "@elmer.db.elephantsql.com:5432/lulcbcpp"))
