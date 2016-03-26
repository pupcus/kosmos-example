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
        (ok {:result (+ x y)})))))

(defn ^:private read-config
  "Read the file in and parse it as edn."
  [filename]
  (edn/read-string (slurp filename)))

(defn ^:private base-system
  "Pass the map we read from the config file to kosmos and setup as base system."
   []
   (let [config (read-config "resources/config.edn")]
    (kosmos/map->system config)))

(defn application-system
  "Use kosmos to start the application." 
  []
  (kosmos/start! (base-system)))

(defn -main
  "Start the application."
  [& args]
  (application-system))
