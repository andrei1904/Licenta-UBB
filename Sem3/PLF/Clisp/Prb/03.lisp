; lista(l1...ln) = { (), daca n = 0
;                  { l1 (+) (l2...ln), daca l1 e numar
;                  { lista(l2...ln), altfel
;


(defun lista (l)
  (cond
    ((null l) nil)
    ((numberp (car l)) (cons (car l) (lista (cdr l))))
    (t (lista (cdr l)))
  )
)
