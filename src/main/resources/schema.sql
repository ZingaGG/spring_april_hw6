create table notes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description varchar(50) NOT NULL,
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);