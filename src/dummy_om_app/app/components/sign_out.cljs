(ns dummy-om-app.app.components.sign-out
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent sign-out
  (render
   (dom/div nil "sign-out view will go here")))


