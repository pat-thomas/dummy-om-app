(ns dummy-om-app.app.models.friend)

(defn locate-by-id
  [friend-list id]
  (let [matching (filterv (fn [friend]
                            (let [friend-id (get friend "id")]
                              (= (str friend-id) id)))
                          friend-list)]
    (when-not (empty? matching)
      (first matching))))
