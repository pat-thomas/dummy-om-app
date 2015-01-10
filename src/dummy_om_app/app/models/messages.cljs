(ns dummy-om-app.app.models.messages
  (:require [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.xhr   :as xhr]))

(defn fetch
  []
  (xhr/xhr-req
   {:method :get
    :url    "users/messages"
    :on-complete (fn [resp]
                   (swap! app-state/app-state assoc-in [:users :messages] resp))}))
