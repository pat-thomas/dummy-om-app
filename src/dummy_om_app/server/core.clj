(ns dummy-om-app.server.core
  (:require [sham.core :as sham]))

(sham/init!
 {:port 4321})

(comment
  (sham/reload!
   {:port 4321})
  )
