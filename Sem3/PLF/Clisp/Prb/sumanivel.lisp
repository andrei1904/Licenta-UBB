; suma(l1...ln) = { 0, daca n = 0
;                 { l1 + suma(l2...ln), daca l1 numar
;                 { suma(l2...ln), daca l1 e atom si nu e numar
;                 { suma(l1) + suma(l2...ln), altfel


(defun suma(l)
  (cond
    ((null l) 0)
    ((numberp (car l)) (+ (car l) (suma (cdr l))))
    ((atom (car l)) (suma (cdr l)))
    (t (+ (suma (car l)) (suma (cdr l))))
  )
)
