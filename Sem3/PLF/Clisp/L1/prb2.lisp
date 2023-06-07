; maxim(l1...ln) = { -inf, daca n = 0
;                  { max(l1, maxim(l2...ln), daca l1 este atom numeric
;                  { maxim(l2...ln), daca l1 este atom
;                  { max(maxim(l1), maxim(l2...ln)), altfel

(DEFUN MAXIM (L)
  (COND
    ((NULL L) most-negative-fixnum)
    ((NUMBERP (CAR L)) (MAX (CAR L) (MAXIM (CDR L))))
    ((ATOM (CAR L)) (MAXIM (CDR L)))
    (T (MAX (MAXIM (CAR L)) (MAXIM (CDR L))))
  )
)
