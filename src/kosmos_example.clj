(ns kosmos-example
  (:require [kosmos.web :refer :all]
            [clojure.tools.reader.edn :as edn]
            [clj-http.client :as http]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]))

(defn test-ring-app [request]
  {:status 200
   :headers {"Content-Type" "application/edn"}
   :body (pr-str {:testing 123})})

(def config (edn/read-string (slurp "resources/web_config.edn")))

(defn -main [& args]
  (log/info (pr-str config))
  (let [my-ring-component (map->RingJettyComponent (:web config))]
    (component/start my-ring-component)))

