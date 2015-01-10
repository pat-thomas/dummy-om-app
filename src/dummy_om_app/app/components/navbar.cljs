(ns dummy-om-app.app.components.navbar
  (:require [dummy-om-app.app.util   :as util]
            [dummy-om-app.app.history :as history]
            [om.core                 :as om  :include-macros true]
            [om.dom                  :as dom :include-macros true]
            [secretary.core          :as secretary :refer-macros [defroute]])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defn navigate-to-app-route
  [el-name]
  (fn []
    (history/redirect
     (clojure.string/join "-" (map #(.toLowerCase %)
                                   (clojure.string/split el-name #" "))))))

(defcomponent navbar-link
  (render
   (let [{:keys [link-text]} opts]
     (dom/li #js {:className "navbar-list-item"
                  :onClick   (navigate-to-app-route link-text)}
             link-text))))

(defcomponent navbar
  (render
   (dom/header #js {:id "navbar"}
               (apply dom/ul #js {:id "navbar-list"}
                      (map (fn [n]
                             (om/build navbar-link data {:opts {:link-text n}}))
                           ;; because they are floated to the right, they have to be rendered in "reverse" order
                           ["Sign Out" "Messages" "Friends" "Home"])))))
