CREATE DATABASE Cabinet;
USE Cabinet;

-- Create tables without foreign key dependencies first
CREATE TABLE FicheSuivi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    observations TEXT
);

CREATE TABLE Agenda (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Compte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    tel VARCHAR(20),
    email VARCHAR(255) NOT NULL,
    pswd VARCHAR(255) NOT NULL
);

CREATE TABLE QO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT NOT NULL,
    reponse TEXT
);

CREATE TABLE Anamnese (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE EpreuveClinique (
    id INT AUTO_INCREMENT PRIMARY KEY,
    details TEXT
);

CREATE TABLE Diagnostic (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT
);

CREATE TABLE ProjetTherapeutique (
    id INT AUTO_INCREMENT PRIMARY KEY,
    texte TEXT
);

CREATE TABLE Trouble (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    categorie VARCHAR(255)
);

CREATE TABLE RendezVous (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATETIME NOT NULL,
    duree DOUBLE,
    type VARCHAR(255),
    resume TEXT
);

CREATE TABLE Question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255),
    question TEXT,
    score INT
);

CREATE TABLE Exercice (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consigne TEXT NOT NULL,
    nomMateriel VARCHAR(255),
    score TEXT
);

CREATE TABLE Test (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    capacite VARCHAR(255),
    compteRendu DOUBLE,
    conclusion TEXT
);

CREATE TABLE TestExercice (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    capacite VARCHAR(255),
    compteRendu DOUBLE,
    conclusion TEXT
);

CREATE TABLE Terme (
    id INT AUTO_INCREMENT PRIMARY KEY,
    terme VARCHAR(255) NOT NULL
);

-- Create tables with foreign key dependencies next, ensuring referenced tables are created first
CREATE TABLE DossierPatient (
    numDossier INT PRIMARY KEY,
    ficheSuivi_id INT,
    FOREIGN KEY (ficheSuivi_id) REFERENCES FicheSuivi(id)
);

CREATE TABLE Anamnese_QO (
    anamnese_id INT,
    qo_id INT,
    PRIMARY KEY (anamnese_id, qo_id),
    FOREIGN KEY (anamnese_id) REFERENCES Anamnese(id),
    FOREIGN KEY (qo_id) REFERENCES QO(id)
);

CREATE TABLE Diagnostic_Trouble (
    diagnostic_id INT,
    trouble_id INT,
    PRIMARY KEY (diagnostic_id, trouble_id),
    FOREIGN KEY (diagnostic_id) REFERENCES Diagnostic(id),
    FOREIGN KEY (trouble_id) REFERENCES Trouble(id)
);

CREATE TABLE Test_Question (
    test_id INT,
    question_id INT,
    PRIMARY KEY (test_id, question_id),
    FOREIGN KEY (test_id) REFERENCES Test(id),
    FOREIGN KEY (question_id) REFERENCES Question(id)
);

CREATE TABLE TestExercice_Exercice (
    test_id INT,
    exercice_id INT,
    PRIMARY KEY (test_id, exercice_id),
    FOREIGN KEY (test_id) REFERENCES TestExercice(id),
    FOREIGN KEY (exercice_id) REFERENCES Exercice(id)
);

CREATE TABLE EpreuveClinique_Observation (
    epreuveClinique_id INT,
    observation VARCHAR(255), -- Use VARCHAR with a specific length
    PRIMARY KEY (epreuveClinique_id, observation),
    FOREIGN KEY (epreuveClinique_id) REFERENCES EpreuveClinique(id)
);


CREATE TABLE EpreuveClinique_Test (
    epreuveClinique_id INT,
    test_id INT,
    PRIMARY KEY (epreuveClinique_id, test_id),
    FOREIGN KEY (epreuveClinique_id) REFERENCES EpreuveClinique(id),
    FOREIGN KEY (test_id) REFERENCES Test(id)
);

CREATE TABLE Exercice_Score (
    exercice_id INT,
    score INT,
    PRIMARY KEY (exercice_id, score),
    FOREIGN KEY (exercice_id) REFERENCES Exercice(id)
);

