; apmap(e, l) = { 1, daca l = e
;               { 0, daca l e atom
;               { apmap(e, l1) + ... + apmap(e, ln), unde l=(l1...ln)

(defun apmap(e l)
  (cond
    ((equal e l) 1)
    ((atom l) 0)
    (t (apply #'+ (mapcar #'(lambda (l) (apmap e l))l)))
  )
)



; ap(e, l1...ln) = { 1 + ap(e, l2...ln), daca l1 = e
;                  { ap(e, l2...ln), daca l1 e atom
;                  { ap(e, l1) + ap(e, l2...ln), altfel

(defun ap(e l)
  (cond
    ((equal (car l) e) (+ 1 (ap e (cdr l))))
    ((atom (car l)) (ap e (cdr l)))
    (t (+ (ap e (car l)) (ap e (cdr l))))
  )
)
