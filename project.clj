(defproject kosmos-example "0.1.0-SNAPSHOT"
  :description "A simple example using some of kosmos's configurations."
  :url "TODO"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "2.1.0"]
                 [metosin/compojure-api "1.0.0"]
                 [kosmos/kosmos-web  "0.0.2"]
                 [kosmos/kosmos-nrepl "0.0.1"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]]
                   :source-paths ["dev"]}}
 
  :main kosmos-example/-main
  :repl-options {:init-ns user}
  )
