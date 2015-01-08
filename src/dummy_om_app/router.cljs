(ns dummy-om-app.router
  (:require [secretary.core         :as secretary :refer-macros [defroute]]
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
  (println "home"))

(defroute "*"
  []
  (println "not-found"))

;;(defroute "/users/:id")


