DROP DATABASE IF EXISTS cabinet;
CREATE DATABASE cabinet;
USE cabinet;

CREATE TABLE Compte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    tel VARCHAR(20),
    email VARCHAR(255) NOT NULL,
    pswd VARCHAR(255) NOT NULL
);

CREATE TABLE enfant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    date_naissance DATETIME,
    lieu_naissance VARCHAR(255),
    adresse VARCHAR(255),
    age INT,
    classe_etude VARCHAR(255),
    tel_parent_1 VARCHAR(20),
    tel_parent_2 VARCHAR(20)
);

CREATE TABLE adulte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    date_naissance DATETIME,
    lieu_naissance VARCHAR(255),
    adresse VARCHAR(255),
    age INT,
    diplome VARCHAR(255),
    profession VARCHAR(255),
    tel VARCHAR(20)
);

CREATE TABLE proposition (
    id INT AUTO_INCREMENT PRIMARY KEY,
    val TEXT
);

CREATE TABLE proposition_erronnee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    val TEXT
);

CREATE TABLE proposition_valide (
    id INT AUTO_INCREMENT PRIMARY KEY,
    val TEXT
);

CREATE TABLE qo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT,
    reponse TEXT,
    score DECIMAL,
    categorie VARCHAR(255)
);

CREATE TABLE qcu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT,
    proposition_id INT,
    proposition_erronnee_id INT,
    proposition_valide TEXT,
    score DECIMAL,
    categorie VARCHAR(255)
);

CREATE TABLE qcm (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT,
    proposition_id INT,
    proposition_erronnee_id INT,
    proposition_valide_id INT,
    score DECIMAL,
    categorie VARCHAR(255)
);

CREATE TABLE score (
    id INT AUTO_INCREMENT PRIMARY KEY,
    val DECIMAL
);

CREATE TABLE exercice (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consigne TEXT,
    materiele VARCHAR(255)
);

CREATE TABLE test_exercice (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    capacite VARCHAR(255),
    compteRendu DECIMAL,
    conclusion TEXT
);

CREATE TABLE test_questionnaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT,
    reponse TEXT,
    score DECIMAL,
    categorie VARCHAR(255)
);

CREATE TABLE anamnese (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE epreuve_clinique (
    id INT AUTO_INCREMENT PRIMARY KEY,
    details TEXT
);

CREATE TABLE trouble (
    id INT AUTO_INCREMENT PRIMARY KEY,
    categorie ENUM('Deglutition', 'NeuroDeveloppementaux', 'Cognitif')
);

CREATE TABLE diagnostic (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT
);

CREATE TABLE projet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    texte TEXT
);

CREATE TABLE bo (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE objective (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    terme ENUM('Court', 'Moyen', 'Long'),
    is_atteint BOOLEAN
);

CREATE TABLE fiche_suivi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    observations TEXT
);

CREATE TABLE dossier_enfant (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE dossier_adulte (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE rendez_vous_consultation_enfant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_rv DATETIME,
    duree DOUBLE,
    resume_rv TEXT
);

CREATE TABLE rendez_vous_consultation_adulte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_rv DATETIME,
    duree DOUBLE,
    resume_rv TEXT
);

CREATE TABLE rendez_vous_seance_suivi_enfant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_rv DATETIME,
    is_presentiel BOOLEAN,
    resume_rv VARCHAR(255)
);

CREATE TABLE rendez_vous_seance_suivi_adulte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_rv DATETIME,
    is_presentiel BOOLEAN,
    resume_rv VARCHAR(255)
);

CREATE TABLE orthophoniste (
    id INT AUTO_INCREMENT PRIMARY KEY,
    compte_id INT,
    FOREIGN KEY (compte_id) REFERENCES Compte(id)
);

-- Link tables for relationships
CREATE TABLE anamnese_qo (
    anamnese_id INT,
    qo_id INT,
    PRIMARY KEY (anamnese_id, qo_id),
    FOREIGN KEY (anamnese_id) REFERENCES anamnese(id),
    FOREIGN KEY (qo_id) REFERENCES qo(id)
);

CREATE TABLE epreuve_clinique_test_exercice (
    epreuve_clinique_id INT,
    test_exercice_id INT,
    PRIMARY KEY (epreuve_clinique_id, test_exercice_id),
    FOREIGN KEY (epreuve_clinique_id) REFERENCES epreuve_clinique(id),
    FOREIGN KEY (test_exercice_id) REFERENCES test_exercice(id)
);

