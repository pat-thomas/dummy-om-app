(ns dummy-om-app.app.components.app
  (:require [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.xhr   :as xhr]
            [dummy-om-app.app.util  :as util]
            [om.core                :as om  :include-macros true]
            [om.dom                 :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent home
  (will-mount
   (xhr/xhr-req {:method      :get
                 :url         "users/accounts"
                 :on-complete (fn [resp]
                                (om/update! data [:users :accounts] resp))}))
  (render
   (dom/div nil "hello")))
