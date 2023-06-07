; permutari(l1...ln) = { ((l1)), daca n <= 1
;                      { inserare(l1, permutari(l2...ln)), altfel
;
; inserare(x, l1...ln) = { (), daca n = 0
;                        { append(inserare-toate-poz(x, lenght(l1)+1, l1),
;                                  inserare(x, l2...ln)), altfel
;
; inserare-toate-poz(x, n, l1...lm) = { (), daca m = 0
;                                     { cons(inserare-poz(x, n, l1...lm),
;                                            inserare-toate-poz(x, n-1, l1...lm)), altfel
;
; inserare-poz(x, n, l1...lm) = { cons(x, l1...lm), daca n = 1
;                               { cons(l1, inserare-poz(x, n-1, l2...lm)), altfel

(DEFUN INSERARE-POZ (X N L)
  (COND
    ((= N 1) (CONS X L))
    (T (CONS (CAR L) (INSERARE-POZ X (- N 1) (CDR L))))
  )
)

(DEFUN INSERARE-TOATE-POZ (X N L)
  (COND
    ((= N 0) NIL)
    (T (CONS (INSERARE-POZ X N L) (INSERARE-TOATE-POZ X (- N 1) L)))
  )
)

(DEFUN INSERARE (X L)
  (COND
    ((NULL L) NIL)
    (T (APPEND
          (INSERARE-TOATE-POZ X (+ (LENGTH (CAR L)) 1) (CAR L))
          (INSERARE X (CDR L))
       )
    )
  )
)

(DEFUN PERMUTARI (L)
  (COND
    ((NULL (CDR L)) (LIST(LIST (CAR L))))
    (T (INSERARE (CAR L) (PERMUTARI (CDR L))))
  )
)


stergere(l1...ln, e) = [], n = 0
                       stergere(l2...ln, e), daca l1 = e
                       stergere(l1, e) (+) stergere(l2...ln, e), daca l1 este lista
                       
