; sterge(l) = { (), daca l e numar si l < 0
;             { (l), daca l e atom nenumeric sau numar > 0
;             { sterge(l1) (+) ... (+) sterge(l2), altfel, unde l=(l1...ln)
;
;

(defun sterge(l, k, niv)
  (cond
    ((and (atom l) (eq niv k  )) nil)
    ((atom l) (list l))
    (t (list (apply #'append (mapcar #'sterge l))))
  )
)