CREATE TABLE epreuve_clinique_test_questionnaire (
    epreuve_clinique_id INT,
    test_questionnaire_id INT,
    PRIMARY KEY (epreuve_clinique_id, test_questionnaire_id),
    FOREIGN KEY (epreuve_clinique_id) REFERENCES epreuve_clinique(id),
    FOREIGN KEY (test_questionnaire_id) REFERENCES test_questionnaire(id)
);

CREATE TABLE diagnostic_trouble (
    diagnostic_id INT,
    trouble_id INT,
    PRIMARY KEY (diagnostic_id, trouble_id),
    FOREIGN KEY (diagnostic_id) REFERENCES diagnostic(id),
    FOREIGN KEY (trouble_id) REFERENCES trouble(id)
);

CREATE TABLE bo_anamnese (
    bo_id INT,
    anamnese_id INT,
    PRIMARY KEY (bo_id, anamnese_id),
    FOREIGN KEY (bo_id) REFERENCES bo(id),
    FOREIGN KEY (anamnese_id) REFERENCES anamnese(id)
);

CREATE TABLE bo_epreuve_clinique (
    bo_id INT,
    epreuve_clinique_id INT,
    PRIMARY KEY (bo_id, epreuve_clinique_id),
    FOREIGN KEY (bo_id) REFERENCES bo(id),
    FOREIGN KEY (epreuve_clinique_id) REFERENCES epreuve_clinique(id)
);

CREATE TABLE bo_diagnostic (
    bo_id INT,
    diagnostic_id INT,
    PRIMARY KEY (bo_id, diagnostic_id),
    FOREIGN KEY (bo_id) REFERENCES bo(id),
    FOREIGN KEY (diagnostic_id) REFERENCES diagnostic(id)
);

CREATE TABLE bo_projet (
    bo_id INT,
    projet_id INT,
    PRIMARY KEY (bo_id, projet_id),
    FOREIGN KEY (bo_id) REFERENCES bo(id),
    FOREIGN KEY (projet_id) REFERENCES projet(id)
);

CREATE TABLE fiche_suivi_objective (
    fiche_suivi_id INT,
    objective_id INT,
    PRIMARY KEY (fiche_suivi_id, objective_id),
    FOREIGN KEY (fiche_suivi_id) REFERENCES fiche_suivi(id),
    FOREIGN KEY (objective_id) REFERENCES objective(id)
);

CREATE TABLE dossier_enfant_enfant (
    dossier_enfant_id INT,
    enfant_id INT,
    PRIMARY KEY (dossier_enfant_id, enfant_id),
    FOREIGN KEY (dossier_enfant_id) REFERENCES dossier_enfant(id),
    FOREIGN KEY (enfant_id) REFERENCES enfant(id)
);

CREATE TABLE dossier_enfant_bo (
    dossier_enfant_id INT,
    bo_id INT,
    PRIMARY KEY (dossier_enfant_id, bo_id),
    FOREIGN KEY (dossier_enfant_id) REFERENCES dossier_enfant(id),
    FOREIGN KEY (bo_id) REFERENCES bo(id)
);

CREATE TABLE dossier_enfant_fiche_suivi (
    dossier_enfant_id INT,
    fiche_suivi_id INT,
    PRIMARY KEY (dossier_enfant_id, fiche_suivi_id),
    FOREIGN KEY (dossier_enfant_id) REFERENCES dossier_enfant(id),
    FOREIGN KEY (fiche_suivi_id) REFERENCES fiche_suivi(id)
);

CREATE TABLE dossier_adulte_adulte (
    dossier_adulte_id INT,
    adulte_id INT,
    PRIMARY KEY (dossier_adulte_id, adulte_id),
    FOREIGN KEY (dossier_adulte_id) REFERENCES dossier_adulte(id),
    FOREIGN KEY (adulte_id) REFERENCES adulte(id)
);

CREATE TABLE dossier_adulte_bo (
    dossier_adulte_id INT,
    bo_id INT,
    PRIMARY KEY (dossier_adulte_id, bo_id),
    FOREIGN KEY (dossier_adulte_id) REFERENCES dossier_adulte(id),
    FOREIGN KEY (bo_id) REFERENCES bo(id)
);

CREATE TABLE dossier_adulte_fiche_suivi (
    dossier_adulte_id INT,
    fiche_suivi_id INT,
    PRIMARY KEY (dossier_adulte_id, fiche_suivi_id),
    FOREIGN KEY (dossier_adulte_id) REFERENCES dossier_adulte(id),
    FOREIGN KEY (fiche_suivi_id) REFERENCES fiche_suivi(id)
);

