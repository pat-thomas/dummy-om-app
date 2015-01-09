(ns dummy-om-app.app.components.messages
  (:require [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.xhr   :as xhr]
            [dummy-om-app.app.util  :as util]
            [om.core                :as om  :include-macros true]
            [om.dom                 :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent messages
  (render
   (dom/div nil "messages will go here")))
