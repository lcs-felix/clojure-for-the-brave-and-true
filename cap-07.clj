;; using ' symbol, list and read-string functions to print my name and my favorite sci-fic movie

(eval '(println "Lucas," "Back to the future"))
(eval (list println "Lucas," "Back to the future"))
(eval (read-string "(println \"Lucas,\", \"Back to the future\")"))