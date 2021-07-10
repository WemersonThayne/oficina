(ns oficina-vital.schema.order
  (:require [clj-time.core :as t]))

(defn order-factory [uuid amount products car client]
  {:uuid uuid
   :amount amount
   :products products
   :car car
   :client client
   :order-date t/now})
