;; a simple (incomplete and eager) implementation of the map function using reduce

(defn my-map
  [f coll]
    (seq (reduce (fn [new-coll element] (conj new-coll (f element)))
               [] coll)))

;; an implementation of the filter function using reduce

(defn my-filter
  [pred coll]
  (sequence (reduce 
              (fn [new-coll element]
                 (if (pred element) 
                   (conj new-coll element) new-coll)) 
              [] coll)))

;; an implementation of the some function using reduce

(defn my-some
  [pred coll]
  (reduce (fn [init value]
            (let [result (or (pred value) init)]
              (if result (reduced result))))
          nil coll))