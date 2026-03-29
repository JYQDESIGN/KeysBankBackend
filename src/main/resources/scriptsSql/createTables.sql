---------------------------------------------------------------------------
-- DROP POUR CLEAR BDD
---------------------------------------------------------------------------

ALTER TABLE ACCOUNT_USER_PROFILE
    DROP CONSTRAINT fk_user,fk_account;

ALTER TABLE CREDIT_CARD
    DROP CONSTRAINT fk_account_user_profile_card;

ALTER TABLE CHECK_BOOK
    DROP CONSTRAINT fk_account_user_profile_book;

ALTER TABLE OPERATION_CATEGORY
    DROP CONSTRAINT fk_account_op_category;
    
ALTER TABLE OPERATION_SUB_CATEGORY
    DROP CONSTRAINT fk_sub_category_category;

ALTER TABLE SUB_CATEGORY_KEY
    DROP CONSTRAINT fk_key_sub_category;

ALTER TABLE MODE_KEY
    DROP CONSTRAINT fk_key_mode;

ALTER TABLE OPERATION
    DROP CONSTRAINT fk_account_op;

ALTER TABLE OPERATION_TYPE
    DROP CONSTRAINT fk_account_op_type;

ALTER TABLE [OPERATION_MODE]
    DROP CONSTRAINT fk_account_op_mode;

ALTER TABLE BALANCE_SHEET
    DROP CONSTRAINT fk_account_balance_sheet;

DROP TABLE IF EXISTS [USER];
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS ACCOUNT_USER_PROFILE;
DROP TABLE IF EXISTS CREDIT_CARD;
DROP TABLE IF EXISTS CHECK_BOOK;
DROP TABLE IF EXISTS OPERATION_TYPE;
DROP TABLE IF EXISTS [OPERATION_MODE];
DROP TABLE IF EXISTS OPERATION_STATUS;
DROP TABLE IF EXISTS OPERATION;
DROP TABLE IF EXISTS OPERATION_CATEGORY;
DROP TABLE IF EXISTS OPERATION_SUB_CATEGORY;
DROP TABLE IF EXISTS SUB_CATEGORY_KEY;
DROP TABLE IF EXISTS MODE_KEY;
DROP TABLE IF EXISTS BALANCE_SHEET;
---------------------------------------------------------------------------
-- CREATION DES TABLES
---------------------------------------------------------------------------

CREATE TABLE [USER]
(
    id_user                 BIGINT NOT NULL PRIMARY KEY IDENTITY (1,1),
    pseudo                  VARCHAR(30)     NOT NULL,
    email                   VARCHAR(30)     NOT NULL,
    password                VARCHAR(256)    NOT NULL,
    first_name              VARCHAR(30)     NOT NULL,
    last_name               VARCHAR(30)     NOT NULL,
    last_connection         DATETIME2       NOT NULL,
)

CREATE TABLE ACCOUNT
(
    id_account              BIGINT NOT NULL PRIMARY KEY IDENTITY (1,1),
    name                    VARCHAR(30)     NOT NULL,
    bank                    VARCHAR(30)     NOT NULL,
    reference               VARCHAR(30)     NOT NULL,
    current_solde           BIGINT          NOT NULL,
    last_update             DATETIME2       NOT NULL,
    since_year              BIGINT,
    initial_balance         BIGINT,
    csv_import_folder       VARCHAR(256),
    csv_row_ignored         BIGINT,
    csv_row_date            BIGINT,
    csv_row_solde           BIGINT,
    csv_column_number       BIGINT,
    csv_column_date         BIGINT,
    csv_date_format         VARCHAR(128),
    csv_column_description  BIGINT,
    csv_column_credit       BIGINT,
    csv_column_debit        BIGINT,
    csv_column_value        BIGINT
)

