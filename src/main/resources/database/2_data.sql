INSERT INTO Brands (name) VALUES
    ('Renault'), ('Peugeot'), ('Citroën');

INSERT INTO Models (brand_id, name) VALUES
    (1, 'Clio V'), (1, 'Zoe'),
    (2, '208'), (2, '3008'),
    (3, 'C3');

INSERT INTO Vehicles (registration, model_id, mileage, registrationDate, comment) VALUES
    ('FX-210-EB', 2, 32000, '2020-03-20', NULL),
    ('GE-852-QQ', 1, 45000, '2021-05-12', 'Portière abimée'),
    ('GM-404-BK', 3, 12000, '2022-10-01', 'Antenne volée'),
    ('HB-109-RS', 5, 5000, '2023-01-15', 'Feu avant cassé');

INSERT INTO Services (name) VALUES
    ('Voirie'), ('Espaces verts'), ('Sécurité'), ('Logisitque');

INSERT INTO Employees (firstname, lastname, service_id, phone, mail) VALUES
    ('Jean', 'Dupont', 1, '0601020304', 'jean.dupont@entreprise.com'),
    ('Alice', 'Martin', 2, '0611223344', 'alice.martin@entreprise.com'),
    ('Robert', 'Durand', 4, '0788990011', 'robert.durand@entreprise.com');

INSERT INTO Assignments (vehicle_id, employee_id, dateStart, dateEnd, comment) VALUES
    (1, 1, '2024-01-01', '2024-12-31', 'A percuté un sanglier'),
    (3, 3, '2024-02-15', '2024-02-20', 'A percuté un chevreuil'),
    (2, 2, '2023-01-01', '2023-06-30', 'A percuté M. le maire ???');