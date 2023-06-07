; atomi(l) = { (l), daca l e atom
;            { atomi(l1) (+) atomi(l2) (+) ... (+) atomi(ln), unde l=(l1...ln)
;


(defun atomi(l)
  (cond
    ((atom l) (list l))
    (t (apply #'append (mapcar #'atomi l)))
  )
)
