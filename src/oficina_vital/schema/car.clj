(ns oficina-vital.schema.car)

(defn car-factory [uuid client-uuid license-plate current-mileage status]
  {:uuid uuid
   :client-uuid     client-uuid
   :license-plate   license-plate
   :current-mileage current-mileage
   :status          status})
