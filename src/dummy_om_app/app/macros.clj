(ns dummy-om-app.app.macros)

(defn register-standard-route!
  [^clojure.lang.Keyword route-name]
  (let [route-name-str (name route-name)]
    `(swap! dummy-om-app.app.components.app/routing-table
            assoc route-name-str (symbol (str route-name-str "/" route-name-str)))))
