;; 1
(str "Farinha, "  "rapadura " " e graviola")

(def foods ["farinha", "rapadura", "graviola"])
(conj foods "manteiga da terra")

(def list-food '("farinha", "rapadura", "graviola"))
(conj list-food "manteiga da terra")

;; 2

(defn inc-100
  [number]
  (+ number 100))

(inc-100 100)
(inc-100 0)

;; 3

(defn dec-maker
  [dec-value]
  #(- % dec-value))

(def dec9 (dec-maker 9))

(dec9 10)

;; 4

(defn mapset
  [f coll]
  (loop [remaining-collection coll final-set (sorted-set)]
    (if (empty? remaining-collection)
      final-set
      (let [[element & remaining] remaining-collection]
        (recur remaining
               (conj final-set
                     (f element)))))))

(mapset inc [1 1 2 2])

;; 5

(def asym-alien-parts
  [{:name "head" :size 10}
   {:name "eye-1" :size 1}
   {:name "arm-1" :size 4}])

(defn generate-alien-parts
  [part]
  (sort-by :name
           (set (map (fn [part-number]
                             {:name (clojure.string/replace
                                      (:name part) #"-1$" part-number)
                              :size (:size part)}) ["-1" "-2", "-3", "-4", "-5"]))))

(defn symmetrize-body-parts
  [asym-parts]
  (reduce (fn [final-sym-parts part]
            (into final-sym-parts (generate-alien-parts part)))
          [] asym-parts))

(symmetrize-body-parts asym-alien-parts)

;; 6

