(ns dummy-om-app.server.core
  (:require [sham.core :as sham]))

(sham/init!
 {:port 4321})
