(ns oficina-vital.handler.order.order-service
  (:require [oficina-vital.schema.order :as order]
            [ring.util.response :as ring-resp]))

(defn get-by-uuid [request]
  (prn request)
  (ring-resp/response {:uuid       (java.util.UUID/randomUUID)
                       :amount     3490.00
                       :products   [1 2 3 4 5]
                       :car        1
                       :client     1
                       :order-date clj-time.core/now}))

(defn get-all [request]
  (ring-resp/response [{:uuid  (java.util.UUID/randomUUID)
    :amount 3490.00
    :products [1 2 3 4 5]
    :car 1
    :client 1
    :order-date clj-time.core/now},
   {:uuid  (java.util.UUID/randomUUID)
    :amount 349.00
    :products [1 4 5]
    :car 3
    :client 2
    :order-date clj-time.core/now}]))

(defn create [order]
  (prn order)
  true)

(defn update [uuid order]
  (prn order)
  true)

(defn delete [uuid]
  (prn uuid)
  true)

(defn get [params]
  (prn params)
  (get-all))
