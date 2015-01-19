(ns dummy-om-app.app.xhr
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [goog.events           :as events]
            [dummy-om-app.app.util :as util]
            [cljs.core.async       :as async])
  (:import [goog.net XhrIo]
           goog.net.EventType
           [goog.events EventType]))

(def ^:private meths
  {:get    "GET"
   :post   "POST"
   :put    "PUT"
   :delete "DELETE"})

(def ^:private api-base-url "http://localhost:3141/api/")

(defn xhr-req
  [{:keys [method url data on-complete]}]
  (let [xhr      (XhrIo.)
        xhr-chan (async/chan 1)]
    (events/listen xhr goog.net.EventType.COMPLETE
                   (fn [e]
                     (async/put! xhr-chan (on-complete (util/parse-json (.getResponseText xhr))))))
    (go (. xhr
           (send (str api-base-url url) (meths method) (when data (util/write-json data))
                 #js {"Content-Type" "application/json"}))
        (async/<! xhr-chan))))