CREATE TABLE Objectif (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    terme_id INT,
    isAtteint BOOLEAN,
    FOREIGN KEY (terme_id) REFERENCES Terme(id)
);

CREATE TABLE FicheSuivi_Objectif (
    ficheSuivi_id INT,
    objectif_id INT,
    PRIMARY KEY (ficheSuivi_id, objectif_id),
    FOREIGN KEY (ficheSuivi_id) REFERENCES FicheSuivi(id),
    FOREIGN KEY (objectif_id) REFERENCES Objectif(id)
);

CREATE TABLE BO (
    id INT AUTO_INCREMENT PRIMARY KEY,
    anamnese_id INT,
    epreuve_id INT NOT NULL,
    diagnostic_id INT NOT NULL,
    projet_id INT NOT NULL,
    FOREIGN KEY (anamnese_id) REFERENCES Anamnese(id),
    FOREIGN KEY (epreuve_id) REFERENCES EpreuveClinique(id),
    FOREIGN KEY (diagnostic_id) REFERENCES Diagnostic(id),
    FOREIGN KEY (projet_id) REFERENCES ProjetTherapeutique(id)
);

CREATE TABLE DossierPatient_RendezVous (
    dossierPatient_id INT,
    rendezVous_id INT,
    PRIMARY KEY (dossierPatient_id, rendezVous_id),
    FOREIGN KEY (dossierPatient_id) REFERENCES DossierPatient(numDossier),
    FOREIGN KEY (rendezVous_id) REFERENCES RendezVous(id)
);

CREATE TABLE DossierPatient_BO (
    dossierPatient_id INT,
    bo_id INT,
    PRIMARY KEY (dossierPatient_id, bo_id),
    FOREIGN KEY (dossierPatient_id) REFERENCES DossierPatient(numDossier),
    FOREIGN KEY (bo_id) REFERENCES BO(id)
);

CREATE TABLE Orthophoniste (
    id INT AUTO_INCREMENT PRIMARY KEY,
    compte_id INT,
    agenda_id INT,
    FOREIGN KEY (compte_id) REFERENCES Compte(id),
    FOREIGN KEY (agenda_id) REFERENCES Agenda(id)
);
CREATE TABLE Patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    dateDeNaissance DATE NOT NULL,
    lieuDeNaissance VARCHAR(255) NOT NULL,
    isFirstTime BOOLEAN NOT NULL,
    idDossier INT NOT NULL,
    FOREIGN KEY (idDossier) REFERENCES DossierPatient(numDossier)
);
CREATE TABLE Orthophoniste_DossierPatient (
    orthophoniste_id INT,
    dossierPatient_numDossier INT,
    PRIMARY KEY (orthophoniste_id, dossierPatient_numDossier),
    FOREIGN KEY (orthophoniste_id) REFERENCES Orthophoniste(id),
    FOREIGN KEY (dossierPatient_numDossier) REFERENCES DossierPatient(numDossier)
);

CREATE TABLE Orthophoniste_Patient (
    orthophoniste_id INT,
    patient_id INT,
    PRIMARY KEY (orthophoniste_id, patient_id),
    FOREIGN KEY (orthophoniste_id) REFERENCES Orthophoniste(id),
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);




CREATE TABLE Adulte (
    id INT PRIMARY KEY,
    diplome VARCHAR(255),
    profession VARCHAR(255),
    tel VARCHAR(20),
    FOREIGN KEY (id) REFERENCES Patient(id)
);

CREATE TABLE Enfant (
    id INT PRIMARY KEY,
    classeDeEtude VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES Patient(id)
);

CREATE TABLE TelParent (
    enfant_id INT,
    telParent VARCHAR(20),
    PRIMARY KEY (enfant_id, telParent),
    FOREIGN KEY (enfant_id) REFERENCES Enfant(id)
);

-- Adding missing foreign key reference to Agenda table for Orthophoniste
ALTER TABLE Orthophoniste
ADD FOREIGN KEY (agenda_id) REFERENCES Agenda(id);
