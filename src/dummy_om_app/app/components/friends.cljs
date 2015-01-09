(ns dummy-om-app.app.components.friends
  (:require [om.core              :as om  :include-macros true]
            [om.dom               :as dom :include-macros true]
            [dummy-om-app.app.history :as history]
            [dummy-om-app.app.xhr :as xhr])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defn navigate-to-friend
  [{:strs [id] :as data}]
  (fn []
    (history/redirect (str "friends/" id))))

(defcomponent friend-button
  (render
   (let [{:strs [username email]} data]
     (dom/ul #js {:className "friend-button"
                  :onClick   (navigate-to-friend data)}
             (dom/li nil (str "Username: " username))
             (dom/li nil (str "Email: " email))))))

(defcomponent friends
  (will-mount
   (xhr/xhr-req {:method      :get
                 :url         "users/accounts"
                 :on-complete (fn [resp]
                                (om/transact! data [:users :accounts] (fn [_]
                                                                        resp)))}))
  (render
   (apply dom/div #js {:id "user-list"}
          (mapv (fn [friend-button-data]
                  (om/build friend-button friend-button-data))
                (get-in data [:users :accounts])))))
