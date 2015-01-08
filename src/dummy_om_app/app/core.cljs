(ns dummy-om-app.app.core
  (:require [dummy-om-app.app.router :as router]
            [dummy-om-app.app.xhr    :as xhr]))

(enable-console-print!)

(defn init!
  []
  (router/init!))

(init!)
