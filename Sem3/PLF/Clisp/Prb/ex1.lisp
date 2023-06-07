(defun elimina(l k niv)
  (cond
    ((and (atom l) (equal niv k)) nil)
    ((not (eq niv k)) (list l))
    (t (apply #'append (mapcar #'(lambda (v)
                                    (elimina v k (+ 1 niv))
                                  ) l
                        )
                      )
    )
  )
)
