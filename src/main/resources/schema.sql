SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS users
(
    user_id       int          NOT NULL AUTO_INCREMENT,
    user_name     varchar(255) NOT NULL UNIQUE,
    user_password varchar(255) NOT NULL,
    user_email 	  varchar(255) NOT NULL,
    user_address  varchar(255) NOT NULL,
    user_phone    int(11) NOT NULL,
    PRIMARY KEY (user_id)
);


CREATE TABLE IF NOT EXISTS on_sale_assets
(
    id int NOT NULL AUTO_INCREMENT,
    asset_type varchar(255) NOT NULL,
    asset_name varchar(255) NOT NULL,
    price FLOAT(2) NOT NULL,
    user_id int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id int NOT NULL AUTO_INCREMENT,
    asset_id int NOT NULL,
    buyer_id int NOT NULL,
    seller_id int NOT NULL,
    state varchar(255) NOT NULL,
    date DATETIME NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES on_sale_assets(id),
    FOREIGN KEY (buyer_id) REFERENCES users(user_id),
    FOREIGN KEY (seller_id) REFERENCES users(user_id),
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS user_logs
(
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    login_time DATETIME NOT NULL,
    logout_time DATETIME NOT NULL,
    browser varchar(255) NOT NULL,
	ip varchar(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    PRIMARY KEY (id)
);

