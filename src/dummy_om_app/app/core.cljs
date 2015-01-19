(ns dummy-om-app.app.core
  (:require [dummy-om-app.app.history        :as history]
            dummy-om-app.app.router
            [dummy-om-app.app.components.app :as app]
            [dummy-om-app.app.models.session :as session]
            [dummy-om-app.app.state          :as app-state]
            [om.core                         :as om  :include-macros true]))

(enable-console-print!)

(defn init!
  []
  (history/init!)
  (session/dispatch-on-session-status
   {"Authorized" (fn [_]
                   (om/root
                    app/app-root
                    app-state/app-state
                    {:target (. js/document (getElementById "my-app"))
                     :opts   {}}))
    "*"          (fn [_]
                   (history/redirect "login"))}))

(init!)
