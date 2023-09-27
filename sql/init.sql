CREATE EXTENSION IF NOT EXISTS CUBE;
CREATE EXTENSION IF NOT EXISTS EARTHDISTANCE;
DROP TABLE IF EXISTS ACCOUNT;
CREATE TABLE ACCOUNT 
(
	Id SERIAL PRIMARY KEY,
	Email TEXT UNIQUE NOT NULL,
	PASSWORD TEXT NOT NULL,
	NICKNAME TEXT UNIQUE NOT NULL,
	PHONE_NUMBER TEXT UNIQUE NOT NULL,
	PAYMENT_CARD TEXT,
	IS_VERIFIED BOOLEAN NOT NULL,
	PROFILE_PHOTO TEXT
);
DROP TABLE IF EXISTS DELETED_ACCOUNT;
CREATE TABLE DELETED_ACCOUNT 
(
	Id SERIAL PRIMARY KEY,
	Email TEXT UNIQUE NOT NULL,
	PHONE_NUMBER TEXT UNIQUE NOT NULL,
	DELETE_TIME TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS STORE;
CREATE TABLE STORE
(
	ID SERIAL PRIMARY KEY,
	ACCOUNT_ID INT NOT NULL,
	NAME TEXT NOT NULL,
	LATITUDE FLOAT8 NOT NULL,
	LONGITUDE FLOAT8 NOT NULL,
	ADDRESS TEXT,
	INFO TEXT,
	PHONE_NUMBER TEXT NOT NULL,
	CAN_RESERVATION BOOL,
	IS_OPEN BOOL,
	PROFILE_PHOTO TEXT,
	PHOTOS TEXT NOT NULL
);
DROP TABLE IF EXISTS REVIEW;
CREATE TABLE REVIEW
(
	ID SERIAL PRIMARY KEY,
	ACCOUNT_ID INT NOT NULL,
	STORE_ID INT NOT NULL,
	DETAIL TEXT,
	WRITING_TIME TIMESTAMP NOT NULL,
	RATING INT NOT NULL,
	PHOTOS TEXT NOT NULL
);
DROP TABLE IF EXISTS MENU;
CREATE TABLE MENU
(
	ID SERIAL PRIMARY KEY,
	STORE_ID INT NOT NULL,
	NAME TEXT NOT NULL,
	PRICE INT NOT NULL,
	PHOTO TEXT,
	CATEGORY TEXT
);
DROP TABLE IF EXISTS TABLE_ITEM;
CREATE TABLE TABLE_ITEM
(
	ID SERIAL PRIMARY KEY,
	STORE_ID INT NOT NULL,
	NAME TEXT,
	COORD_X TEXT NOT NULL,
	COORD_Y TEXT NOT NULL,
	WIDTH TEXT NOT NULL,
	HEIGHT TEXT NOT NULL,
	PRIVATE_KEY TEXT NOT NULL
);
DROP TABLE IF EXISTS ORDER_ITEM;
CREATE TABLE ORDER_ITEM
(
	ID SERIAL PRIMARY KEY,
	ACCOUNT_ID INT NOT NULL,
	STORE_ID INT NOT NULL,
	TABLE_ID INT NOT NULL,
	ORDER_TIME TIMESTAMP,
	PAYMENT_TIME TIMESTAMP,
	RESERVATION_TIME TIMESTAMP,
	ORDER_CODE SMALLINT NOT NULL,
	RESERVATION_DENY_DETAIL Text
);
DROP TABLE IF EXISTS ORDER_DETAIL;
CREATE TABLE ORDER_DETAIL
(
	ID SERIAL PRIMARY KEY,
	ORDER_ID INT NOT NULL,
	MENU_ID INT NOT NULL,
	AMOUNT INT NOT NULL
);
DROP TABLE IF EXISTS AUTH;
CREATE TABLE AUTH
(
	ID SERIAL PRIMARY KEY,
	ACCOUNT_ID INT UNIQUE NOT NULL,
	REFRESH_TOKEN TEXT
)
DROP TABLE IF EXISTS OPERATING_DAY;
CREATE TABLE OPERATING_DAY
(
	STORE_ID INT NOT NULL,
	WEEK SMALLINT NOT NULL,
	OPEN_TIME TIME NOT NULL,
	CLOSE_TIME TIME NOT NULL
)
/*
	WISH_LIST,
	COUPON_LIST
*/