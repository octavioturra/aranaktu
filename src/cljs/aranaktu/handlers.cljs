(ns aranaktu.handlers
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:require [re-frame.core :as re-frame]
              [aranaktu.db :as db]
              [aranaktu.api :as api]
              [cljs.core.async :as async :refer [<!]]))

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
  (fn [db [_ [field value]]]
    (update-in db [:form-login field] #(-> value))))

(re-frame/register-handler
  :logged-in
  (fn [db [_ user]]
    (assoc db :user user)))

(re-frame/register-handler
  :login
  (fn [db [_ data]]
    (go (let [username (:username data)
          password (:password data)
          result (<! (api/login username password))]
      (js/console.log (clj->js result))))))

;; (re-frame/register-handler
;;   :signin
;;   (fn [db [_ data]]
;;     (api/signin data)))
