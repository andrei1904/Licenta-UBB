; inloc(l1...ln, e, k, niv) =
;      { (), daca n = 0
;      { e (+) inloc(l2, e, k, niv + 1) (+) inloc(l3, e, k, niv + 1), daca n = k
;      { l1 (+) inloc(l2, e, k, niv + 1) + inloc(l3, e, k, niv + 1), altfel

; inloc(l, e, k, niv) = {
;                       {
;

(defun inloc(l e k niv)
  (cond
    ((null (car l)) nil)
    ((equal niv k) (append
                      (list e)
                      (list (inloc (cadr l) e k (+ 1 niv)))
                      (list (inloc (caddr l) e k (+ 1 niv)))
                    )
    )
    (t (append
            (list (car l))
            (list (inloc (cadr l) e k (+ 1 niv)))
            (list (inloc (caddr l) e k (+ 1 niv)))
        )
    )
  )
)
