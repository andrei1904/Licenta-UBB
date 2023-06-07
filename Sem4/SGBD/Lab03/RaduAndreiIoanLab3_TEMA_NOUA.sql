CREATE OR ALTER PROCEDURE AddDonorsConditions 
@nationalMedicalNumber INT,
@gender VARCHAR(6),
@dateOfBirth DATE,
@firstName VARCHAR(20),
@lastName VARCHAR(20),
@phoneNumber INT,
@conditionName VARCHAR(20),
@conditionDetails VARCHAR(100)
AS
BEGIN
	BEGIN TRAN
	BEGIN TRY
		IF (LEN(@nationalMedicalNumber) != 5)
		BEGIN 
			RAISERROR('National medical number must have 5 digits', 14, 1)
		END
		
		IF (@gender != 'male' and @gender != 'female')
		BEGIN 
			RAISERROR('Gender must be male or female', 14, 1)
		END

		IF (@dateOfBirth > DATEADD(YEAR, -14, GETDATE()))
		BEGIN 
			RAISERROR('Date of birth is invalid', 14, 1)
		END

		IF (LEN(@firstName) < 2 or LEN(@lastName) < 2)
		BEGIN 
			RAISERROR('First and last name must have at least 2 characters each', 14, 1)
		END

		IF (LEN(@phoneNumber) != 10)
		BEGIN 
			RAISERROR('Phone number must have 10 digits', 14, 1)
		END

		IF (LEN(@conditionName) < 2)
		BEGIN 
			RAISERROR('Condition name must have at least 2 characters', 14, 1)
		END

		IF (LEN(@conditionDetails) < 5)
		BEGIN 
			RAISERROR('Condition details must have at least 5 characters', 14, 1)
		END

		INSERT INTO Donors(AdressId, BloodBankId, NationalMediaclNumber, Gender, DateOfBirth, FirstName,
							LastName, PhoneNumber) VALUES (14023, 14026, @nationalMedicalNumber, @gender,
							@dateOfBirth, @firstName, @lastName, @phoneNumber)

		INSERT INTO MedicalConditions(ConditionName, ConditionDetails) VALUES (@conditionName, @conditionDetails)

		DECLARE @donorId INT
		SELECT TOP 1 @donorId = DonorId FROM Donors ORDER BY DonorId DESC

		DECLARE @conditionId INT
		SELECT TOP 1 @conditionId = ConditionId FROM MedicalConditions ORDER BY ConditionId DESC

		INSERT INTO DonorsMedicalConditions(DonorId, ConditionId) VALUES (@donorId, @conditionId)

		COMMIT TRAN
		SELECT'Transaction committed'

		INSERT INTO LogTable(TypeOperation, TableOperation, ExecutionDate) VALUES  ('INSERT', 'Donors', GETDATE())
		INSERT INTO LogTable(TypeOperation, TableOperation, ExecutionDate) VALUES  ('INSERT', 'MedicalConditions', GETDATE())
		INSERT INTO LogTable(TypeOperation, TableOperation, ExecutionDate) VALUES  ('INSERT', 'DonorsMedicalConditions', GETDATE())
	
		SELECT TOP 3 * FROM LogTable ORDER BY Lid DESC
	END TRY

	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Transaction rollbacked'
		SELECT 
			ERROR_NUMBER() AS ErrorNumber,
			ERROR_MESSAGE() AS ErrorMessage
	END CATCH
END


EXEC AddDonorsConditions 13445, 'male', '2000-07-04', 'Cristi', 'Paul', 1344513445, 'raceala', 'okkkkkk';
select * from Donors

EXEC AddDonorsConditions 1344, 'male', '2000-07-04', 'Cristi', 'Paul', 1344513445, 'raceala', 'okkkkkk';
EXEC AddDonorsConditions 13445, 'ok', '2000-07-04', 'Cristi', 'Paul', 1344513445, 'raceala', 'okkkkkk';
EXEC AddDonorsConditions 13445, 'male', '2017-07-04', 'Cristi', 'Paul', 1344513445, 'raceala', 'okkkkkk';
EXEC AddDonorsConditions 13445, 'male', '2000-07-04', 'Cr', 'l', 1344513445, 'raceala', 'okkkkkk';
EXEC AddDonorsConditions 13445, 'male', '2000-07-04', 'Cristi', 'Paul', 134, 'raceala', 'okkkkkk';
EXEC AddDonorsConditions 13445, 'male', '2000-07-04', 'Cristi', 'Paul', 1344513445, 'r', 'okkkkkk';
EXEC AddDonorsConditions 13445, 'male', '2000-07-04', 'Cristi', 'Paul', 1344513445, 'raceala', 'okk';


