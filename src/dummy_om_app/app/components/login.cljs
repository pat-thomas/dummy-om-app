(ns dummy-om-app.app.components.login
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent login
  (render
   (dom/div nil "this login view will go here")))
