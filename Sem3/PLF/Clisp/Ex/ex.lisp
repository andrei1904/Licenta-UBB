(DEFUN PRIM (X)
  (COND
    ((ATOM X) X)
    ((NULL X) NIL) ;inutil
    (T (CAR X))
  )
)


(DEFUN  MAXI (X Y)
  (COND
    ((< X Y) X)
    (T Y)
  )
)

(DEFUN PROD (X Y)
  (* X Y)
)

(defun IP (a b)
    (if (null a)
        0
        (+ (* (car a) (car b))
              (IP (cdr a) (cdr b)))))
