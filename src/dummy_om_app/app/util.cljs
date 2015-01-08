(ns dummy-om-app.app.util
  (:require [cognitect.transit :as transit]))

(def ^:private r (transit/reader :json))
(def ^:private w (transit/writer :json))

(defn parse-json
  [raw-json]
  (transit/read r raw-json))

(defn write-json
  [clj-data]
  (transit/write w clj-data))
