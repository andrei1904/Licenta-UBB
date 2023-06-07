%member(e:element, L:list)
%(i,i)-determinist, (o,i)-nedeterminist
member(E,[E|_]):-!.
       member(E,[_|L]):-memeber(E,L).


