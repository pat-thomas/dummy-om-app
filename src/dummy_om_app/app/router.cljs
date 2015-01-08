(ns dummy-om-app.app.router
  (:require [dummy-om-app.app.xhr            :as xhr]
            [dummy-om-app.app.components.app :as app]
            [dummy-om-app.app.state          :as app-state]
            [om.core                         :as om  :include-macros true]
            [om.dom                          :as dom :include-macros true]
            [secretary.core                  :as secretary :refer-macros [defroute]]
            [goog.events                     :as events]
            [goog.history.EventType          :as EventType])
  (:import goog.History))

(defn init!
  []
  (secretary/set-config! :prefix "#")
  (let [h (History.)]
    (events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
    (doto h (.setEnabled true))))

(defroute home "/"
  []
  (om/root app/home app-state/app-state {:target (. js/document (getElementById "my-app"))
                                         :opts   {}}))

(defroute "*"
  []
  (println "not-found"))




