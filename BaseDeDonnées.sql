-- Création de la base de données
CREATE DATABASE IF NOT EXISTS gestion_vehicules;
USE gestion_vehicules;

-- Table Véhicule
CREATE TABLE Vehicule (
    idVehicule INT AUTO_INCREMENT PRIMARY KEY,
    Immatriculation VARCHAR(20) NOT NULL UNIQUE,
    Marque VARCHAR(50) NOT NULL,
    Modele VARCHAR(50) NOT NULL,
    DateAchat DATE NOT NULL,
    Statut VARCHAR(20) DEFAULT 'Disponible',
    INDEX idx_immatriculation (Immatriculation)
);

-- Table Agent
CREATE TABLE Agent (
    idAgent INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(50) NOT NULL,
    Prenom VARCHAR(50) NOT NULL,
    Mail VARCHAR(100) NOT NULL UNIQUE,
    MotDePasse VARCHAR(255) NOT NULL,
    Statut VARCHAR(20) DEFAULT 'Actif',
    INDEX idx_mail (Mail)
);

-- Table Affectation
CREATE TABLE Affectation (
    idAffectation INT AUTO_INCREMENT PRIMARY KEY,
    idVehicule INT NOT NULL,
    idAgent INT NOT NULL,
    DateDebut DATE NOT NULL,
    DateFin DATE,
    FOREIGN KEY (idVehicule) REFERENCES Vehicule(idVehicule) ON DELETE CASCADE,
    FOREIGN KEY (idAgent) REFERENCES Agent(idAgent) ON DELETE CASCADE,
    INDEX idx_date_debut (DateDebut),
    INDEX idx_date_fin (DateFin)
);

-- Insertion de données d'exemple (optionnel)
INSERT INTO Vehicule (Immatriculation, Marque, Modele, DateAchat, Statut) VALUES
('AB-123-CD', 'Renault', 'Clio', '2023-01-15', 'Disponible'),
('EF-456-GH', 'Peugeot', '308', '2022-05-20', 'En service'),
('IJ-789-KL', 'Citroën', 'C4', '2024-03-10', 'Disponible');

INSERT INTO Agent (Nom, Prenom, Mail, MotDePasse, Statut) VALUES
('Dupont', 'Jean', 'jean.dupont@example.com', SHA2('pass123', 256), 'Actif'),
('Martin', 'Marie', 'marie.martin@example.com', SHA2('secure456', 256), 'Actif'),
('Bernard', 'Paul', 'paul.bernard@example.com', SHA2('admin789', 256), 'Inactif');

INSERT INTO Affectation (idVehicule, idAgent, DateDebut, DateFin) VALUES
(2, 1, '2024-01-01', NULL),
(1, 2, '2024-02-15', '2024-05-15');