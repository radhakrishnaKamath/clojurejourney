(ns clojure-rated.core-test
 (:require #_[clojure.test :refer :all]
           [expectations :as expect]
           [clojure-rated.core :refer :all]))

#_(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(expect/expect 2 (+ 1 1))

(expect/expect true (my-some #(> % 2) [1 2 3 4]))

;test for my-map

(expect/expect [" is back!!!"] (my-map demon [""]))

(expect/expect [" is back!!!"] (my-map-helper demon [""] []))

(expect/expect [1 2 3 4] (my-map inc [0 1 2 3]))

(expect/expect '(2 3 4) (my-drop '(0 1 2 3 4) 2))

(expect/expect [2 3 4] (my-drop '(0 1 2 3 4) 2))

(expect/expect [0 1 2 3 4] (my-drop '(0 1 2 3 4) -1))

(expect/expect '(2 3 4) (my-drop [0 1 2 3 4] 2))

(expect/expect '(1 0) (my-take '() '(0 1 2 3 4) 2))

(expect/expect '() (my-take '() '(0 1 2 3 4) -1))

(expect/expect [0 1] (my-take [] '(0 1 2 3 4) 2))

(expect/expect '(1 0) (my-take '() [0 1 2 3 4] 2))

(expect/expect [1 3 5] (my-filter [] [1 2 3 4 5 6] odd?))

(expect/expect [2 4 6] (my-filter [] [1 2 3 4 5 6] even?))

(expect/expect 21 (my-reduce [1 2 3 4 5 6] +))

(expect/expect 1 (my-reduce [] *))

(expect/expect [] (my-drop-while #(< % 8) [1 2 3 4 5 6]))

(expect/expect [1 2 3 4 5 6] (my-drop-while #(> % 2) [1 2 3 4 5 6]))

(expect/expect [3 4 5 6] (my-drop-while #(< % 3) [1 2 3 4 5 6]))

(expect/expect [] (my-take-while [] [1 2 3 4 5 6] even?))

(expect/expect [1] (my-take-while [] [1 2 3 4 5 6] odd?))

(expect/expect [1 2] (my-take-while [] [1 2 3 4 5 6] #(< % 3)))

(expect/expect '() (my-take-while [] [1 2 3 4 5 6] #(> % 7)))

(expect/expect true (my-some even? [1 2 3 4 5 6] ))

(expect/expect nil (my-some even? [1 3 5] ))

(expect/expect [1 2 3 4] (my-remove-helper (fn [x] (= x (apply max [1 2 3 4 5]))) [1 2 3 4 5] []))

(expect/expect [1 2 3 4] (my-remove (fn [x] (= x (apply max [1 2 3 4 5]))) [1 2 3 4 5]))

(expect/expect [1 2 3 4 5 6] (my-sort [6 5 4 3 2 1] [] ))

(expect/expect [1 1 2 3 3 4 5 6] (my-sort [6 5 4 3 2 3 1 1] [] ))

(expect/expect [] (my-sort [] [] ))

(expect/expect [1 2 3 4] (my-into [1 2] [3 4]))

(expect/expect '(4 3 1 2) (my-into '(1 2) [3 4]))

(expect/expect [1 2] (mod-decider [1 2] [3 4]))

(expect/expect [1 2] (mod-decider [3 4] [1 2]))

(expect/expect [0 1] (mod-min-helper-helper [1 2] [[3 4] [0 1]]))

(expect/expect [0 1] (mod-min-helper [[1 2] [0 1] [3 4]]))

(expect/expect [0 1] (mod-min [1 2] [3 4] [0 1]))

(expect/expect {"a" 1,"bb" 2,"ccc" 3,"d" 1,"ee" 2} (hash-map-helper count ["a" "bb" "ccc" "d" "ee"] {}))

(expect/expect {"a" 1,"bb" 2,"ccc" 3,"d" 1,"ee" 2} (my-hash-map count ["a" "bb" "ccc" "d" "ee"]))

(expect/expect [[1 2] [3 4]] (mod-remove-helper (fn [x] (= x (apply mod-min [[1 2] [3 4] [0 1]]))) [[1 2] [3 4] [0 1]] []))

(expect/expect [[1 2] [3 4]] (mod-remove (fn [x] (= x (apply mod-min [[1 2] [3 4] [0 1]]))) [[1 2] [3 4] [0 1]]))

(expect/expect [0 1 3] (cust-sort-helper [[1 2] [3 4] [0 1]] []))

(expect/expect [0 1 3] (cust-sort [[1 2] [3 4] [0 1]]))

(expect/expect ["a" "bb" "dd" "ccc" "eee"] (my-sort-by count ["ccc" "bb" "a" "eee" "dd"]))

(expect/expect [1 2 3 4 0 1] (my-concat-helper [] [[1 2] [3 4] [0 1]]))

(expect/expect [1 2 3 4 0 1] (my-concat [1 2] [3 4] [0 1]))

(expect/expect 1 (decider 1 2))

(expect/expect 0 (my-min-helper-helper 1 '(2 3 4 0 1)))

(expect/expect 0 (my-min-helper '(1 2 3 4 0 1)))

(expect/expect 1 (my-min 1 2 3 4 1 2))

(expect/expect {:foo 1} (expect/in {:foo 1 :cat 2}))

(expect/expect :foo (expect/in #{:foo :cat}))

(expect/expect :foo (expect/in [:foo :cat]))

(expect/expect 4 ((comp inc +) 2 1))

;(expect/expect '(* + inc) (my-comp-rev '(inc + *)) )

;(expect/expect 4 ((my-comp-helper '(+ inc)) 2 1))

(expect/expect 4 (my-comp-helper-helper [* inc] 3))

(expect/expect 7 ((my-comp inc + *) 1 2 3))
