(ns dummy-om-app.app.core
  (:require [dummy-om-app.app.history        :as history]
            dummy-om-app.app.router
            [dummy-om-app.app.components.app :as app]
            [dummy-om-app.app.state          :as app-state]
            [om.core                         :as om  :include-macros true]))

(enable-console-print!)

(defn init!
  []
  (history/init!)
  (om/root
   app/app-root
   app-state/app-state
   {:target (. js/document (getElementById "my-app"))
    :opts   {}}))

(init!)
