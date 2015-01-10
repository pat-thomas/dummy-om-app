(ns dummy-om-app.server.core
  (:require [sham.core                  :as sham]
            [clojure.tools.nrepl.server :as nrepl]))

(defn init-webserver
  []
  (let [port 4321]
    (println (format "initializing webserver on port %s" port))
    (sham/init!
     {:port 4321})))

(defn init-nrepl-server
  []
  (let [port 5321]
    (println (format "initializing nrepl server on port %s" port))
    (nrepl/start-server :port 5321)))

(defn init!
  []
  (init-webserver)
  (init-nrepl-server))

(comment
  (sham/reload!
   {:port 4321})
  )
