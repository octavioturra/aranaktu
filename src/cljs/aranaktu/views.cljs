(ns aranaktu.views
    (:require [re-frame.core :as re-frame]))

(defn home [] [:div "Home"])

(defn login-checkbox-change [field] (fn [event]
                                   (re-frame/dispatch [:set-login-form-value
                                                        [field (-> event .-target .-checked)]])))

(defn login-field-change [field] (fn [event]
                                   (re-frame/dispatch [:set-login-form-value
                                                        [field (-> event .-target .-value)]])))

(defn handle-login [data] (fn [event] (.preventDefault event)(re-frame/dispatch [:login data])))

(defn handle-signin [data] (fn [event] (.preventDefault event)(re-frame/dispatch [:signin data])))

(defn login-box []
  (let [
         form-login (re-frame/subscribe [:form-login])
         signin (:signin @form-login)
        ]
    (fn [] [:form {:on-submit (if signin (handle-signin @form-login) (handle-login @form-login))}
      [:fieldset
        [:label "E-mail"]
        [:input {:type "email" :on-change (login-field-change :username)}]]
      [:fieldset
        [:label "Password"]
        [:input {:type "password" :on-change (login-field-change :password)}]]
      [:label {:for "no-user"}
       [:input {:type "checkbox"
                :id "no-user"
                :on-change (login-checkbox-change :signin)}] "não tenho usuário"]
      (when signin [:fieldset
        [:label "Confirm Password"]
        [:input {:type "password" :on-change (login-field-change :confirm-password)}]])
      [:button "Entrar"]])))

(defn agenda [] [:div "agenda"])

(defn speech [] [:div "speech"])

;; panels

(defn home-panel [] [:div "home"
                      [:a {:href "#speech/blah"} "speech"]
                      [login-box]])

(defn speech-panel [params] [:div params])

(defn initialize-panel []
  (api/initialize)
  [:h1 "inicializando"])

;; main

(defmulti panels first)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :speech-panel [[key & params]] [speech-panel params])
(defmethod panels :initialize-panel [] [initialize-panel])
(defmethod panels :default [_] [:div])

(defn show-panel
  [[panel-name & props]]
  [panels [panel-name props]])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
