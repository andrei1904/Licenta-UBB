; lungime(l) = { 1, daca l e atom
;              { lunime(l1) + lungime(l2) + ... + lungime(ln), unde l =(l1...ln)
;

(defun lungime(l)
  (cond
    ((atom l) 1)
    (t (apply #'+ (mapcar #'lungime l)))
  )
)
