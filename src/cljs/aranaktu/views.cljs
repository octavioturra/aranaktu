(ns aranaktu.views
    (:require [re-frame.core :as re-frame]
              [aranaktu.api :as api]))

(defn home [] [:div "Home"])

(defn login-box [] [:form 
  [:fieldset 
    [:label "E-mail"]
    [:input {:type "email"}]]
  [:fieldset 
    [:label "Password"]
    [:input {:type "password"}]]
  [:button "Entrar"]
  [:label {:for "no-user"} [:input {:type "checkbox" :id "no-user"}] "não tenho usuário"]])

(defn agenda [] [:div "agenda"])

(defn speech [] [:div "speech"])

;; panels

(defn home-panel [] [:div "home" 
                      [:a {:href "#speech/blah"} "speech"] 
                      (login-box)])

(defn speech-panel [params] [:div params])

(defn initialize-panel [] 
  (api/initialize)
  [:h1 "inicializando"])

;; main

(defmulti panels first)
(defmethod panels :home-panel [_] [home-panel])
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
