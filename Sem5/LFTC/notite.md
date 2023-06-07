# LFTC

### Simbol
Simbol = a, b, c, 0, 1, 2, 3 (atom lexical)

### Alfabet
Alfabet = o multime finitã si nevidã de elemente
numite simboluri (se noteaza: Σ - sigma) ex: {a, b}, {0, 1, 2}

### Secventa
Serventa = succesiune finita de simboluri; ex: a, b, 0, 1
Secventa vida = epsion

### Limbaj
Limbaj = set de secvente
ex: Σ = {0, 1} ; L1 = set de toate secventele de lungime 2 => {00, 01, 10, 11}

Daca are numar finit de elemente = finit, altfel e infinit

#### Puterile unui alfabet Σ
Σ = {0, 1}

Σ^0 = {epsion}

Σ^1 = {0, 1}

Σ^2 = {00, 01, 10, 11}

Numarul de elemente ale unui alfabet = 2^n

## Automat finit - fara output
#### Deterministic Automat Finit (AFD)
Poti sa mergi intr-o singura stare printr-un anumit simbol
M = (Q, Σ, q0, F, del)

Q = alfabetul starilor

Σ = alfabet de intrare

q0 = starea initiala

F = setul starilor finale

del = functie de tranzicite din QxΣ -> Q (tabel cu Q pe linii si Σ pe coloane si in celluri este unde ajungi din linia x prin cloana x)

#### NON-Deterministic Automat Finit (NFA)
Poti sa mergin in mai multe stari prin acelasi simbol (Ex: din A mergi in prin 1 in B, dar mergi prin 1 si in C)

del = QxΣ = 2^numarul de stari (2^cardinalul lui Q)



### Limbaje regulare
ESTE limbaj regular doar daca o Mastina cu Stari Finite o recunoaste

NU ESTE limbaj regular daca are nevoie de memorie, adica are nevoie de secvente (stringuri) (nu este recunoscut de FSM)


Daca un limbaj este regular => este si independent de context.
Complementul unui limbaj regular este si el limbaj regular, dar si independent de context.


### LL(1) parser
Ai nevoie de:

1. First() si Follow()
2. Parsing table
3. Stack implementation
4. Parse the input string

Trebuie eliminat Left Recursion

#### Left Recursion
Problema pentru parserele top - down, apare atunci cand cea mai din stanga variabila din RHS = cu aceasi variabila din LHS

#### First()
First(X) este un set de simboluri terminale, aceste simboluri sunt primul simbol din regulile de derivare ale lui X

Reguli:
1. X -> eps => First(X) = {eps}
2. X -> a => First(X) = {a}
3. X -> Y1Y2Y3
    - Daca First(Y1) nu contine eps => First(X) = First(Y1)
    - Altfel First(X) = {First(Y1) - eps} U First(Y2Y3)

#### Follow()
Follow(X) simbolurile care apar in dreapta lui X

Reguli:
1. Pentru simbolul de start(S) trebuie pus $ in Follow(S)
2. Daca este o productie de tipul A -> aB, atunci Follow(B) = Follow(A)
3. Daca este o productie de tipul A -> aBB
    - daca eps nu apartine de First(B), atunci Follow(B) = First(B)
    - altfel Follow(B) = {First(B) - eps} U Follow(A)

Exemplu problema LL(1) parser:

G = ({S,A,B,C,D}, {+,*,(,),a}, P, S)

P:
- S -> BA
- A -> +BA
- A -> eps
- B -> DC
- C -> *DC
- C -> eps
- D -> (S)
- D -> a

secventa w = a * (a + a)

|  | First     | Follow |
| :------------- | :------------- | :----------- |
| S | a, (   | $, ) |
| A | +, eps | $, ) |
| B | a, (   | |
| C | *, eps | |
| D | a, (   | |
