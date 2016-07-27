(ns aranaktu.api
    (:require [matchbox.core :as m]
              [aranaktu.locals :as locals]))

(def root (m/connect locals/database-URL))

(defn create-user [username password] (m/create-user root username password))
(defn login [username password] (m/auth root email password))

(defn initialize [] (let [base-data locals/base-data
                          v1 (m/get-in root [:v1])] 
                          (m/reset! v1 base-data)))