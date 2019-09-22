;; a simple (incomplete and eager) implementation of the map function using reduce

(defn my-map
  [f coll]
    (seq (reduce (fn [new-coll element] (conj new-coll (f element)))
               [] coll)))

;; an simple (eager) implementation of the filter function using reduce

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

;; 1

(defn glitter-filter
  [minimum-glitter records]
  (map #(:name %)
       (filter #(>= (:glitter-index %) minimum-glitter) records)))

;; 2

(defn append
  [coll new-item]
  (conj (into [] coll) new-item))
;; (append (glitter-filter 3 (mapify (parse (slurp filename))))
;;          {:name "Zé Vampir", :glitter-index 5})

;; 3

(defn validate
  [validating-functions record]
  (reduce (fn [errors-list [error-key validate-function]]
            (if (not (validate-function record))
              (conj errors-list error-key)
              errors-list))
          [] validating-functions))

(defn append
  [coll new-item]
  (let [errors-list (validate rules new-item)]
    (if (empty? errors-list)
      (conj (into [] coll) new-item)
      errors-list)))

;; 4

(defn to-csv
  [records]
  (reduce #(str %1 %2 "\n")
          ""
          (map (fn [record]
                 (str/join "," (vals record))) records)))