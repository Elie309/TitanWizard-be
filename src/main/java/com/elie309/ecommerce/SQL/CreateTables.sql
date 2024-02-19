CREATE SCHEMA IF NOT EXISTS ecommerce;
USE ecommerce;

CREATE TABLE IF NOT EXISTS account_type
(
account_type_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
account_type CHAR(30) UNIQUE NOT NULL,
account_type_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
account_type_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `account`
( 
account_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
account_firstname CHAR(30) NOT NULL,
account_middlename CHAR (30) NULL,
account_lastname CHAR(30) NOT NULL,

account_type INT UNSIGNED DEFAULT 1,

account_email Text NOT NULL,
account_password INT UNSIGNED NOT NULL,

account_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
account_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 
FOREIGN KEY (`account_type`) REFERENCES `account_type`(`account_type_id`)
 
);


CREATE TABLE IF NOT EXISTS phone
( 
phone_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
account_id INT UNSIGNED NOT NULL,
phone_area_code CHAR(3)	NOT NULL,
phone_number CHAR(20) NOT NULL,
phone_type INT UNSIGNED NOT NULL,
phone_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
phone_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

FOREIGN KEY (`account_id`) REFERENCES `account`(`account_id`)
);

CREATE TABLE IF NOT EXISTS phone_type
( 
phone_type_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
phone_type CHAR(30)	NOT NULL UNIQUE,
phone_type_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
phone_type_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS address_type
( 
address_type_id	INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
address_type CHAR(50) NOT NULL UNIQUE,
address_type_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
address_type_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS countries
(
country_code CHAR(2) NOT NULL PRIMARY KEY UNIQUE,
country_name CHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS address
( 
address_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
account_id 	INT UNSIGNED NOT NULL,
address_type_id INT UNSIGNED DEFAULT 1,
country_code CHAR(2) DEFAULT 'LB',

state CHAR(50) NOT NULL,
street CHAR(50) NOT NULL,
city CHAR(50) NOT NULL,
postal_code CHAR(50) NULL,

address_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
address_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

FOREIGN KEY (`account_id`) REFERENCES `account`(`account_id`),
FOREIGN KEY (`address_type_id`) REFERENCES `address_type`(`address_type_id`),
FOREIGN KEY (`country_code`) REFERENCES `countries`(`country_code`)

);



####### ITEMS ######


CREATE TABLE IF NOT EXISTS product_category
( 
product_category_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
product_category CHAR(50) NOT NULL,
product_category_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
product_category_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS product_subcategory
(
product_subcategory_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
product_category_id INT UNSIGNED NOT NULL,
product_subcategory CHAR(50) NOT NULL,
product_subcategory_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
product_subcategory_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

FOREIGN KEY (product_category_id) REFERENCES product_category(product_category_id)
);


CREATE TABLE IF NOT EXISTS product
(
product_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
product_title CHAR(255) NOT NULL,
product_description LONGTEXT,
product_sku CHAR(50) NULL DEFAULT '',

product_category_id INT UNSIGNED NOT NULL,
product_subcategory_id INT UNSIGNED,

product_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
product_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

FOREIGN KEY (product_category_id) REFERENCES product_category(product_category_id), 
FOREIGN KEY (product_subcategory_id) REFERENCES product_subcategory(product_subcategory_id)

);


##### PRICE ######


CREATE TABLE price_type
( 
price_type_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
price_type CHAR(50) NOT NULL UNIQUE,
price_type_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
price_type_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


#startdate & enddate very important for difference between old and new price
CREATE TABLE IF NOT EXISTS price
(
price_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
product_id INT UNSIGNED NOT NULL,
price_type_id INT UNSIGNED,

price_amount INT UNSIGNED NOT NULL,
start_date DATE NOT NULL,
end_date DATE,

price_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
price_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

FOREIGN KEY (product_id) REFERENCES product(product_id),
FOREIGN KEY (price_type_id) REFERENCES price_type(price_type_id)

);

