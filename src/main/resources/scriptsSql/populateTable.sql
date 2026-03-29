-- --------------------------------------------------------------------------
-- -- INSERTION DES UTILISATEURS
-- --------------------------------------------------------------------------
INSERT INTO [USER] (pseudo, email, password, first_name, last_name, last_connection)
VALUES ('Jean-Yves', 'jyquintard@gmail.com', '123', 'Jean-Yves', 'Quintard', '2026-02-01 10:00:00');

INSERT INTO [USER] (pseudo, email, password, first_name, last_name, last_connection)
VALUES ('Catherine', 'katherine.quintard@gmail.com', '123', 'Catherine', 'Quintard', '2026-01-01 10:00:00');

INSERT INTO [USER] (pseudo, email, password, first_name, last_name, last_connection)
VALUES ('User3', 'user3@gmail.com', '123', 'Indiana', 'Jones', '2025-01-01 10:00:00');

-- --------------------------------------------------------------------------
-- -- INSERTION DES COMPTES
-- --------------------------------------------------------------------------
INSERT INTO [ACCOUNT] (
    bank, 
    reference, 
    [name], 
    current_solde, 
    last_update, 
    since_year,
    initial_balance,
    csv_import_folder,
    csv_row_ignored,
    csv_row_date,
    csv_row_solde,
    csv_column_number,
    csv_column_date,
    csv_date_format,
    csv_column_description,
    csv_column_credit,
    csv_column_debit,
    csv_column_value)
VALUES (
    'Banque Postale', 
    '320121E', 
    'Compte joint', 
    446772, 
    '',
    2007,
    451103,
    '',
    7,
    4,
    5,
    3,
    1,
    'JJ/MM/AAAA',
    2,
    null,
    null,
    3);

INSERT INTO [ACCOUNT] (bank, reference, name, current_solde, initial_balance, last_update)
VALUES ('CMB', '123456789', 'Perso', 0, 0, '');

INSERT INTO [ACCOUNT] (bank, reference, name, current_solde, initial_balance, last_update)
VALUES ('PARIBAS', '11223344', 'MyAccount', 0, 0, '');

-- --------------------------------------------------------------------------
-- -- INSERTION DES BALANCES ANNUELLES
-- --------------------------------------------------------------------------

INSERT INTO [BALANCE_SHEET] (id_account, [year], starting_balance)
VALUES (1, 2023, 0);

INSERT INTO [BALANCE_SHEET] (id_account, [year], starting_balance)
VALUES (1, 2024, 0);

INSERT INTO [BALANCE_SHEET] (id_account, [year], starting_balance)
VALUES (1, 2025, 0);

INSERT INTO [BALANCE_SHEET] (id_account, [year], starting_balance)
VALUES (1, 2026, 333762);

INSERT INTO [BALANCE_SHEET] (id_account, [year], starting_balance)
VALUES (1, 2007, 451103);

-- --------------------------------------------------------------------------
-- -- INSERTION DES PROFILE DE COMPTES
-- --------------------------------------------------------------------------
INSERT INTO [ACCOUNT_USER_PROFILE] (id_user, id_account, user_role)
VALUES ('1', '1', 'ADMIN');

INSERT INTO [ACCOUNT_USER_PROFILE] (id_user, id_account, user_role)
VALUES ('2', '1', 'HOLDER');

INSERT INTO [ACCOUNT_USER_PROFILE] (id_user, id_account, user_role)
VALUES ('1', '2', 'HOLDER');

INSERT INTO [ACCOUNT_USER_PROFILE] (id_user, id_account, user_role)
VALUES ('3', '3', 'HOLDER');

-- --------------------------------------------------------------------------
-- -- INSERTION DES CARTES DE CREDITS
-- --------------------------------------------------------------------------

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '821', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '776', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '521', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '420', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '876', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '980', 1);




INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('2', '819', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('2', '865', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('2', '123', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('2', '474', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('2', '219', 0);

-- --------------------------------------------------------------------------
-- -- INSERTION DES CHEQUIERS
-- --------------------------------------------------------------------------

INSERT INTO [CHECK_BOOK] (id_account_user_profile, first_check_number, check_range, active)
VALUES ('1', '1721041', '40', 0);

INSERT INTO [CHECK_BOOK] (id_account_user_profile, first_check_number, check_range, active)
VALUES ('2', '1721001', '40', 1);
