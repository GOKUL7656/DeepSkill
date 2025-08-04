BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Accounts';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Employees';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE SavingsAccounts';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

CREATE TABLE SavingsAccounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(100) NOT NULL,
    Balance NUMBER(10, 2) NOT NULL
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    Department VARCHAR2(50) NOT NULL,
    Salary NUMBER(10, 2) NOT NULL
);

CREATE TABLE Accounts (
    AccountNumber VARCHAR2(20) PRIMARY KEY,
    AccountHolder VARCHAR2(100) NOT NULL,
    Balance NUMBER(10, 2) NOT NULL
);

INSERT INTO SavingsAccounts (AccountID, CustomerName, Balance) VALUES (1001, 'John Doe', 5000.00);
INSERT INTO SavingsAccounts (AccountID, CustomerName, Balance) VALUES (1002, 'Jane Smith', 12000.50);
INSERT INTO SavingsAccounts (AccountID, CustomerName, Balance) VALUES (1003, 'Mike Johnson', 800.75);
INSERT INTO SavingsAccounts (AccountID, CustomerName, Balance) VALUES (1004, 'Emily Davis', 25000.00);

INSERT INTO Employees (EmployeeID, Name, Department, Salary) VALUES (1, 'Alice Green', 'Sales', 60000.00);
INSERT INTO Employees (EmployeeID, Name, Department, Salary) VALUES (2, 'Bob Black', 'Marketing', 55000.00);
INSERT INTO Employees (EmployeeID, Name, Department, Salary) VALUES (3, 'Charlie Red', 'Sales', 70000.00);
INSERT INTO Employees (EmployeeID, Name, Department, Salary) VALUES (4, 'David Blue', 'HR', 50000.00);

INSERT INTO Accounts (AccountNumber, AccountHolder, Balance) VALUES ('ACC001', 'Alice User', 1000.00);
INSERT INTO Accounts (AccountNumber, AccountHolder, Balance) VALUES ('ACC002', 'Bob User', 500.00);
INSERT INTO Accounts (AccountNumber, AccountHolder, Balance) VALUES ('ACC003', 'Charlie User', 2500.00);

COMMIT;

SELECT * FROM SavingsAccounts;
SELECT * FROM Employees;
SELECT * FROM Accounts;

--Monthly Interest
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
    v_interest_rate CONSTANT NUMBER := 0.01;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Processing Monthly Interest for Savings Accounts ---');

    UPDATE SavingsAccounts
    SET Balance = Balance * (1 + v_interest_rate);

    DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || ' savings accounts updated with monthly interest.');

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('--- Monthly interest processing complete. ---');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred during interest processing: ' || SQLERRM);
END ProcessMonthlyInterest;
/

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department_name IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) IS
    v_updated_count NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Updating Employee Bonus for Department: ' || p_department_name || ' ---');
    DBMS_OUTPUT.PUT_LINE('Applying bonus of: ' || TO_CHAR(p_bonus_percentage * 100, 'FM99.99') || '%');

    UPDATE Employees
    SET Salary = Salary * (1 + p_bonus_percentage)
    WHERE Department = p_department_name;

    v_updated_count := SQL%ROWCOUNT;
    DBMS_OUTPUT.PUT_LINE(v_updated_count || ' employees in ' || p_department_name || ' had their salaries updated.');

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('--- Employee bonus update complete. ---');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred during employee bonus update: ' || SQLERRM);
END UpdateEmployeeBonus;
/

--Transfer
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_source_account_number IN VARCHAR2,
    p_destination_account_number IN VARCHAR2,
    p_amount IN NUMBER
) IS
    v_source_balance NUMBER(10, 2);
    e_insufficient_funds EXCEPTION;
    PRAGMA EXCEPTION_INIT(e_insufficient_funds, -20001);

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Initiating Fund Transfer ---');
    DBMS_OUTPUT.PUT_LINE('From: ' || p_source_account_number || ' To: ' || p_destination_account_number || ' Amount: $' || TO_CHAR(p_amount, 'FM999,999.00'));

    IF p_amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Transfer amount must be positive.');
        RETURN;
    END IF;

    SELECT Balance
    INTO v_source_balance
    FROM Accounts
    WHERE AccountNumber = p_source_account_number;

    IF v_source_balance < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountNumber = p_source_account_number;

    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountNumber = p_destination_account_number;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful!');
    DBMS_OUTPUT.PUT_LINE('New balance for ' || p_source_account_number || ': $' || TO_CHAR(v_source_balance - p_amount, 'FM999,999.00'));

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One or both account numbers do not exist.');
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in source account ' || p_source_account_number || '. Current balance: $' || TO_CHAR(v_source_balance, 'FM999,999.00'));
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An unexpected error occurred during transfer: ' || SQLERRM);
END TransferFunds;
/