CREATE TABLE BALANCE_SHEET
(
    id_balance_sheet        BIGINT NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_account              BIGINT NOT NULL,
    [year]                  BIGINT,
    ending_solde            BIGINT,
    starting_balance        BIGINT,
    operation_count         BIGINT,
    cumul_credit            BIGINT,
    cumul_debit             BIGINT,
    cumul_none              BIGINT,
    cumul_saving            BIGINT,
    cumul_survival          BIGINT,
    cumul_cultural          BIGINT,
    cumul_optional          BIGINT,
    cumul_extra             BIGINT,
    cumul_income            BIGINT
)

CREATE TABLE ACCOUNT_USER_PROFILE
(
    id_profile              BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_user                 BIGINT          NULL,
    id_account              BIGINT          NULL,
    user_role               VARCHAR(16)     DEFAULT 'HOLDER' CHECK ((user_role IN ('ADMIN', 'HOLDER', 'VIEWER')))
)

CREATE TABLE CREDIT_CARD
(
    id_credit_card          BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_account_user_profile BIGINT          NULL,
    reference               BIGINT          NOT NULL,
    active                  BIT
)

CREATE TABLE CHECK_BOOK
(
    id_check_book           BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_account_user_profile BIGINT          NULL,
    first_check_number      BIGINT          NOT NULL,
    check_range             BIGINT          NOT NULL,
    active                  BIT
)

CREATE TABLE OPERATION_TYPE
(
    id_op_type              BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_account              BIGINT,
    op_type_label           VARCHAR(64),
    op_type_value           VARCHAR(16)     DEFAULT 'NONE' CHECK ((op_type_value IN ('NONE', 'SAVING', 'SURVIVAL', 'CULTURAL', 'OPTIONAL', 'EXTRA','INCOME'))),
    op_type_color           VARCHAR(16),
    op_type_icon            VARCHAR(32)
)

CREATE TABLE [OPERATION_MODE]
(
    id_op_mode              BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_account              BIGINT,
    op_mode_label           VARCHAR(64),
    op_mode_value           VARCHAR(20)     DEFAULT 'NONE' CHECK ((op_mode_value IN ('NONE', 'CREDIT_CARD', 'DIRECT_DEBIT', 'BANK_CHECK_IN', 'BANK_CHECK_OUT', 'BANK_TRANSFER_IN', 'BANK_TRANSFER_OUT', 'DEFERRED_OUT'))),
    op_mode_color           VARCHAR(16),
    op_mode_icon            VARCHAR(32)
)

CREATE TABLE MODE_KEY
(
    id_key                  BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_op_mode              BIGINT,
    key_label               VARCHAR(32) 
)

CREATE TABLE OPERATION_CATEGORY
(
    id_op_category          BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_account              BIGINT,
    op_category_label       VARCHAR(32),
    op_category_color       VARCHAR(16),
    op_category_type        VARCHAR(16)
)

CREATE TABLE OPERATION_SUB_CATEGORY
(
    id_op_sub_category      BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_op_category          BIGINT,
    op_sub_category_label   VARCHAR(32),
    op_sub_category_color   VARCHAR(16),
    op_sub_category_type    VARCHAR(16)
)

CREATE TABLE SUB_CATEGORY_KEY
(
    id_key                  BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_op_sub_category      BIGINT,
    key_label               VARCHAR(32) 
)

CREATE TABLE OPERATION_STATUS
(
    id_op_status            BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    op_status_value         VARCHAR(16)     DEFAULT 'NONE' CHECK ((op_status_value IN ('NONE', 'POINTED', 'TO_BE_CHECKED', 'SUSPICIOUS'))),
    op_status_color         VARCHAR(16)
)

