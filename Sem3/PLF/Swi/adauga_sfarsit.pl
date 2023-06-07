%adaug(e:element, L:list, LRez:list)
%(i,i,o)-determinist
adaug(E,[],[E]).
adaug(E,[H|T],[H,Rez]):-
    adaug(E,T,Rez).