GO
CREATE OR ALTER PROCEDURE AddDonorsConditions2 
@nationalMedicalNumber INT,
@gender VARCHAR(6),
@dateOfBirth DATE,
@firstName VARCHAR(20),
@lastName VARCHAR(20),
@phoneNumber INT,
@conditionName VARCHAR(20),
@conditionDetails VARCHAR(100)
AS
BEGIN
	BEGIN TRAN
	BEGIN TRY
		IF (LEN(@nationalMedicalNumber) != 5)
		BEGIN 
			RAISERROR('National medical number must have 5 digits', 14, 1)
		END
		
		IF (@gender != 'male' and @gender != 'female')
		BEGIN 
			RAISERROR('Gender must be male or female', 14, 1)
		END

		IF (@dateOfBirth > DATEADD(YEAR, -14, GETDATE()))
		BEGIN 
			RAISERROR('Date of birth is invalid', 14, 1)
		END

		IF (LEN(@firstName) < 2 or LEN(@lastName) < 2)
		BEGIN 
			RAISERROR('First and last name must have at least 2 characters each', 14, 1)
		END

		IF (LEN(@phoneNumber) != 10)
		BEGIN 
			RAISERROR('Phone number must have 10 digits', 14, 1)
		END

		INSERT INTO Donors(AdressId, BloodBankId, NationalMediaclNumber, Gender, DateOfBirth, FirstName,
							LastName, PhoneNumber) VALUES (14023, 14026, @nationalMedicalNumber, @gender,
							@dateOfBirth, @firstName, @lastName, @phoneNumber)

		COMMIT TRAN
		SELECT'Transaction committed' AS Donors

		INSERT INTO LogTable(TypeOperation, TableOperation, ExecutionDate) VALUES  ('INSERT', 'Donors', GETDATE())

		SELECT TOP 1 * FROM LogTable ORDER BY Lid DESC
	END TRY

	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Transaction rollbacked' AS Donors
		SELECT 
			ERROR_NUMBER() AS ErrorNumber,
			ERROR_MESSAGE() AS ErrorMessage
	END CATCH

	BEGIN TRAN
	BEGIN TRY
		
		IF (LEN(@conditionName) < 2)
		BEGIN 
			RAISERROR('Condition name must have at least 2 characters', 14, 1)
		END

		IF (LEN(@conditionDetails) < 5)
		BEGIN 
			RAISERROR('Condition details must have at least 5 characters', 14, 1)
		END

		INSERT INTO MedicalConditions(ConditionName, ConditionDetails) VALUES (@conditionName, @conditionDetails)

		COMMIT TRAN
		SELECT 'Transaction committed' AS MedicalConditions

		INSERT INTO LogTable(TypeOperation, TableOperation, ExecutionDate) VALUES  ('INSERT', 'MedicalConditions', GETDATE())
	
		SELECT TOP 1 * FROM LogTable ORDER BY Lid DESC
	END TRY

	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Transaction rollbacked' AS MedicalConditions
		SELECT 
			ERROR_NUMBER() AS ErrorNumber,
			ERROR_MESSAGE() AS ErrorMessage
	END CATCH


	BEGIN TRAN
	BEGIN TRY
		DECLARE @donorId INT
		SELECT TOP 1 @donorId = DonorId FROM Donors ORDER BY DonorId DESC

		DECLARE @conditionId INT
		SELECT TOP 1 @conditionId = ConditionId FROM MedicalConditions ORDER BY ConditionId DESC

		DECLARE @number INT
		SELECT TOP 1 @number = DonorId FROM DonorsMedicalConditions WHERE DonorId = @donorId and ConditionId = @conditionId
		IF (@number = @donorId)
		BEGIN
			RAISERROR('This relation exists already', 14, 1)
		END

		INSERT INTO DonorsMedicalConditions(DonorId, ConditionId) VALUES (@donorId, @conditionId)

		COMMIT TRAN
		SELECT 'Transaction committed' AS DonorsMedicalConditions

		INSERT INTO LogTable(TypeOperation, TableOperation, ExecutionDate) VALUES  ('INSERT', 'DonorsMedicalConditions', GETDATE())
	
		SELECT TOP 1 * FROM LogTable ORDER BY Lid DESC
	END TRY

	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Transaction rollbacked' AS DonorsMedicalConditions
		SELECT 
			ERROR_NUMBER() AS ErrorNumber,
			ERROR_MESSAGE() AS ErrorMessage
	END CATCH
END


EXEC AddDonorsConditions2 13445, 'male', '2000-07-04', 'Cristi', 'Paul', 1344513445, 'raceala', 'okkkkkk';
SELECT * FROM Donors
SELECT * FROM MedicalConditions
SELECT * FROM DonorsMedicalConditions


-- tran donors rollback
EXEC AddDonorsConditions2 1344, 'male', '2000-07-04', 'Cristi', 'Paul', 1344513445, 'raceala', 'okkkkkk';

-- tran medical conditions rollback 
EXEC AddDonorsConditions2 13445, 'female', '2000-07-04', 'Cristina', 'Paula', 1344513445, 'raceala', 'ok';

-- tran donors, medical conditions, donors medical conditions rollback
EXEC AddDonorsConditions2 1345, 'female', '2000-07-04', 'Cristina', 'Paula', 1344513445, 'raceala', 'ok';


