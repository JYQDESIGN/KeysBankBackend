-- --------------------------------------------------------------------------
-- -- INSERTION DES UTILISATEURS
-- --------------------------------------------------------------------------
INSERT INTO [USER] (pseudo, email, password, first_name, last_name, last_connection)
VALUES ('User1', 'user1@gmail.com', '123', 'John', 'Snow', '2026-02-01 10:00:00');

INSERT INTO [USER] (pseudo, email, password, first_name, last_name, last_connection)
VALUES ('User2', 'user2@gmail.com', '123', 'Paul', 'Astreïd', '2026-01-01 10:00:00');

INSERT INTO [USER] (pseudo, email, password, first_name, last_name, last_connection)
VALUES ('User3', 'user3@gmail.com', '123', 'Indiana', 'Jones', '2025-01-01 10:00:00');

-- --------------------------------------------------------------------------
-- -- INSERTION DES COMPTES
-- --------------------------------------------------------------------------
INSERT INTO [ACCOUNT] (bank, reference, name)
VALUES ('Banque Postale', '987654321', 'Compte joint');

INSERT INTO [ACCOUNT] (bank, reference, name)
VALUES ('CMB', '123456789', 'Perso');

INSERT INTO [ACCOUNT] (bank, reference, name)
VALUES ('PARIBAS', '11223344', 'MyAccount');

-- --------------------------------------------------------------------------
-- -- INSERTION DES PROFILE DE COMPTES
-- --------------------------------------------------------------------------
INSERT INTO [ACCOUNT_USER_PROFILE] (id_user, id_account, user_role)
VALUES ('1', '1', 'ADMIN');

INSERT INTO [ACCOUNT_USER_PROFILE] (id_user, id_account, user_role)
VALUES ('1', '2', 'HOLDER');

INSERT INTO [ACCOUNT_USER_PROFILE] (id_user, id_account, user_role)
VALUES ('2', '1', 'HOLDER');

INSERT INTO [ACCOUNT_USER_PROFILE] (id_user, id_account, user_role)
VALUES ('3', '3', 'HOLDER');

-- --------------------------------------------------------------------------
-- -- INSERTION DES CARTES DE CREDITS
-- --------------------------------------------------------------------------

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '1234', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '5678', 0);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('1', '1111', 1);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('2', '2222', 1);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('3', '3333', 1);

INSERT INTO [CREDIT_CARD] (id_account_user_profile, reference, active)
VALUES ('4', '4444', 1);

-- --------------------------------------------------------------------------
-- -- INSERTION DES CHEQUIERS
-- --------------------------------------------------------------------------

INSERT INTO [CHECK_BOOK] (id_account_user_profile, first_check_number, check_range, active)
VALUES ('1', '123456789', '40', 0);

INSERT INTO [CHECK_BOOK] (id_account_user_profile, first_check_number, check_range, active)
VALUES ('1', '234567891', '40', 1);

INSERT INTO [CHECK_BOOK] (id_account_user_profile, first_check_number, check_range, active)
VALUES ('2', '345678912', '40', 1);

INSERT INTO [CHECK_BOOK] (id_account_user_profile, first_check_number, check_range, active)
VALUES ('3', '4567891234', '40', 1);

INSERT INTO [CHECK_BOOK] (id_account_user_profile, first_check_number, check_range, active)
VALUES ('4', '5678912345', '40', 1);

-- --------------------------------------------------------------------------
-- -- INSERTION DES CATEGORY
-- --------------------------------------------------------------------------

INSERT INTO OPERATION_CATEGORY (op_category_label, op_category_color)
VALUES ('Category_1', '#2020FF');

INSERT INTO OPERATION_CATEGORY (op_category_label, op_category_color)
VALUES ('Category_2', '#20FF20');

INSERT INTO OPERATION_CATEGORY (op_category_label, op_category_color)
VALUES ('Category_3', '#FF2020');

INSERT INTO OPERATION_CATEGORY (op_category_label, op_category_color)
VALUES ('Category_4', '#FF20FF');



INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('1', 'SubCategory_11', '#2020EE');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('1', 'SubCategory_12', '#2020DD');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('1', 'SubCategory_13', '#2020CC');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('2', 'SubCategory_21', '#20EE20');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('2', 'SubCategory_22', '#20DD20');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('2', 'SubCategory_23', '#20CC20');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('3', 'SubCategory_31', '#EE2020');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('3', 'SubCategory_32', '#DD2020');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('3', 'SubCategory_33', '#CC2020');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('4', 'SubCategory_41', '#EE20EE');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('4', 'SubCategory_42', '#DD20DD');

INSERT INTO OPERATION_SUB_CATEGORY (id_op_category, op_sub_category_label, op_sub_category_color)
VALUES ('4', 'SubCategory_43', '#CC20CC');


-- --------------------------------------------------------------------------
-- -- INSERTION DES OPERATIONS
-- --------------------------------------------------------------------------