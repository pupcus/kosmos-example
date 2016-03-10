(ns kosmos-example
  (:require [kosmos.web :refer :all]
            [clojure.tools.reader.edn :as edn]
            [clj-http.client :as http]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [kosmos]))

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Sample"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
      :tags ["api"]
      (GET "/multiply" []
        :return {:result Long}
        :query-params [x :- Long, y :- Long]
        :summary "multiply two numbers"
        (ok {:result (* x y)}))

      (GET "/plus" []
        :return {:result Long}
        :query-params [x :- Long, y :- Long]
        :summary "adds two numbers together"
        (ok {:result (+ x y)}))

      )))

(defn read-config [filename]
  (edn/read-string (slurp filename)))

(defn base-system []
  (let [config (read-config "resources/web_config.edn")]
    (kosmos/map->system config)))

(defn application-system []
  (kosmos/start! (base-system)))

(defn -main [& args]
  (application-system))
