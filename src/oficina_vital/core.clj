(ns oficina-vital.core
  (:gen-class)
  (:require [oficina-vital.server :as server]
            [io.pedestal.http :as http]))

(def system-map
       {::http/routes server/routes
        ::http/type   :jetty
        ::http/port   8890})

(defn prepare-system! [system-map]
  (server/start system-map))

(defn -main [& _]
  (prepare-system! system-map))