(ns dummy-om-app.app.router
  (:require [dummy-om-app.app.xhr   :as xhr]
            [secretary.core         :as secretary :refer-macros [defroute]]
            [goog.events            :as events]
            [goog.history.EventType :as EventType])
  (:import goog.History))

(defn init!
  []
  (secretary/set-config! :prefix "#")
  (let [h (History.)]
    (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
    (doto h (.setEnabled true))))

(defroute home "/"
  []
  (xhr/xhr-req {:method      :get
                :url         "users/accounts"
                :on-complete (fn [resp]
                               (println resp))})
  (println "home"))

(defroute "*"
  []
  (println "not-found"))

;;(defroute "/users/:id")


