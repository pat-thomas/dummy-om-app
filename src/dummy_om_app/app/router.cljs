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

(defn set-current-view!
  [current-view]
  (swap! app-state/app-state #(assoc % :current-view current-view)))

(defroute root "/"
  []
  (set-current-view! "home"))

(defroute home "/home"
  []
  (set-current-view! "home"))

(defroute messages "/messages"
  []
  (set-current-view! "messages"))

(defroute sign-out "/sign-out"
  []
  (set-current-view! "sign-out"))

(defroute friends "/friends"
  []
  (set-current-view! "friends"))

(defroute "*"
  []
  (println "not-found"))




