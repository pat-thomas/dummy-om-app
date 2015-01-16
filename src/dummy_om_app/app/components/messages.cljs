(ns dummy-om-app.app.components.messages
  (:require [dummy-om-app.app.history       :as history]
            [dummy-om-app.app.models.helper :as model-helper]
            [om.core                        :as om  :include-macros true]
            [om.dom                         :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defn navigate-to-user-inbox
  [sender]
  (fn []
    (history/redirect (str "message-inbox/user/" sender))))

(defcomponent user-inbox
  (render
   (dom/div #js {:className "content-area"
                 :onClick (navigate-to-user-inbox (:sender opts))}
            (dom/div #js {:className "sender-name"} (:sender opts))
            (dom/div nil (str (count data) " messages.")))))

(defcomponent message-detail
  (render
   (dom/div #js {:className "message-detail"}
            (str "Sender: " (get data "sender_id") ", " (get data "content")))))

(defn group-messages-by-sender
  [messages]
  (group-by #(get % "sender_id") messages))

(defcomponent messages
  (will-mount
   (model-helper/fetch-from-db :users :messages))
  (render
   (apply dom/div #js {:id "messages"}
          (map (fn [[sender messages-by-sender]]
                 (om/build user-inbox messages-by-sender {:opts {:sender sender}}))
               (-> data :users :messages group-messages-by-sender)))))