CREATE TABLE rendez_vous_consultation_enfant_enfant (
    rendez_vous_consultation_enfant_id INT,
    enfant_id INT,
    PRIMARY KEY (rendez_vous_consultation_enfant_id, enfant_id),
    FOREIGN KEY (rendez_vous_consultation_enfant_id) REFERENCES rendez_vous_consultation_enfant(id),
    FOREIGN KEY (enfant_id) REFERENCES enfant(id)
);

CREATE TABLE rendez_vous_consultation_adulte_adulte (
    rendez_vous_consultation_adulte_id INT,
    adulte_id INT,
    PRIMARY KEY (rendez_vous_consultation_adulte_id, adulte_id),
    FOREIGN KEY (rendez_vous_consultation_adulte_id) REFERENCES rendez_vous_consultation_adulte(id),
    FOREIGN KEY (adulte_id) REFERENCES adulte(id)
);

CREATE TABLE rendez_vous_seance_suivi_enfant_dossier (
    rendez_vous_seance_suivi_enfant_id INT,
    dossier_id INT,
    PRIMARY KEY (rendez_vous_seance_suivi_enfant_id, dossier_id),
    FOREIGN KEY (rendez_vous_seance_suivi_enfant_id) REFERENCES rendez_vous_seance_suivi_enfant(id),
    FOREIGN KEY (dossier_id) REFERENCES dossier_enfant(id)
);

CREATE TABLE rendez_vous_seance_suivi_adulte_dossier (
    rendez_vous_seance_suivi_adulte_id INT,
    dossier_id INT,
    PRIMARY KEY (rendez_vous_seance_suivi_adulte_id, dossier_id),
    FOREIGN KEY (rendez_vous_seance_suivi_adulte_id) REFERENCES rendez_vous_seance_suivi_adulte(id),
    FOREIGN KEY (dossier_id) REFERENCES dossier_adulte(id)
);

CREATE TABLE orthophoniste_dossier_enfant (
    orthophoniste_id INT,
    dossier_enfant_id INT,
    PRIMARY KEY (orthophoniste_id, dossier_enfant_id),
    FOREIGN KEY (orthophoniste_id) REFERENCES orthophoniste(id),
    FOREIGN KEY (dossier_enfant_id) REFERENCES dossier_enfant(id)
);

CREATE TABLE orthophoniste_dossier_adulte (
    orthophoniste_id INT,
    dossier_adulte_id INT,
    PRIMARY KEY (orthophoniste_id, dossier_adulte_id),
    FOREIGN KEY (orthophoniste_id) REFERENCES orthophoniste(id),
    FOREIGN KEY (dossier_adulte_id) REFERENCES dossier_adulte(id)
);

CREATE TABLE orthophoniste_rv_seance_suivi_enfant (
    orthophoniste_id INT,
    rv_seance_suivi_enfant_id INT,
    PRIMARY KEY (orthophoniste_id, rv_seance_suivi_enfant_id),
    FOREIGN KEY (orthophoniste_id) REFERENCES orthophoniste(id),
    FOREIGN KEY (rv_seance_suivi_enfant_id) REFERENCES rendez_vous_seance_suivi_enfant(id)
);

CREATE TABLE orthophoniste_rv_seance_suivi_adulte (
    orthophoniste_id INT,
    rv_seance_suivi_adulte_id INT,
    PRIMARY KEY (orthophoniste_id, rv_seance_suivi_adulte_id),
    FOREIGN KEY (orthophoniste_id) REFERENCES orthophoniste(id),
    FOREIGN KEY (rv_seance_suivi_adulte_id) REFERENCES rendez_vous_seance_suivi_adulte(id)
);

CREATE TABLE orthophoniste_rv_consultation_enfant (
    orthophoniste_id INT,
    rv_consultation_enfant_id INT,
    PRIMARY KEY (orthophoniste_id, rv_consultation_enfant_id),
    FOREIGN KEY (orthophoniste_id) REFERENCES orthophoniste(id),
    FOREIGN KEY (rv_consultation_enfant_id) REFERENCES rendez_vous_consultation_enfant(id)
);

CREATE TABLE orthophoniste_rv_consultation_adulte (
    orthophoniste_id INT,
    rv_consultation_adulte_id INT,
    PRIMARY KEY (orthophoniste_id, rv_consultation_adulte_id),
    FOREIGN KEY (orthophoniste_id) REFERENCES orthophoniste(id),
    FOREIGN KEY (rv_consultation_adulte_id) REFERENCES rendez_vous_consultation_adulte(id)
);
