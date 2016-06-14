(defproject text-nodes "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure            "1.9.0-alpha4"]
                 [datascript                           "0.13.3"]
                 [posh                                  "0.3.5"]
                 [org.clojure/clojurescript           "1.9.36"
                                          :scope     "provided"]
                 [alandipert/storage-atom               "2.0.1"]
                 [re-com                                "0.8.3"]
                 [reagent "0.5.1"]
                 [reagent-utils                         "0.1.7"]
                 [com.rpl/specter                      "0.11.2"]
                 [keybind                               "2.0.0"]
                 [re-frame "0.7.0"]
                 [garden "1.3.2"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-garden "0.2.6"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css"]

  :figwheel {:css-dirs     ["resources/public/css"]}

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   text-nodes.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}
    :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "text-nodes.core/mount-root"}
     :compiler     {:main                 text-nodes.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            text-nodes.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]}



  :profiles
  {:dev
   [{:dependencies [[org.clojure/test.check "0.9.0"]]}
    {:plugins [[lein-figwheel "0.5.3"]]}]})
