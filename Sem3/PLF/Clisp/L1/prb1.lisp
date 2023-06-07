; prod(a1...an, b1...bm) = { 0, daca lista a e vida
;                          { 0, daca lista b e vida si a este nevida
;                          { a1 * b1 + prod(a2...an, b2...bm), altfel

(DEFUN PROD (A B)
  (COND
    ((NULL A) 0)
    ((NULL B) 0)
    (T (+ (* (CAR A) (CAR B))
              (PROD (CDR A) (CDR B)))
    )
  )
)
