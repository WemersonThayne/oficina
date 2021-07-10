(ns oficina-vital.schema.client)

(defn client-factory [uuid name phone cpf status]
  {:uuid uuid
   :name name
   :phone phone
   :cpf cpf
   :status status})
