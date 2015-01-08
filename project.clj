(defproject dummy-om-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "1.0.4"]]
  :source-paths ["src"]
  :dependencies [[org.clojure/clojure       "1.6.0"]
                 [org.clojure/clojurescript "0.0-2665"]
                 [om                        "0.8.0-rc1"]
                 [om-utils                  "0.4.0"]
                 [secretary                 "1.2.1"]]
  :cljsbuild {:builds [{:id           "development"
                        :source-paths ["src"]
                        :compiler     {:output-to     "dummy_om_app.js"
                                       :output-dir    "out"
                                       :optimizations :none
                                       :source-map    true}}
                       {:id           "production"
                        :source-paths ["src"]
                        :compiler     {:output-to     "dummy_om_app_prod.js"
                                       :optimizations :advanced
                                       :pretty-print  false
                                       :preamble      ["react/react.min.js"]
                                       :externs       ["react/externs/react.js"]}}]})
