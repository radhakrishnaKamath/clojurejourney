(ns clojure-rated.core
  (:gen-class))

(defn add [a b](+ a b))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (str "It was the panda" "in the library" "with the dust buster"))
  )

((fn [m]
   (map
    (fn f [n]
      (if (zero? n)
        0
        (if (= n 1)
          1
          (let [a (f (dec n))
                b (f (- n 2))]
            (+ a b)))))(range 1 (inc m)))
   )
 3)

((fn [arr] (map (fn [arr n]
                  (if (= (nth arr n) (nth arr (- (count arr) n)))
                    true
                    false
                    )
                  )arr range (count arr)) )[1 2 3 2 1])
((fn [arr] (map (fn s [x](if (coll? x)
                           (do (concat (s (first x)) (if(empty? (rest x)) (rest x)(s (rest x)))
                                       ))
                        [x])) arr))'('(1 2) 3 [4 5 6]
 ))

#_((fn [arr] (reduce (fn [a b] (if (= a b)
                               ()
                               (conj a b))) "" arr ))"Leeeerrrooyy")


(list (str "a" "A") (str "s" "S") (str "v" "V"))

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stat [no] (map #(% no) [sum avg]))

(stat [1 2 3 4])

(def heroes
 [{:a "batman" :r "bruce" }
  {:a "spidy" :r "peter"}
  {:a "thor" :r "thor"}]
  )

(map :a heroes)

(reduce (fn [new-map [key val]] (assoc new-map key (inc val))) {} {:mq 30 :m 10})

(assoc (assoc {} :max (inc 30)) :min (inc 10))


;defination of fn

(defn demon [arr] (str arr " is back!!!"))

(map demon ["rated" "detar"])

(map #(demon (second %)) {:nemesis "ben"})

(map #(demon (second %)) {"rated" "ben"})


; my map implemented using recursion
;this is done
(defn my-map [f arr new]
  (if (not(empty? arr))
    (my-map f (rest arr) (conj new (f (first arr))))
    new))

(my-map demon ["rated" "r"] [])

;map using reduce
((fn [f arra]
   (reduce
    (fn [new arr]
      (if (not (empty? arr))
                                        ; (list (f (rest arr)) (first arr))
        (conj new (f (first arr)))(f (rest arr))

        )) [] arra)) demon ["r" "s"])

;my-drop done
(defn my-drop [arr n]
  (if (zero? n)
    arr
    (my-drop (rest arr) (dec n))
    ))

(my-drop [1 2 3 4 5 6 7 8 9] 2)

;my-take done
(defn my-take [new arr n]
  (if (zero? n)
    new
    (my-take (conj new (first arr)) (rest arr) (dec n))))

(my-take [] [1 2 3 4 5 6 7 8 9] 4)

;trying
(list "r" "s")

;my-filter done
(defn my-filter [new arr f]
  (if (not (empty? arr))
    (if (f (first arr))
      (my-filter (conj new (first arr)) (rest arr) f)
      (my-filter new (rest arr) f))
    new))
(defn mod2 [x] (if (mod x 2)
                 true
                 false))
(my-filter []  [1 2 3 4 5 6 7] odd?)
(my-filter []  [1 2 3 4 5 6 7] mod2 )
(filter even? [1 2 3 4 5 6])

;my-reduce done
(defn my-reduce
  ([arr acc f]
   (if (not (empty? arr))
     (my-reduce (rest arr) (f acc (first arr)) f)
     acc))
  ([arr f]
   (my-reduce arr (f) f)
   #_(if (not (empty? arr))
     (my-reduce (my-drop arr 2) (f (first arr) (second arr)) f)
     (f))))

(reduce + [])

(my-reduce [] conj)

;my-drop-while done
(defn my-drop-while [f arr]
  (if (not (empty? arr))
    (if (f (first arr))
      (do (rest arr)(my-drop-while f (rest arr)))
      arr)
    arr))

(my-drop-while #(< % 8) [1 2 3 4 5 6])

(drop-while #(< % 8) [1 2 3 4 5 6])

(odd? (first [1 2 3 4 5]))

;my-take-while done
(defn my-take-while [new arr f]
  (if (not (empty? arr))
    (if (f (first arr))
      (my-take-while (conj new (first arr)) (rest arr) f)
      new)
    new))

(my-take-while [] [1 2 3 4 5 6] odd?)

(take-while odd? [1 2 3 4 5])

;my-some done
(defn my-some [f arr]
  (if (not (empty? arr))
    (if (f (first arr))
      true
      (some f (rest arr)))
    nil))

(my-some #(and (> % 2) %) [1 2 3 4])

(some #(> % 2) [1 2 3])

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])


(some #(if (> (:critter %) 3) %) food-journal)

(some #(when (> (:critter %) 3) %) food-journal)

;my-sort done
(defn my-remove-helper [f arr new] (if (or (empty? arr) (f (first arr)))
                                     (concat new
                                             (rest arr))
                                     (my-remove-helper f
                                                       (rest arr) (conj new
                                                                        (first arr)))))


(def arr [1 2 3 4 1])
(my-remove-helper (fn [x] (= x (apply min arr))) [1 2 3 4 1] [])

(defn my-remove [f arr] (my-remove-helper f arr []))


(defn my-sort [arr new] (if (empty? arr)
                          new
                          (do (let [min-arr (apply min arr)]
                                (recur (my-remove (fn [x] (= x min-arr)) arr)
                                         (conj new min-arr))))))

(my-sort [4 2 3 1 5 1 4 2] [])
(my-sort [] [])

(sort > [1 2 3 1 5])



;my-into
(defn my-into [new arr]
  (if (empty? arr)
    new
    (my-into (conj new (first arr)) (rest arr) )))

(my-into '(1 2 3) '(4 5 6))

(defn my-new-into [new arr] (apply conj new arr))

(my-new-into '() '(1 3 4 5))

(def arr [2 1 4 3 5])

(min arr)

(filter (fn [x] (not= x (min arr))) arr)

(apply < [1 2 3 4 5])


;(remove (min [1 2 3 4 5]) [1 2 3 4 5])
;my-sort-by

(defn mod-decider [a b] (if (<= (second a) (second b)) a b))

(defn mod-min-helper-helper [acc arr]
  (if (empty? arr)
    acc
    (recur (mod-decider acc (first arr)) (rest arr))  ))

(defn mod-min-helper [arr]
  (mod-min-helper-helper (mod-decider (first arr)
                                      (first arr))
                         (rest arr)))

(defn mod-min [& args]
  (if (empty? (rest args))
    (first args)
    (mod-min-helper args)))

(defn hash-map-helper [f arr new]
  (if (empty? arr)
    new
    (hash-map-helper f
                     (rest arr)
                     (into new {(first arr) (f (first arr))}))))

(defn my-hash-map [f arr] (hash-map-helper f arr {}))

(my-hash-map count ["aaa" "bb" "c" "dd"])


(defn mod-remove-helper [f arr new]
  (if (or (empty? arr) (f (first arr)))
    (concat new
            (rest arr))
    (mod-remove-helper f
                       (rest arr) (conj new
                                        (first arr)))))

(defn mod-remove [f arr] (mod-remove-helper f arr []))


(defn cust-sort-helper [map arr]
  (if (empty? map)
    arr
    (let [min-guy (apply mod-min map)]
      (recur (mod-remove (fn [x] (= x min-guy))
                         map)
             (conj arr
                   (first min-guy))) )))

(defn cust-sort [map] (cust-sort-helper map []))

(cust-sort (vec {1 2,3 4, 5 6}))



(vec {1 2, 3 4, 5 6})

(defn my-sort-by [f arr]
  (cust-sort (my-hash-map f arr)))


;my-concat done
#_(see let me decide what i have to do so the first question is to how to get a single element and call into and pass new and first of all arr and then it will be calling until all the arrays get empty so

       1. take 2 arg new and list of all args
       2. send new and first of that list and in return you will get new with conj
       3. call itself with new NEW and rest of list until list gets empty)

(defn my-concat-helper [new arr]
  (if (empty? arr)
    new
    (my-concat-helper (my-into new (first arr)) (rest arr)) ))

(defn my-concat [& arr]
  (my-concat-helper [] arr))

(my-concat [1 2 3 5] [1 2 3 4] [2 3 4])

(concat [1 2 3 4] [1 2 4 4 5 6])
(concat '(1 2 3 4) '(1 2 3 4 5 1))

;my-min done
#_(see the problem is i have a list with atleast 2 numbers now i need a function which compares these 2 numbers and return a small number and then if the list gets empty then i need return this value else i need drop 2 elements from list and pass first and this small no to the decider function now my helper has list so i can decide the acc as first compared with itself and then recurse like sending acc and rest list)

(defn decider [a b] (if (< a b) a b))

(defn my-min-helper-helper [acc arr] (if (empty? arr)
                                       acc
                                       (recur (decider acc (first arr)) (rest arr))  ))

(defn my-min-helper [arr] (my-min-helper-helper (decider (first arr) (first arr)) (rest arr)))



(defn my-min [& args] (if (empty? (rest args))
                        (first args)
                        (my-min-helper args)
                        ))

(my-min 3 4 5 1 3)

;my-complement done

(defn my-complement [f] (fn [& args] (not (apply f args))))

(def a (my-complement <))

(a 3 2)

(def s (complement <))

(s 2 3)

;my-partial

(defn my-partial [f & args] (fn [& margs]
                              (apply f (into args
                                             margs))) )

(def mult2)
