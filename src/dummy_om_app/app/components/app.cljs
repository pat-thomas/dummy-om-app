(ns dummy-om-app.app.components.app
  (:require [dummy-om-app.app.state                     :as app-state]
            [dummy-om-app.app.util                      :as util]
            [dummy-om-app.app.history                   :as history]
            [dummy-om-app.app.models.session            :as session]
            [dummy-om-app.app.components.navbar         :as navbar]
            [dummy-om-app.app.components.messages       :as messages]
            [dummy-om-app.app.components.home           :as home]
            [dummy-om-app.app.components.friends        :as friends]
            [dummy-om-app.app.components.friends.detail :as friends-detail]
            [dummy-om-app.app.components.sign-out       :as sign-out]
            [om.core                                    :as om  :include-macros true]
            [om.dom                                     :as dom :include-macros true])
  (:require-macros [om-utils.core           :refer [defcomponent]]
                   [dummy-om-app.app.macros :refer [register-standard-route!]]))

(def routing-table
  {""               home/home
   "home"           home/home
   "friends"        friends/friends
   "friends-detail" friends-detail/friends-detail
   "messages"       messages/messages
   "sign-out"       sign-out/sign-out})

(defcomponent app-root
  (will-mount
   (session/dispatch-on-session-status
    {"NotAuthorized" (fn [_]
                       (history/redirect "login"))
     "*"             (fn [_]
                       :no-op)}))
  (render
   (dom/div #js {:id "app"}
            (om/build navbar/navbar                            data {})
            (println "Rendering" (get-in data [:current-view :view]))
            (dom/div #js {:id "main-content"}
                     (let [current-view (:current-view data)]
                       (om/build (get routing-table (:view current-view)) data {:opts (:opts current-view)}))))))
