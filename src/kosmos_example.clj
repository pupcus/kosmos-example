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


(defn read-config [filename]
  (edn/read-string (slurp "resources/web_config.edn")))
#_(def config (edn/read-string (slurp "resources/web_config.edn")))
;; ring-server (map->RingJettyComponent (:web config))


(defn base-system []
  (let [config (read-config "resources/web_config.edn")]
    (kosmos/map->system config)))

(defn application-system []
  (base-system))

(defn -main [& args]
  (kosmos/start! (application-system)))

