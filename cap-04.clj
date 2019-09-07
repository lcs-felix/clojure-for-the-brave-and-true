;; a simple (incomplete and eager) implementation of map function using reduce

(defn my-map
  [f coll]
    (seq (reduce (fn [new-coll element] (conj new-coll (f element)))
               [] coll)))

;; an implementation of some function using reduce

(defn my-some
  [pred coll]
  (reduce (fn [init value]
            (let [result (or (pred value) init)]
              (if result (reduced result))))
          nil coll))