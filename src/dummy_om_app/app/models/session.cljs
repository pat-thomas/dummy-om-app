(ns dummy-om-app.app.models.session
  (:require [dummy-om-app.app.util  :as util]
            [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.xhr   :as xhr]))

(defn get-session
  []
  (xhr/xhr-req {:method :get
                :url    "session"
                :on-complete (fn [resp]
                               (swap! app-state/app-state assoc :session resp))}))

(defn dispatch-on-session-status
  [session-dispatch-table & [data]]
  (xhr/xhr-req
   {:method      :get
    :url         "session"
    :on-complete (fn [{:keys [status] :as resp}]
                   (when-let [handler (or (get session-dispatch-table status)
                                          (get session-dispatch-table "*"))]
                     (if data
                       (handler data)
                       (handler))))}))
