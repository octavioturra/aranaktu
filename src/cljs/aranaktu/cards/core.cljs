(ns example.core
  (:require [reagent.core :as reagent]
            [devtools.core :as devtools])
  (:require-macros
    [devcards.core :refer [defcard]]))

(devtools/install!)

(defcard my-first-card
  [:h1 "Devcards is freaking awesome!"])


(reagent/render [views/main-panel]
                (.getElementById js/document "cards"))
