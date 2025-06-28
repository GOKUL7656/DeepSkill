BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Loans';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    Age NUMBER NOT NULL,
    Balance NUMBER DEFAULT 0,
    IsVIP VARCHAR2(5) DEFAULT 'FALSE'
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER NOT NULL,
    InterestRate NUMBER(5, 2) NOT NULL,
    Amount NUMBER(10, 2) NOT NULL,
    DueDate DATE NOT NULL,
    CONSTRAINT FK_CustomerLoan FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Customers (CustomerID, Name, Age, Balance) VALUES (1, 'Alice Smith', 65, 12000);
INSERT INTO Customers (CustomerID, Name, Age, Balance) VALUES (2, 'Bob Johnson', 58, 8500);
INSERT INTO Customers (CustomerID, Name, Age, Balance) VALUES (3, 'Charlie Brown', 72, 5000);
INSERT INTO Customers (CustomerID, Name, Age, Balance) VALUES (4, 'Diana Prince', 45, 15000);
INSERT INTO Customers (CustomerID, Name, Age, Balance) VALUES (5, 'Eve Adams', 61, 9000);
INSERT INTO Customers (CustomerID, Name, Age, Balance) VALUES (6, 'Frank White', 30, 2000);

INSERT INTO Loans (LoanID, CustomerID, InterestRate, Amount, DueDate) VALUES (101, 1, 7.50, 25000, SYSDATE + 90);
INSERT INTO Loans (LoanID, CustomerID, InterestRate, Amount, DueDate) VALUES (102, 2, 6.80, 15000, SYSDATE + 15);
INSERT INTO Loans (LoanID, CustomerID, InterestRate, Amount, DueDate) VALUES (103, 3, 8.00, 30000, SYSDATE + 45);
INSERT INTO Loans (LoanID, CustomerID, InterestRate, Amount, DueDate) VALUES (104, 4, 6.00, 50000, SYSDATE + 10);
INSERT INTO Loans (LoanID, CustomerID, InterestRate, Amount, DueDate) VALUES (105, 5, 7.20, 10000, SYSDATE + 120);
INSERT INTO Loans (LoanID, CustomerID, InterestRate, Amount, DueDate) VALUES (106, 1, 7.00, 5000, SYSDATE + 365);

COMMIT;

SELECT * FROM Customers;
SELECT * FROM Loans;