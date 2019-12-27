;; Implement the comp function.

(defn my-comp
  ([] identity)
  ([& fs]
   (fn [& args]
     (let [[f & fs] (reverse fs)]
       (reduce #(%2 %1)
               (apply f args) fs)))))

((my-comp inc +) 10 10)

;; Function attr

(defn attr
  [attribute]
  (comp attribute :attributes))

;; Implement the assoc-in function

(defn my-assoc-in
  [m [k & ks] v]
  (let [keys (reverse ks)
        new-map (reduce (fn [inner-map key]
                          (assoc {} key inner-map))
                {(first keys) v} (rest keys))]
    (assoc m k new-map)))