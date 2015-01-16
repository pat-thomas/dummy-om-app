(ns dummy-om-app.app.components.messages.user-inbox
  (:require [dummy-om-app.app.history       :as history]
            [dummy-om-app.app.models.helper :as model-helper]
            [om.core                        :as om  :include-macros true]
            [om.dom                         :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent user-inbox
  (render
   (dom/div nil "ok!")))
