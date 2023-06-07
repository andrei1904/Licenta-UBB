-- conectare la baza de date Vacante
USE Vacante;

-- modifica numele bazei de date
ALTER DATABASE Vacante
MODIFY Name = Excursii;

-- sterge baza de date
USE master;
DROP DATABASE Excursii;

-- crearea unei noi baze de date
CREATE DATABASE Seminar1226;

-- conectare la baza de date Seminar1226
USE Seminar1226;

-- crearea unui tabel
CREATE TABLE Mancare
(cod_m INT,
nume VARCHAR(100),
data_expirarii DATE,
gramaj FLOAT,
pret FLOAT,
valoare_energetica FLOAT,
tara_de_origine VARCHAR(100),
ok_vegan BIT
);

-- adauga o coloana tabelului Mancare
ALTER TABLE Mancare
ADD timp_de_preparare TIME;

-- schimbarea tipului de date al unei coloane
ALTER TABLE Mancare
ALTER COLUMN valoare_energetica INT;

-- stergem o coloana
ALTER TABLE Mancare
DROP COLUMN tara_de_origine;

-- stergem un tabel
DROP TABLE Mancare;

-- constrangere NOT NULL
CREATE TABLE Avioane
(cod_a INT NOT NULL,
capacitate INT,
an_fabricatie INT NOT NULL,
model VARCHAR(100),
firma VARCHAR(70),
autonomie TIME
);

-- adaugarea unei constrangeri UNIQUE pe o coloana
ALTER TABLE Avioane
ADD CONSTRAINT uc_cod_a UNIQUE(cod_a);

-- adaugarea unei constranger UNIQUE pe mai multe coloane
ALTER TABLE Avioane
ADD UNIQUE (model, an_fabricatie);

-- eliminarea UNIQUE 
ALTER TABLE Avioane
DROP CONSTRAINT uc_cod_a;

-- adauga o constrangere PRIMARY KEY in tabelul Avioane
ALTER TABLE Avioane
ADD CONSTRAINT pk_avioane PRIMARY KEY (cod_a);

-- crearea unei constrangeri FOREIGN KEY
CREATE TABLE Clienti
(cod_c INT PRIMARY KEY,
nume VARCHAR(100)
);

CREATE TABLE Comenzi
(cod_com INT PRIMARY KEY,
nr_comanda INT,
cod_c INT FOREIGN KEY REFERENCES Clienti
);