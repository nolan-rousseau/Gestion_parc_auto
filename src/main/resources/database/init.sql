-- Création d'une table test
CREATE TABLE IF NOT EXISTS Personne (
                                        id INT PRIMARY KEY,
                                        nom VARCHAR(255)
    );

-- Insérer quelques données de test
INSERT INTO Personne (id, nom) VALUES (1, 'Alice');
INSERT INTO Personne (id, nom) VALUES (2, 'Bob');
