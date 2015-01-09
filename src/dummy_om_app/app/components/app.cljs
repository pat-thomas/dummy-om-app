(ns dummy-om-app.app.components.app
  (:require [dummy-om-app.app.state               :as app-state]
            [dummy-om-app.app.util                :as util]
            [dummy-om-app.app.components.navbar   :as navbar]
            [dummy-om-app.app.components.messages :as messages]
            [dummy-om-app.app.components.home     :as home]
            [dummy-om-app.app.components.friends  :as friends]
            [dummy-om-app.app.components.sign-out :as sign-out]
            [om.core                              :as om  :include-macros true]
            [om.dom                               :as dom :include-macros true])
  (:require-macros [om-utils.core           :refer [defcomponent]]
                   [dummy-om-app.app.macros :refer [register-standard-route!]]))

(def routing-table
  {""         home/home
   "home"     home/home
   "friends"  friends/friends
   "messages" messages/messages
   "sign-out" sign-out/sign-out})

(defcomponent app-root
  (render
   (dom/div #js {:id "app"}
            (om/build navbar/navbar                            data {})
            (om/build (get routing-table (:current-view data)) data {}))))
