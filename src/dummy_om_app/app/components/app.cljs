(ns dummy-om-app.app.components.app
  (:require [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.xhr   :as xhr]
            [dummy-om-app.app.util  :as util]
            [dummy-om-app.app.components.navbar :as navbar]
            [om.core                :as om  :include-macros true]
            [om.dom                 :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent home
  (will-mount
   (xhr/xhr-req {:method      :get
                 :url         "users/accounts"
                 :on-complete (fn [resp]
                                (om/transact! data [:users :accounts] (fn [_]
                                                                        resp)))}))
  (render
   (apply dom/div #js {:id "user-list"}
          (om/build navbar/navbar data)
          (mapv (fn [{:strs [username email]}]
                  (dom/ul nil
                          (dom/li nil (str "Username: " username))
                          (dom/li nil (str "Email: " email))))
                (get-in data [:users :accounts])))))
