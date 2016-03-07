(ns kosmos-example
  (:require [kosmos.web :refer :all]
            [clojure.tools.reader.edn :as edn]
            [clj-http.client :as http]
            [com.stuartsierra.component :as component]))

(defn test-ring-app [request]
  {:status 200
   :headers {"Content-Type" "application/edn"}
   :body (pr-str {:testing 123})})

(defn init-fn []
  (println "whatup internet"))

(def config
  {:web
   {:kosmos/type :kosmos.web/RingJettyComponent
    :join? false
    :ring-app #'test-ring-app
    :port 1111}})

(defn -main [& args]
  (let [my-ring-component (map->RingJettyComponent (:web config))]
    (component/start my-ring-component)))

