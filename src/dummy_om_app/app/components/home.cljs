(ns dummy-om-app.app.components.home
  (:require [dummy-om-app.app.state :as app-state]
            [dummy-om-app.app.xhr   :as xhr]
            [dummy-om-app.app.util  :as util]
            [dummy-om-app.app.models.session :refer [dispatch-on-session-status]]
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

(defcomponent home-component
  (render
   (apply dom/div nil
          (map (fn [{:keys [render-fn text js-opts]}]
                 (dom/div nil (render-fn js-opts text)))
               home-text))))

(defcomponent home-not-authorized
  (render
   (dom/div nil "you are not authorized")))

(defcomponent home
  (render
   (dispatch-on-session-status
    {"Authorized" (fn [_]
                    (om/build home-component data))
     "*"          (fn [_]
                    (om/build home-not-authorized data))})))
