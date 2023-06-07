; suma(l) = { l, daca l este atom numeric
;           { 0, daca l este atom si nu este numeric
;           { suma(l1) + suma(l2) + ... suma(ln), unde l = (l1...ln), altfel

(defun suma(l)
  (cond
    ((numberp l) l)
    ((atom l) 0)
    (t (apply '+ (mapcar #'suma l)))
  )
)
