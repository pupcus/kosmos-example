(ns kosmos-example
  (:require [kosmos.web :refer :all]
            [clojure.tools.reader.edn :as edn]
            [clj-http.client :as http]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Pizza
  {:name s/Str
   (s/optional-key :description) s/Str
   :size (s/enum :L :M :S)
   :origin {:country (s/enum :FI :PO)
            :city s/Str}})

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

      (GET "/plus" []
        :return {:result Long}
        :query-params [x :- Long, y :- Long]
        :summary "adds two numbers together"
        (ok {:result (+ x y)}))

      (POST "/echo" []
        :return Pizza
        :body [pizza Pizza]
        :summary "echoes a Pizza"
        (ok pizza)))))


(defn test-ring-app [request]
  {:status 200
   :headers {"Content-Type" "application/edn"}
   :body (pr-str {:testing 123})})

#_(def config (edn/read-string (slurp "resources/web_config.edn")))

(defn application-system []
  (let [config (edn/read-string (slurp "resources/web_config.edn"))
        ring-server (map->RingJettyComponent (:web config))]
    (component/start ring-server)))

(defn -main [& args]
  (application-system))

