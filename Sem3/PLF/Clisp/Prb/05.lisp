; inordine(l1...ln) = { (), daca n = 0
;                     { inordine(l2) (+) l1 (+) inordine(l3), altfel


(defun inordine(l)
  (cond
    ((null l) nil)
    (t (append (inordine (cadr l)) (list (car l)) (inordine (caddr l))))
  )
)
