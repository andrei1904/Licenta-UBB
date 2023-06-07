USE BloodBank;

--DIRTY READS
BEGIN TRANSACTION

UPDATE Donors SET FirstName='Marian' WHERE DonorId=2

WAITFOR DELAY '00:00:05'

ROLLBACK TRANSACTION



--NON-REPEATABLE READS
INSERT INTO Donors(AdressId, BloodBankId, NationalMediaclNumber, Gender, FirstName, PhoneNumber) 
VALUES (14023, 14026,'12345','male','Paul','1700112233')

BEGIN TRANSACTION

WAITFOR DELAY '00:00:05'

UPDATE Donors SET FirstName='George' WHERE PhoneNumber='1700112233'
COMMIT TRANSACTION


DELETE FROM Donors WHERE PhoneNumber='1700112233';



--PHANTOM READS

BEGIN TRANSACTION

WAITFOR DELAY '00:00:10'

INSERT INTO Donors(AdressId, BloodBankId, NationalMediaclNumber, Gender, FirstName, PhoneNumber) 
VALUES (14023, 14026,'12345','male','Paul','1700112233')
COMMIT TRANSACTION

DELETE FROM Donors WHERE PhoneNumber='1700112233';



-- DEADLOCK
BEGIN TRANSACTION

UPDATE Donors SET FirstName='Mihai' 
WHERE DonorId=2

WAITFOR DELAY '00:00:10'

UPDATE Addresses SET County='Cluj' 
WHERE AddressId = 14024
COMMIT TRANSACTION



GO
CREATE OR ALTER PROCEDURE Deadlock_Thread1 
AS

BEGIN TRANSACTION

UPDATE Donors SET FirstName='Mihai' 
WHERE DonorId=2

WAITFOR DELAY '00:00:10'

UPDATE Addresses SET County='Cluj' 
WHERE AddressId = 14024
COMMIT TRANSACTION



