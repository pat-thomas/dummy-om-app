(ns dummy-om-app.app.components.navbar
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defcomponent navbar
  (render
   (apply dom/ul #js {:id "navbar"}
          (map (fn [n]
                 (dom/li #js {:className "navbar-el"} n))
               ["Home" "Messages" "Sign Out"]))))
