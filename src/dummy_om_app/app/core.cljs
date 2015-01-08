(ns dummy-om-app.app.core
  (:require [dummy-om-app.app.router :as router]))

(enable-console-print!)

(defn init!
  []
  (router/init!))

(init!)
