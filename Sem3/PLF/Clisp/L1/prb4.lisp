; nrpar(l1...ln) = { adevarat, daca n = 0
;                  { fals, daca n = 1
;                  { nrpar(l3...ln), altfel

(DEFUN NRPAR (L)
  (COND
    ((NULL L) T)
    ((NULL (CDR L)) NIL)
    (T (NRPAR (CDDR L)))
  )
)
