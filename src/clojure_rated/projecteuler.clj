(ns clojure-rated.projecteuler
  (:require  [clojure.math.numeric-tower :as math]))

;; problem 1

(defn mod-3-5 [no] (or (= 0 (mod no 3))
                       (= 0  (mod no 5))))

(defn mult-3-5 [no] (reduce +
                            (filter mod-3-5
                                    (range 1
                                           no))))

;; problem 2

(defn fibo [x]
  (if (= x 0)
    1
    (if (= x 1)
      1
      (+ (fibo (dec x))
         (fibo (- x 2))))))

(defn sum-even-fibo [no]
  (reduce +
          (filter even?
                  (map fibo
                       (range 1
                              (inc no))))))


;; problem 3
;;not giving answer for very big number
(defn get-factors [no arr]
  (filter #(= 0 (mod no %)) arr))

(defn prime? [no]
  (= (count (get-factors no
                         (range 2
                                (math/sqrt no))))
     0))

(defn prime-factors [no]
  (filter prime?
          (get-factors no
                       (range 2
                              (math/sqrt no)))))
;(/ no 2)

(defn largest-prime-factor [no]
  (last (prime-factors no)))


;; problem 4

#_ (so lets see how to do
      what i thinking was double loop java way but there is a function which i will start with and i will send  )

(defn palindrome? [no]
  (= (str no)
     (apply str
            (reverse (str no)))))

;; problem 5

#_ (in this i need a number which will return true or false if it is divisible by all the numbers from 1 to 20 )

(defn evenly-divisible? [no]
  (= 20
     (count (take-while #(= 0
                        (mod no %))
                    (range 1 21)))))

(defn check [x]
  (if (evenly-divisible? x)
    x
    (recur (inc x))))

;; problem 6

(defn sum-of-square [no]
  (reduce +
          (map #(* % %)
               (range 1 (inc no)))))

(defn square-of-sum [no]
  (let [sum (reduce + (range 1
                             (inc no)))]
    (* sum sum)))


(defn diff [no]
  (- (square-of-sum no)
     (sum-of-square no)))

;; probelm 7

#_ (here i need a function which returns a list of prime numbers once this happens i need to pass a list upto 10001 and its done)

(defn isprime? [ar]
  ((fn [b a]
     (if (> b (math/sqrt a))
       true
       (if (zero? (mod a b))
         false
         (recur (inc b) a))))
   2 ar))

(defn prime-at-pos [cp pn cnt pos]
  (if (= pos cp)
    pn
    (if (isprime? cnt)
      (recur (inc cp)
             cnt
             (inc cnt)
             pos)
      (recur cp
             pn
             (inc cnt)
             pos))))

;; problem 8
(def no  7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450)

(defn digits [number] (map #(Character/digit % 10) (str number)))

(digits no)

(defn great-prod [prod lis]
  (if (< (count lis) 13)
    prod
    (let [new-prod (reduce *
                           (take 13 lis)) ] (if (> new-prod
                                                  prod)
                                             (recur new-prod (rest lis))
                                             (recur prod (rest lis))))))



;; problem 10

(defn sum-prime [sum pn no]
  (if (> pn no)
    sum
    (if (isprime? pn)
      (recur (+ sum pn) (inc pn) no)
      (recur sum (inc pn) no))))

;; problem 12

(defn get-tri [no]
  (reduce + (range 1 (inc no))))
