(ns dummy-om-app.app.models.helper
  (:require [dummy-om-app.app.util :as util]))

(defn find-by
  [^java.util.Map data ^clojure.lang.Keyword key-path-one ^clojure.lang.Keyword key-path-two ^java.util.Map locator]
  (let [locator-keys (keys locator)
        matching     (filter (fn [candidate]
                               (let [selected-candidate (select-keys candidate locator-keys)]
                                 (println "attempting to match locator:" locator "to:" selected-candidate)
                                 (= locator selected-candidate)))
                             (get-in data [key-path-one key-path-two]))]
    (cond (empty? matching)
          nil

          (= (count matching) 1)
          (first matching)

          :return-all-matching
          matching)))

(defn find-by-id
  [^java.util.Map data ^clojure.lang.Keyword key-path-one ^clojure.lang.Keyword key-path-two ^java.lang.String id]
  (find-by data key-path-one key-path-two {"id" (util/parse-int id)}))
