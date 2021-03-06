(ns dummy-om-app.app.components.messages
  (:require [dummy-om-app.app.models.helper :as model-helper]
            [om.core                        :as om  :include-macros true]
            [om.dom                         :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent message-detail
  (render
   (dom/div #js {:className "message-detail"}
            (str "Sender: " (get data "sender_id") ", " (get data "content")))))

(defcomponent messages
  (will-mount
   (model-helper/fetch-from-db :users :messages))
  (render
   (apply dom/div #js {:id "messages"}
          (map (fn [message-data]
                 (om/build message-detail message-data))
               (get-in data [:users :messages])))))