CREATE TABLE OPERATION
(
    id_op                   BIGINT          NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_account              BIGINT          NOT NULL,
    category                VARCHAR(32)     NOT NULL,
    sub_category            VARCHAR(32)     NOT NULL,
    mode                    VARCHAR(20)     DEFAULT 'NONE' CHECK ((mode IN ('NONE', 'CREDIT_CARD', 'DIRECT_DEBIT', 'BANK_CHECK_IN', 'BANK_CHECK_OUT', 'BANK_TRANSFER_IN', 'BANK_TRANSFER_OUT', 'DEFERRED_OUT'))),
    [type]                  VARCHAR(16)     DEFAULT 'NONE' CHECK ((type IN ('NONE', 'SAVING', 'SURVIVAL', 'CULTURAL', 'OPTIONAL', 'EXTRA', 'INCOME'))),
    [status]                VARCHAR(16)     DEFAULT 'NONE' CHECK (([status] IN ('NONE', 'POINTED', 'TO_BE_CHECKED', 'SUSPICIOUS'))),
    [description]           VARCHAR(256)    NOT NULL,
    comment                 VARCHAR(256),
    [date]                  DATETIME2       NOT NULL,
    [value]                 BIGINT          NOT NULL
)

---------------------------------------------------------------------------
-- LIENS ENTRE LES TABLES
---------------------------------------------------------------------------

ALTER TABLE ACCOUNT_USER_PROFILE
    ADD CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES [USER] (id_user)
        ON DELETE SET NULL
        ON UPDATE CASCADE

ALTER TABLE ACCOUNT_USER_PROFILE
    ADD CONSTRAINT fk_account FOREIGN KEY (id_account) REFERENCES [ACCOUNT] (id_account)
        ON DELETE SET NULL
        ON UPDATE CASCADE

ALTER TABLE CREDIT_CARD
    ADD CONSTRAINT fk_account_user_profile_card FOREIGN KEY (id_account_user_profile) REFERENCES ACCOUNT_USER_PROFILE (id_profile)
        ON DELETE SET NULL
        ON UPDATE CASCADE

ALTER TABLE CHECK_BOOK
    ADD CONSTRAINT fk_account_user_profile_book FOREIGN KEY (id_account_user_profile) REFERENCES ACCOUNT_USER_PROFILE (id_profile)
        ON DELETE SET NULL
        ON UPDATE CASCADE

ALTER TABLE OPERATION_TYPE
    ADD CONSTRAINT fk_account_op_type FOREIGN KEY (id_account) REFERENCES [ACCOUNT] (id_account)
        ON DELETE SET NULL
        ON UPDATE CASCADE

ALTER TABLE [OPERATION_MODE]
    ADD CONSTRAINT fk_account_op_mode FOREIGN KEY (id_account) REFERENCES [ACCOUNT] (id_account)
        ON DELETE SET NULL
        ON UPDATE CASCADE

ALTER TABLE OPERATION_CATEGORY
ADD CONSTRAINT fk_account_op_category 
FOREIGN KEY (id_account) 
REFERENCES [ACCOUNT] (id_account)
ON DELETE CASCADE


ALTER TABLE OPERATION
ADD CONSTRAINT fk_account_op 
FOREIGN KEY (id_account) 
REFERENCES [ACCOUNT] (id_account)
ON DELETE CASCADE
       

ALTER TABLE OPERATION_SUB_CATEGORY
ADD CONSTRAINT fk_sub_category_category
FOREIGN KEY (id_op_category)
REFERENCES OPERATION_CATEGORY(id_op_category)
ON DELETE CASCADE;


ALTER TABLE SUB_CATEGORY_KEY
ADD CONSTRAINT fk_key_sub_category
FOREIGN KEY (id_op_sub_category)
REFERENCES OPERATION_SUB_CATEGORY(id_op_sub_category)
ON DELETE CASCADE;

ALTER TABLE MODE_KEY
ADD CONSTRAINT fk_key_mode
FOREIGN KEY (id_op_mode)
REFERENCES [OPERATION_MODE](id_op_mode)
ON DELETE CASCADE;
    
ALTER TABLE BALANCE_SHEET
ADD CONSTRAINT fk_account_balance_sheet 
FOREIGN KEY (id_account) 
REFERENCES [ACCOUNT] (id_account)
ON DELETE CASCADE    


