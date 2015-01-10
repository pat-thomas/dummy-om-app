(ns dummy-om-app.app.components.home
  (:require [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.xhr   :as xhr]
            [dummy-om-app.app.util  :as util]
            [om.core                :as om  :include-macros true]
            [om.dom                 :as dom :include-macros true])
  (:require-macros [om-utils.core :refer [defcomponent]]))

(def home-text
  [{:render-fn dom/div
    :text      "Welcome to the app!"}
   {:render-fn dom/div
    :text      "It doesn't do much yet... but you can read about the technologies I used to build it below."}
   {:render-fn dom/a
    :text      "Om (client side)"
    :js-opts   #js {:href   "https://github.com/swannodette/om"
                    :target "_none"}}
   {:render-fn dom/a
    :text      "Sham (server side)"
    :js-opts   #js {:href   "https://github.com/pat-thomas/sham"
                    :target "_none"}}])

(defcomponent home
  (render
   (apply dom/div nil
          (map (fn [{:keys [render-fn text js-opts]}]
                 (dom/div nil (render-fn js-opts text)))
               home-text))))
