CREATE TABLE Brands (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE Models (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT FK_Model_Brand FOREIGN KEY (brand_id) REFERENCES Brands(id)
);

CREATE TABLE Vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    registration VARCHAR(10) NOT NULL,
    model_id INT NOT NULL,
    mileage BIGINT NOT NULL,
    registrationDate DATE NOT NULL,
    comment VARCHAR(500),
    CONSTRAINT FK_Vehicle_Model FOREIGN KEY (model_id) REFERENCES Models(id)
);

CREATE TABLE Services (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    service_id INT NOT NULL,
    phone VARCHAR(20),
    mail VARCHAR(50),
    CONSTRAINT FK_Employee_Service FOREIGN KEY (service_id) REFERENCES Services(id)
);

CREATE TABLE Assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    employee_id INT,
    dateStart DATE NOT NULL,
    dateEnd DATE NOT NULL,
    comment VARCHAR(500),
    CONSTRAINT FK_Assignment_Vehicle FOREIGN KEY (vehicle_id) REFERENCES Vehicles(id),
    CONSTRAINT FK_Assignment_Employee FOREIGN KEY (employee_id) REFERENCES Employees(id)
);

