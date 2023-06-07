%fact(N:integer,F:interger)
%(i,i),(i,o)-determinist
fact(N,F):-fact_aux(N,F,1,1).

%fact_aux(N:integer,F:integer,I:integer,P:integer)
%(i,i,i,i),(i,o,i,i)-determinist
fact_aux(N,F,N,F):-!.
fact_aux(N,F,I,P):-I1 is I+1,
    P1 is P*I1,
    fact_aux(N,F,I1,P1).

