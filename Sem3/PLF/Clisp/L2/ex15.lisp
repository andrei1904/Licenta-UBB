; parcurg_stanga(l1...ln, nv, nm) = { (), daca n = 0
;                                   { (), daca nv = 1 + nm
;                                   { l1 (+) l2 (+) parcurg_stanga(l3...ln, nv + 1, nm + l2), altfel
;
; stang(l1...ln) = parcurg_stanga(l3...ln, 0, 0)
;
; parcurg_dreapta(l1...ln, nv, nm) = { (), daca n = 0
;                                    { l1...ln, daca nv = 1 + nm
;                                    { parcurg_dreapta(l3...ln, nv + 1, nm + l2), altfel
;
; drept(l1...ln) = parcurg_dreapta(l3...ln, 0, 0)
;
; postordine(l1...ln) = { (), daca n = 0
;                       { postordine(stang(l1...ln)) (+) postordine(drept(l1...ln)) (+) l1, altfel


(defun parcurg_stanga(arb nv nm)
  (cond
    ((null arb) nil)
    ((= nv (+ 1 nm)) nil)
    (t (cons (car arb) (cons (cadr arb) (parcurg_stanga (cddr arb) (+ 1 nv) (+ (cadr arb) nm)))))
  )
)

(defun stang(arb)
  (parcurg_stanga (cddr arb) 0 0)
)

(defun parcurg_dreapta(arb nv nm)
  (cond
    ((null arb) nil)
    ((= nv (+ 1 nm)) arb)
    (t (parcurg_dreapta (cddr arb) (+ 1 nv) (+ (cadr arb) nm)))
  )
)

(defun drept(arb)
  (parcurg_dreapta (cddr arb) 0 0)
)

(defun postordine(arb)
  (cond
    ((null arb) nil)
    (t (append
          (postordine (stang arb))
          (postordine (drept arb))
          (list (car arb))
        )
    )
  )
)
