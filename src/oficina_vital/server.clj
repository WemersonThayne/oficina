(ns oficina-vital.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]
            [ring.util.response :as ring-resp]))

(def common-interceptors-json [(body-params/body-params) http/json-body])

(defn- ok [body]
        {:status 200 :body body})

(defn- created [body]
  {:status 201 :body body})

(defn- not-found []
        {:status 404 :body "Not found\n"})


(defn- respond-hello [request]
    ;nm   (get-in request [:query-params :name])
  (let [resp "Hello, world!\n"]
       (if resp
       (ok resp)
       (not-found))))

(defn get-json
  [request]
  (ring-resp/response {:users [{:name "nome" } {:name "teste"} ]}))

(def ^:private base-url "/oficina/api/v1")

(def routes
    (route/expand-routes
      #{[(str base-url "/hello") :get respond-hello :route-name :hello]
        ["/oficina/api/v1/info"  :get #(str "teste") :route-name :info]
        ["/json" :get (conj common-interceptors-json `get-json) :route-name :json]}))

(defn start [system-map]
  (prn "Staring server...")
  (http/start (http/create-server system-map)))

(defonce server (atom nil))

(defn start-dev [system-map]
  (prn "Staring dev-server...")
  (reset! server
          (http/start (http/create-server
                        (assoc system-map ::http/join? false)))))

(defn stop-dev []
       (http/stop @server))

(defn restart [system-map]
        (stop-dev)
        (start-dev system-map))