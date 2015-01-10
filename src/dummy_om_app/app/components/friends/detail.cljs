(ns dummy-om-app.app.components.friends.detail
  (:require [dummy-om-app.app.models.friend :as friend-model]
            [dummy-om-app.app.xhr           :as xhr]
            [om.core                        :as om  :include-macros true]
            [om.dom                         :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent friends-detail
  (will-mount
   (xhr/xhr-req {:method      :get
                 :url         "users/accounts"
                 :on-complete (fn [resp]
                                (om/transact! data [:users :accounts] (fn [_]
                                                                        resp)))}))
  (render
   (println "opts:" opts)
   (let [{:strs [username email]} (friend-model/locate-by-id (get-in data [:users :accounts])
                                                             (:id opts))]
     (dom/div #js {:id "friends-detail"}
              (dom/img #js {:className "friends-detail-img"
                            :src       "https://placekitten.com/g/80/80"})
              (dom/div nil (str username ", " email))))))
