; dublare(l1...ln) = { (), daca n = 0
;                    { 2 * l1 (+) dublare(l2...ln), daca l1 e numar
;                    { l1 (+) dublare(l2...ln), daca l1 e atom
;                    { dublare(l1) (+) dublare(l2...ln), altfel


(defun dublare(l)
  (cond
    ((null l) nil)
    ((numberp (car l)) (cons (* 2 (car l)) (dublare (cdr l))))
    ((atom (car l)) (cons (car l) (dublare (cdr l))))
    (t (cons (dublare (car l)) (dublare (cdr l))))
  )
)
