(ns oficina-vital.helpers.helpers
  (:require [clojure.pprint :refer [pprint]]))

(defn log [msg]
  (pprint msg))
