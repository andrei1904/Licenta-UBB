gcd(X, Y, Z) :-
    X < 0, !,
    gcd(-X, Y, Z).
gcd(X, Y, Z) :-
    Y < 0, !,
    gcd(X, -Y, Z).
gcd(X, 0, X) :- X > 0.
gcd(0, Y, Y) :- Y > 0.
gcd(X, Y, Z) :-
    X > Y, Y > 0,
    X1 is X - Y,
    gcd(Y, X1, Z).
gcd(X, Y, Z) :-
    X =< Y, X > 0,
    Y1 is Y - X,
    gcd(X, Y1, Z).


gcd_lista([], 0, 0).

gcd_lista([H|T], Q, X) :-
    gcd(H, Q, X),
    write(X),
    Q1 is X,
    gcd_lista([T], Q1, X).

