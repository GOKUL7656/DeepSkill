--Loan Interest
SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_customer_loans IS
        SELECT
            c.CustomerID,
            c.Age,
            l.LoanID,
            l.InterestRate
        FROM
            Customers c
        JOIN
            Loans l ON c.CustomerID = l.CustomerID;

    v_customer_id    Customers.CustomerID%TYPE;
    v_customer_age   Customers.Age%TYPE;
    v_loan_id        Loans.LoanID%TYPE;
    v_current_rate   Loans.InterestRate%TYPE;
    v_new_rate       Loans.InterestRate%TYPE;
    v_discount_rate  CONSTANT NUMBER := 0.01;

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Applying Loan Interest Rate Discount ---');

    FOR r_loan IN c_customer_loans LOOP
        v_customer_id  := r_loan.CustomerID;
        v_customer_age := r_loan.Age;
        v_loan_id      := r_loan.LoanID;
        v_current_rate := r_loan.InterestRate;

        IF v_customer_age > 60 THEN
            v_new_rate := v_current_rate - v_discount_rate;

            IF v_new_rate < 0 THEN
                v_new_rate := 0;
            END IF;

            UPDATE Loans
            SET InterestRate = v_new_rate
            WHERE LoanID = v_loan_id;

            DBMS_OUTPUT.PUT_LINE(
                'Customer ID: ' || v_customer_id ||
                ' (Age: ' || v_customer_age || ')' ||
                ', Loan ID: ' || v_loan_id ||
                ' - Old Rate: ' || TO_CHAR(v_current_rate, 'FM99.99') || '%' ||
                ', New Rate: ' || TO_CHAR(v_new_rate, 'FM99.99') || '%'
            );
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('--- Discount application complete. ---');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;
/


--VIP ticket

SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_customers IS
        SELECT
            CustomerID,
            Name,
            Balance,
            IsVIP
        FROM
            Customers;

    v_customer_id   Customers.CustomerID%TYPE;
    v_customer_name Customers.Name%TYPE;
    v_balance       Customers.Balance%TYPE;
    v_is_vip_flag   Customers.IsVIP%TYPE;
    v_vip_threshold CONSTANT NUMBER := 10000;

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Promoting Customers to VIP Status ---');

    FOR r_customer IN c_customers LOOP
        v_customer_id   := r_customer.CustomerID;
        v_customer_name := r_customer.Name;
        v_balance       := r_customer.Balance;
        v_is_vip_flag   := r_customer.IsVIP;

        IF v_balance > v_vip_threshold AND v_is_vip_flag = 'FALSE' THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = v_customer_id;

            DBMS_OUTPUT.PUT_LINE(
                'Customer ID: ' || v_customer_id ||
                ' (Name: ' || v_customer_name || ')' ||
                ' - Balance: $' || TO_CHAR(v_balance, 'FM999,999.00') ||
                ' - Promoted to VIP.'
            );
        ELSIF v_balance > v_vip_threshold AND v_is_vip_flag = 'TRUE' THEN
            DBMS_OUTPUT.PUT_LINE(
                'Customer ID: ' || v_customer_id ||
                ' (Name: ' || v_customer_name || ')' ||
                ' - Already VIP.'
            );
        ELSE
            DBMS_OUTPUT.PUT_LINE(
                'Customer ID: ' || v_customer_id ||
                ' (Name: ' || v_customer_name || ')' ||
                ' - Balance: $' || TO_CHAR(v_balance, 'FM999,999.00') ||
                ' - Does not qualify for VIP.'
            );
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('--- VIP promotion process complete. ---');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;
/

--Bank
SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_due_loans IS
        SELECT
            l.LoanID,
            c.Name AS CustomerName,
            l.Amount,
            l.DueDate
        FROM
            Loans l
        JOIN
            Customers c ON l.CustomerID = c.CustomerID
        WHERE
            l.DueDate BETWEEN SYSDATE AND (SYSDATE + 30);

    v_loan_id       Loans.LoanID%TYPE;
    v_customer_name Customers.Name%TYPE;
    v_loan_amount   Loans.Amount%TYPE;
    v_due_date      Loans.DueDate%TYPE;

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Sending Loan Reminders ---');

    OPEN c_due_loans;
    LOOP
        FETCH c_due_loans INTO v_loan_id, v_customer_name, v_loan_amount, v_due_date;
        EXIT WHEN c_due_loans%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Dear ' || v_customer_name ||
            ', your loan (ID: ' || v_loan_id || ')' ||
            ' of $' || TO_CHAR(v_loan_amount, 'FM999,999.00') ||
            ' is due on ' || TO_CHAR(v_due_date, 'DD-MON-YYYY') || '.'
        );
    END LOOP;
    CLOSE c_due_loans;

    DBMS_OUTPUT.PUT_LINE('--- Loan reminder process complete. ---');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;
/