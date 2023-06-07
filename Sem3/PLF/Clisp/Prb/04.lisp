; parcurg_aux(l1...ln, k, col) = { (), daca n = 0
;                                { (col, l1...ln), daca k = 0
;                                { parcurg_aux(l2...ln, k - 1, l1 (+) col), altfel
;
; parcurg(l1...ln, k) = parcurg_aux(l1...ln, k, ())

(defun parcurg_aux(L k col)
  (cond
    ((null L) nil)
    ((= k 0) (list col L))
    (t (parcurg_aux (cdr L) (- k 1) (cons (car l) col)))
  )
)

(defun parcurg (L k)
  (parcurg_aux L k nil)
)
