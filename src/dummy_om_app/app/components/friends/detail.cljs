(ns dummy-om-app.app.components.friends.detail
  (:require [dummy-om-app.app.models.helper :as model-helper]
            [dummy-om-app.app.xhr           :as xhr]
            [om.core                        :as om  :include-macros true]
            [om.dom                         :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent friends-detail
  (will-mount
   (model-helper/fetch-from-db :users :accounts))
  (render
   (let [{:strs [username email avatar_url]} (model-helper/find-by-id data :users :accounts (:id opts))]
     (dom/div #js {:id "friends-detail"}
              (dom/img #js {:className "friends-detail-img"
                            :src       avatar_url})
              (dom/div nil (str username ", " email))))))
