(ns dummy-om-app.app.components.navbar
  (:require [dummy-om-app.app.util   :as util]
            [dummy-om-app.app.history :as history]
            [om.core                 :as om  :include-macros true]
            [om.dom                  :as dom :include-macros true]
            [secretary.core          :as secretary :refer-macros [defroute]])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(defn link-text->app-url
  [el-name]
  (clojure.string/join "-" (map #(.toLowerCase %)
                                (clojure.string/split el-name #" "))))

(defcomponent navbar-link
  (render
   (println opts)
   (let [{:keys [link-text current-view]} opts
         app-url                          (link-text->app-url link-text)
         class-name                       (if (= app-url current-view)
                                            "navbar-list-item highlighted"
                                            "navbar-list-item")]
     (dom/li #js {:className class-name
                  :onClick   #(history/redirect app-url)}
             link-text))))

(defcomponent navbar
  (render
   (dom/header #js {:id "navbar"}
               (apply dom/ul #js {:id "navbar-list"}
                      (map (fn [n]
                             (om/build navbar-link data {:opts {:link-text    n
                                                                :current-view (:current-view opts)}}))
                           ;; because they are floated to the right, they have to be rendered in "reverse" order
                           ["Sign Out" "Messages" "Friends" "Home"])))))
