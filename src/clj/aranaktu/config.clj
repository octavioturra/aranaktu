(ns aranaktu.config
  (:require [aranaktu.locals :as locals]))

(def database-config {:hostname "elmer.db.elephantsql.com"
                  :port 5432 ; default
                  :database "lulcbcpp"
                  :username "lulcbcpp"
                  :password locals/db-password
                  :pool-size 25})

(def database-url "postgres://lulcbcpp:"
  locals/db-password
  "@elmer.db.elephantsql.com:5432/lulcbcpp")
