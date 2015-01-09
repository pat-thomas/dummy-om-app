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
     (dom/li #js {:className "navbar-el"
                  :onClick   (navigate-to-app-route link-text)}
             link-text))))

(defcomponent navbar
  (render
   (apply dom/ul #js {:id "navbar"}
          (map (fn [n]
                 (om/build navbar-link data {:opts {:link-text n}}))
               ["Home" "Friends" "Messages" "Sign Out"]))))
