(ns dummy-om-app.app.components.friends.detail
  (:require [om.core              :as om  :include-macros true]
            [om.dom               :as dom :include-macros true]
            [dummy-om-app.app.xhr :as xhr])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent friends-detail
  (will-mount
   (println (:id opts))
   (xhr/xhr-req {:method      :get
                 :url         "users/accounts"
                 :on-complete (fn [resp]
                                (om/transact! data [:users :accounts] (fn [_]
                                                                        resp)))}))
  (render
   (println (get-in data [:users :accounts]))
   (let [{:strs [username email] :as user} (first
                                            (filter (fn [acct]
                                                      (= (get acct "id")
                                                         (:id opts)))
                                                    (get-in data [:users :accounts])))]
     (println user)
     (dom/div #js {:id "friends-detail"}
              (dom/img #js {:className "friends-detail-img"
                            :src       "https://placekitten.com/g/80/80"})
              (dom/div nil (str username ", " email))))))
