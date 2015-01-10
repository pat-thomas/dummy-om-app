(ns dummy-om-app.app.components.messages
  (:require [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.models.messages :as messages-model]
            [dummy-om-app.app.xhr   :as xhr]
            [dummy-om-app.app.util  :as util]
            [om.core                :as om  :include-macros true]
            [om.dom                 :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent message-detail
  (render
   (dom/div #js {:className "message-detail"}
            (str "Sender: " (get data "sender_id") ", " (get data "content")))))

(defcomponent messages
  (will-mount
   (messages-model/fetch))
  (render
   (apply dom/div #js {:id "messages"}
          (map (fn [message-data]
                 (om/build message-detail message-data))
               (get-in data [:users :messages])))))
