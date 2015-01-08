(ns dummy-om-app.core
  (:require [dummy-om-app.router :as router]))

(enable-console-print!)

(defn init!
  []
  (router/init!))

(init!)
