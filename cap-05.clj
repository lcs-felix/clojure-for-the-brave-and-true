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

;; Function assoc-in copied from clojure core:
;; https://github.com/clojure/clojure/blob/master/src/clj/clojure/core.clj#L6161

(defn assoc-in
  [m [k & ks] v]
  (if ks
    (assoc m k (assoc-in (get m k) ks v))
    (assoc m k v)))

;; Function update-in

(defn my-update-in
  [m ks f & args]
  (let [old-value (get-in m ks)
   new-value (apply f old-value args)]
    (assoc-in m ks new-value)))