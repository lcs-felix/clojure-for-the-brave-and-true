;; Implement the comp function.

(defn my-comp
  ([] identity)
  ([& fs]
   (fn [& args]
     (let [[f & fs] (reverse fs)]
       (reduce #(%2 %1)
               (apply f args) fs)))))

((my-comp inc +) 10 10)

;; attr fucntion

(defn attr
  [attribute]
  (comp attribute :attributes))

;; assoc-in function copied from clojure core:
;; https://github.com/clojure/clojure/blob/master/src/clj/clojure/core.clj#L6161

(defn assoc-in
  [m [k & ks] v]
  (if ks
    (assoc m k (assoc-in (get m k) ks v))
    (assoc m k v)))

;; Implement update-in function

(defn my-update-in
  [m ks f & args]
  (let [old-value (get-in m ks)
   new-value (apply f old-value args)]
    (assoc-in m ks new-value)))