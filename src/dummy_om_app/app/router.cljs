(ns dummy-om-app.app.router
  (:require [dummy-om-app.app.xhr            :as xhr]
            [dummy-om-app.app.components.app :as app]
            [dummy-om-app.app.state          :as app-state]
            [dummy-om-app.app.history        :as history]
            [om.core                         :as om  :include-macros true]
            [om.dom                          :as dom :include-macros true]
            [secretary.core                  :as secretary :refer-macros [defroute]])
  (:require-macros [dummy-om-app.app.router :refer [current-view-route!]]))

(defn set-current-view!
  [current-view & [opts]]
  (if opts
    (swap! app-state/app-state #(assoc % :current-view {:view current-view
                                                        :opts opts}))
    (swap! app-state/app-state #(assoc % :current-view {:view current-view}))))

(current-view-route! home "/")
(current-view-route! home)
(current-view-route! messages)
(current-view-route! sign-out)
(current-view-route! friends)
(current-view-route! friends-detail "/friends/:id" [id])

(defroute "*"
  []
  (history/redirect "home"))
