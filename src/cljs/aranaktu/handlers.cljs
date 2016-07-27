(ns aranaktu.handlers
    (:require [re-frame.core :as re-frame]
              [aranaktu.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/register-handler
  :set-login-form-value
  (fn [db [field value]]
    (assoc-in db [:form-login field] #(-> value))))
