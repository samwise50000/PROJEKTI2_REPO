Week3 - Tehtävä: Home Assignment 3

Brief explanation of the app found in Week3 package under /src/.

1.) Localization Approach:

The solution uses a localization approach to support multiple languages in the application. It loads resource bundles based on the selected language and updates the UI elements (labels, buttons, etc.) accordingly.

2.) Database Localization:

The database localization aspect involves storing localized data in separate tables based on the selected language. For example, the application creates tables like employee_english, employee_farsi, and employee_japanese to store employee data in different languages.

3.) Character Encoding:
To ensure proper handling of Unicode characters, the solution sets the character encoding for the database connection to UTF-8 (characterEncoding=UTF-8 in the database URL). This ensures that the database can store and retrieve characters from different languages correctly.

4.) Saving Data:
When saving data, the application dynamically determines the table name based on the selected language and inserts the data into the corresponding table. This ensures that the data is stored in the appropriate language-specific table.

Overall, the solution effectively implements localization in the application by supporting multiple languages and storing localized data in the database. It demonstrates a practical approach to handle localization challenges in a JavaFX application.

Database script used:

CREATE DATABASE demobase;

USE demobase;

CREATE TABLE employee_english (
 id INT AUTO_INCREMENT PRIMARY KEY,
 first_name VARCHAR(50),
 last_name VARCHAR(50),
 email VARCHAR(100)
);

CREATE TABLE employee_farsi (
 id INT AUTO_INCREMENT PRIMARY KEY,
 first_name VARCHAR(50),
 last_name VARCHAR(50),
 email VARCHAR(100)
);

CREATE TABLE employee_japanese (
 id INT AUTO_INCREMENT PRIMARY KEY,
 first_name VARCHAR(50),
 last_name VARCHAR(50),
 email VARCHAR(100)
);


ALTER TABLE employee_japanese
MODIFY COLUMN first_name VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
MODIFY COLUMN last_name VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
MODIFY COLUMN email VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE employee_farsi
MODIFY COLUMN first_name VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
MODIFY COLUMN last_name VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
MODIFY COLUMN email VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
