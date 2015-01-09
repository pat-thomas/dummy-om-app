(ns dummy-om-app.app.router
  (:require [dummy-om-app.app.xhr            :as xhr]
            [dummy-om-app.app.components.app :as app]
            [dummy-om-app.app.state          :as app-state]
            [dummy-om-app.app.history        :as history]
            [om.core                         :as om  :include-macros true]
            [om.dom                          :as dom :include-macros true]
            [secretary.core                  :as secretary :refer-macros [defroute]]))

(defn set-current-view!
  [current-view]
  (swap! app-state/app-state #(assoc % :current-view current-view)))

(defroute root "/"
  []
  (history/redirect "home"))

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

(defroute friends-detail "/friends/:id"
  [id]
  (set-current-view! "friends-detail"))

(defroute "*"
  []
  (history/redirect "home"))




