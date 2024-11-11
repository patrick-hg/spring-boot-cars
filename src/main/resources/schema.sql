CREATE TABLE IF NOT EXISTS `car` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `brand_name` varchar(100) NOT NULL,
    `model_name` varchar(100) NOT NULL,
    `reg_no` varchar(20) NOT NULL,
    `car_type` varchar(20) NOT NULL,
    `production_year` integer NOT NULL,
    `kms` integer NOT NULL,
    `price` float NOT NULL
);

CREATE TABLE IF NOT EXISTS `book` (
    `id` int AUTO_INCREMENT  PRIMARY KEY,
    `title` varchar(100) NOT NULL,
    `authors` varchar(100) NOT NULL,
    `publisher` varchar(100) NOT NULL,
    `isbn` varchar(30) NOT NULL,
    `year_published` integer NOT NULL,
    `price` integer NOT NULL
);
