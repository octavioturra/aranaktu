(defproject aranaktu "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [org.clojure/core.async "0.2.385"]
                 [reagent "0.5.1"]
                 [binaryage/devtools "0.6.1"]
                 [re-frame "0.7.0"]
                 [secretary "1.2.3"]
                 [compojure "1.5.0"]
                 [yogthos/config "0.8"]
                 [devcards "0.2.1"]
                 [binaryage/devtools "0.7.2"]
                 [alaisi/postgres.async "0.8.0"]
                 [ring/ring-json "0.4.0"]
                 [ring "1.4.0"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-sassy "1.0.7"]
            [lein-asset-minifier "0.2.7"
             :exclusions [org.clojure/clojure]]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:http-server-root "public"
             :css-dirs ["resources/public/css"]
             :ring-handler aranaktu.handler/dev-handler}

  :sass {:src   "src/sass"
         :dst   "resources/public/css"
         :style :compressed}

  :hooks [leiningen.sass]

  :minify-assets
  {:assets
   {"resources/public/css/site.min.css" "resources/public/css/site.css"}}

  :profiles
  {:dev
   {:dependencies []
    :plugins      [[lein-figwheel "0.5.4-3"]]
    :env {:dev true}}

   :uberjar {:hooks [minify-assets.plugin/hooks]
                       :source-paths ["env/prod/clj"]
                       :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
                       :env {:production true}
                       :aot :all
                       :omit-source true}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "aranaktu.core/mount-root"}
     :compiler     {:main                 aranaktu.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :optimizations        :none
                    :source-map           true
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :jar true
     :compiler     {:main            aranaktu.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "devcards"
    :source-paths ["src/cljs"]
    :figwheel { :devcards true }
    :compiler { :main                "aranaktu.cards.core"
                :asset-path          "js/devcards/out"
                :output-to           "resources/public/js/devcards.js"
                :output-dir          "resources/public/js/devcards"
                :source-map-timestamp true }}

    ]}

  :main aranaktu.server

  :aot [aranaktu.server]

  :prep-tasks [["cljsbuild" "once" "min"] "compile"]
  )
