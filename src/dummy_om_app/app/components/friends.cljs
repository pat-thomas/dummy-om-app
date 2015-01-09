(ns dummy-om-app.app.components.friends
  (:require [om.core              :as om  :include-macros true]
            [om.dom               :as dom :include-macros true]
            [dummy-om-app.app.xhr :as xhr])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent friends
  (will-mount
   (xhr/xhr-req {:method      :get
                 :url         "users/accounts"
                 :on-complete (fn [resp]
                                (om/transact! data [:users :accounts] (fn [_]
                                                                        resp)))}))
  (render
   (apply dom/div #js {:id "user-list"}
          (mapv (fn [{:strs [username email]}]
                  (dom/ul nil
                          (dom/li nil (str "Username: " username))
                          (dom/li nil (str "Email: " email))))
                (get-in data [:users :accounts])))))
