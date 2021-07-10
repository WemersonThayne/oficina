(ns oficina-vital.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]
            [ring.util.response :as ring-resp]
            [oficina-vital.handler.order.order-service :as order-server]))

(def ^:private common-interceptors-json [(body-params/body-params) http/json-body])

(defn- ok [body]
        {:status 200 :body body})

(defn- created [body]
  {:status 201 :body body})

(defn- not-found []
        {:status 404 :body "Not found\n"})

(defn- ^:private get-info [request]
  (ring-resp/response {:version "1.0.0"}))

(def ^:private base-url "/oficina/api/v1")

(def routes
    (route/expand-routes
      #{[(str base-url "/info") :get (conj common-interceptors-json `get-info) :route-name :info]
        [(str base-url "/order") :get (conj common-interceptors-json `order-server/get-all)]
        [(str base-url "/order/:id") :get (conj common-interceptors-json `order-server/get-by-uuid)]}))

(defn start [system-map]
  (prn "Staring server...")
  (http/start (http/create-server system-map)))

(defonce server (atom nil))

(defn start-dev [system-map]
  (prn "Staring dev-server...")
  (reset! server
          (http/start
            (http/create-server
              (assoc system-map ::http/join? false)))))

(defn stop-dev []
       (http/stop @server))

(defn restart [system-map]
        (stop-dev)
        (start-dev system-map))