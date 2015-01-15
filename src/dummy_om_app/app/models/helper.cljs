(ns dummy-om-app.app.models.helper
  (:require [dummy-om-app.app.util  :as util]
            [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.xhr   :as xhr]))

(defn fetch-from-db
  [^clojure.lang.Keyword schema ^clojure.lang.Keyword table & [req-opts resp-handler]]
  (let [on-complete (fn [resp]
                      (swap! app-state/app-state assoc-in [schema table] (if resp-handler
                                                                           (resp-handler resp)
                                                                           resp)))
        params      {:method      :get
                     :url         (str (name schema) "/" (name table))
                     :on-complete on-complete}
        params      (if req-opts
                      (assoc params :data req-opts)
                      params)]
    (xhr/xhr-req params)))

(defn find-by
  [^java.util.Map data ^clojure.lang.Keyword key-path-one ^clojure.lang.Keyword key-path-two ^java.util.Map locator]
  (let [locator-keys (keys locator)
        matching     (filter (fn [candidate]
                               (= locator (select-keys candidate locator-keys)))
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
