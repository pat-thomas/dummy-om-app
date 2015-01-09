(ns dummy-om-app.app.components.friends.detail
  (:require [om.core              :as om  :include-macros true]
            [om.dom               :as dom :include-macros true]
            [dummy-om-app.app.xhr :as xhr])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent friends-detail
  (render
   (dom/div #js {:id "friends-detail"}
            (dom/img #js {:className "friends-detail-img"
                          :src       "https://placekitten.com/g/80/80"}))))
