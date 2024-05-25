package application.net.gui;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.net.cabinet.Adulte;
import application.net.cabinet.Anamnese;
import application.net.cabinet.BO;
import application.net.cabinet.Compte;
import application.net.cabinet.ConsultationAdulte;
import application.net.cabinet.ConsultationEnfant;
import application.net.cabinet.Diagnostic;
import application.net.cabinet.DossierPatient;
import application.net.cabinet.Enfant;
import application.net.cabinet.EpreuveClinique;
import application.net.cabinet.FicheSuivi;
import application.net.cabinet.Genre;
import application.net.cabinet.Patient;
import application.net.cabinet.RendezVous;
import application.net.cabinet.RendezVousType;

@SuppressWarnings("unused")
public class DatabaseUtil {

    // Database connection constants
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3305/cabinet";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "31415926";

    // Establishes a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    // Closes the database connection
    static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    // Closes the ResultSet
    static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("Failed to close ResultSet: " + e.getMessage());
            }
        }
    }

    // Closes the PreparedStatement
    static void closeStatement(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Failed to close Statement: " + e.getMessage());
            }
        }
    }
    /**
     * Compte
     */
/**
 * 
 * @param email
 * @param pswd
 * @return
 */
    // Retrieve a Compte by email and password
    public static Compte getCompteByNameAndPswd(String email, String pswd) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Compte compte = null;
        String sql = "SELECT nom, prenom, adresse, tel, email, pswd FROM compte WHERE email = ? AND pswd = ?";
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, pswd);
            rs = stmt.executeQuery();
            if (rs.next()) {
                compte = new Compte();
                compte.setNom(rs.getString("nom"));
                compte.setPrenom(rs.getString("prenom"));
                compte.setAdresse(rs.getString("adresse"));
                compte.setTel(rs.getString("tel"));
                compte.setEmail(rs.getString("email"));
                compte.setPswd(rs.getString("pswd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }
        return compte;
    }
    /**
     * 
     * @param compte
     * @return
     */
 // Inserts a new Compte into the database
    public static boolean addCompte(Compte compte) {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO compte (nom, prenom, adresse, tel, email, pswd) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, compte.getNom());
            stmt.setString(2, compte.getPrenom());
            stmt.setString(3, compte.getAdresse());
            stmt.setString(4, compte.getTel());
            stmt.setString(5, compte.getEmail());
            stmt.setString(6, compte.getPswd());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }

    /**
     * Adulte
     */
    /**
     * 
     * @return
     */
    //Retrieve all adults
    public static ArrayList<Adulte> getAdultes() {
        ArrayList<Adulte> adultes = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM adulte";
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                LocalDateTime dateNaissance = rs.getTimestamp("date_naissance").toLocalDateTime();
                String lieuNaissance = rs.getString("lieu_naissance");
                int age = rs.getInt("age");
                String diplome = rs.getString("diplome");
                String profession = rs.getString("profession");
                String tel = rs.getString("tel");

                Adulte adulte = new Adulte(nom, prenom, age, dateNaissance, lieuNaissance, diplome, profession, tel);
                adultes.add(adulte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }

        return adultes;
    }
    /**
     * 
     * @param nom
     * @param prenom
     * @param dateDeNaissance
     * @param lieuDeNaissance
     * @param age
     * @param diplome
     * @param profession
     * @param tel
     * @return
     */
    //Retrieve an adult according to criteria 
    public static Adulte getAdulteByCriteria(String nom, String prenom, LocalDateTime dateDeNaissance, String lieuDeNaissance, int age, String diplome, String profession, String tel) {
        Adulte adulte = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM adulte WHERE nom = ? AND prenom = ? AND date_naissance = ? AND lieu_naissance = ? AND age = ? AND diplome = ? AND profession = ? AND tel = ?";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setTimestamp(3, Timestamp.valueOf(dateDeNaissance));
            stmt.setString(4, lieuDeNaissance);
            stmt.setInt(5, age);
            stmt.setString(6, diplome);
            stmt.setString(7, profession);
            stmt.setString(8, tel);

            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String fetchedNom = rs.getString("nom");
                String fetchedPrenom = rs.getString("prenom");
                LocalDateTime fetchedDateNaissance = rs.getTimestamp("date_naissance").toLocalDateTime();
                String fetchedLieuNaissance = rs.getString("lieu_naissance");
                int fetchedAge = rs.getInt("age");
                String fetchedDiplome = rs.getString("diplome");
                String fetchedProfession = rs.getString("profession");
                String fetchedTel = rs.getString("tel");

                adulte = new Adulte(fetchedNom, fetchedPrenom, fetchedAge, fetchedDateNaissance, fetchedLieuNaissance, fetchedDiplome, fetchedProfession, fetchedTel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }

        return adulte;
    }
    /**
     * 
     * @param nom
     * @param prenom
     * @param dateDeNaissance
     * @param lieuDeNaissance
     * @param age
     * @param diplome
     * @param profession
     * @param tel
     * @param newValue
     * @return
     */
 //Update adulte according to a criteria 
    public static boolean updateAdulte(String nom, String prenom, LocalDateTime dateDeNaissance, String lieuDeNaissance, int age, String diplome, String profession, String tel, Adulte newValue) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE adulte SET nom = ?, prenom = ?, date_naissance = ?, lieu_naissance = ?, age = ?, diplome = ?, profession = ?, tel = ? WHERE nom = ? AND prenom = ? AND date_naissance = ? AND lieu_naissance = ? AND age = ? AND diplome = ? AND profession = ? AND tel = ?";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set new values
            stmt.setString(1, newValue.getNom());
            stmt.setString(2, newValue.getPrenom());
            stmt.setTimestamp(3, Timestamp.valueOf(newValue.getDateDeNaissance()));
            stmt.setString(4, newValue.getLieuDeNaissance());
            stmt.setInt(5, newValue.getAge());
            stmt.setString(6, newValue.getDiplome());
            stmt.setString(7, newValue.getProfession());
            stmt.setString(8, newValue.getTel());
            
            // Set criteria
            stmt.setString(9, nom);
            stmt.setString(10, prenom);
            stmt.setTimestamp(11, Timestamp.valueOf(dateDeNaissance));
            stmt.setString(12, lieuDeNaissance);
            stmt.setInt(13, age);
            stmt.setString(14, diplome);
            stmt.setString(15, profession);
            stmt.setString(16, tel);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
    /**
     * 
     * @param nom
     * @param prenom
     * @param dateDeNaissance
     * @param lieuDeNaissance
     * @param age
     * @param diplome
     * @param profession
     * @param tel
     * @return
     */
  //Remove adult according to a criteria 
    public static boolean deleteAdulte(String nom, String prenom, LocalDateTime dateDeNaissance, String lieuDeNaissance, int age, String diplome, String profession, String tel) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM adulte WHERE nom = ? AND prenom = ? AND date_naissance = ? AND lieu_naissance = ? AND age = ? AND diplome = ? AND profession = ? AND tel = ?";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set criteria
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setTimestamp(3, Timestamp.valueOf(dateDeNaissance));
            stmt.setString(4, lieuDeNaissance);
            stmt.setInt(5, age);
            stmt.setString(6, diplome);
            stmt.setString(7, profession);
            stmt.setString(8, tel);
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
    /**
     * 
     * @param adulte
     * @return
     */
  //Insert new adulte
    public static boolean insertAdulte(Adulte adulte) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO adulte (nom, prenom, date_naissance, lieu_naissance, age, diplome, profession, tel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set values for the new adulte
            stmt.setString(1, adulte.getNom());
            stmt.setString(2, adulte.getPrenom());
            stmt.setTimestamp(3, Timestamp.valueOf(adulte.getDateDeNaissance()));
            stmt.setString(4, adulte.getLieuDeNaissance());
            stmt.setInt(5, adulte.getAge());
            stmt.setString(6, adulte.getDiplome());
            stmt.setString(7, adulte.getProfession());
            stmt.setString(8, adulte.getTel());
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
/**
 * Enfant
 */
    /**
     * 
     * @return
     */
    //Retrieve All Enfants
    public static ArrayList<Enfant> getEnfants() {
        ArrayList<Enfant> enfants = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM enfant";
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                LocalDateTime dateNaissance = rs.getTimestamp("date_naissance").toLocalDateTime();
                String lieuNaissance = rs.getString("lieu_naissance");
                int age = rs.getInt("age");
                String classeEtude = rs.getString("classe_etude");
                String telParent1 = rs.getString("tel_parent_1");
                String telParent2 = rs.getString("tel_parent_2");

                ArrayList<String> telParents = new ArrayList<>();
                telParents.add(telParent1);
                telParents.add(telParent2);

                Enfant enfant = new Enfant(nom, prenom, age, dateNaissance, lieuNaissance, classeEtude, telParents);
                enfants.add(enfant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }

        return enfants;
    }
    /**
     * 
     * @param nom
     * @param prenom
     * @param dateDeNaissance
     * @param lieuDeNaissance
     * @param age
     * @param classeEtude
     * @param telParent1
     * @param telParent2
     * @return
     */
   //Retrieve an Enfant by Criteria
    public static Enfant getEnfantByCriteria(String nom, String prenom, LocalDateTime dateDeNaissance, String lieuDeNaissance, int age, String classeEtude, String telParent1, String telParent2) {
        Enfant enfant = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM enfant WHERE nom = ? AND prenom = ? AND date_naissance = ? AND lieu_naissance = ? AND age = ? AND classe_etude = ? AND tel_parent_1 = ? AND tel_parent_2 = ?";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setTimestamp(3, Timestamp.valueOf(dateDeNaissance));
            stmt.setString(4, lieuDeNaissance);
            stmt.setInt(5, age);
            stmt.setString(6, classeEtude);
            stmt.setString(7, telParent1);
            stmt.setString(8, telParent2);

            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String fetchedNom = rs.getString("nom");
                String fetchedPrenom = rs.getString("prenom");
                LocalDateTime fetchedDateNaissance = rs.getTimestamp("date_naissance").toLocalDateTime();
                String fetchedLieuNaissance = rs.getString("lieu_naissance");
                int fetchedAge = rs.getInt("age");
                String fetchedClasseEtude = rs.getString("classe_etude");
                String fetchedTelParent1 = rs.getString("tel_parent_1");
                String fetchedTelParent2 = rs.getString("tel_parent_2");

                ArrayList<String> telParents = new ArrayList<>();
                telParents.add(fetchedTelParent1);
                telParents.add(fetchedTelParent2);

                enfant = new Enfant(fetchedNom, fetchedPrenom, fetchedAge, fetchedDateNaissance, fetchedLieuNaissance, fetchedClasseEtude, telParents);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }

        return enfant;
    }
    /**
     * 
     * @param nom
     * @param prenom
     * @param dateDeNaissance
     * @param lieuDeNaissance
     * @param age
     * @param classeEtude
     * @param telParent1
     * @param telParent2
     * @param newValue
     * @return
     */
 //Update Enfant by Criteria
    public static boolean updateEnfant(String nom, String prenom, LocalDateTime dateDeNaissance, String lieuDeNaissance, int age, String classeEtude, String telParent1, String telParent2, Enfant newValue) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE enfant SET nom = ?, prenom = ?, date_naissance = ?, lieu_naissance = ?, age = ?, classe_etude = ?, tel_parent_1 = ?, tel_parent_2 = ? WHERE nom = ? AND prenom = ? AND date_naissance = ? AND lieu_naissance = ? AND age = ? AND classe_etude = ? AND tel_parent_1 = ? AND tel_parent_2 = ?";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set new values
            stmt.setString(1, newValue.getNom());
            stmt.setString(2, newValue.getPrenom());
            stmt.setTimestamp(3, Timestamp.valueOf(newValue.getDateDeNaissance()));
            stmt.setString(4, newValue.getLieuDeNaissance());
            stmt.setInt(5, newValue.getAge());
            stmt.setString(6, newValue.getClasseDeEtude());
            stmt.setString(7, newValue.getTelParent().get(0));
            stmt.setString(8, newValue.getTelParent().get(1));
            
            // Set criteria
            stmt.setString(9, nom);
            stmt.setString(10, prenom);
            stmt.setTimestamp(11, Timestamp.valueOf(dateDeNaissance));
            stmt.setString(12, lieuDeNaissance);
            stmt.setInt(13, age);
            stmt.setString(14, classeEtude);
            stmt.setString(15, telParent1);
            stmt.setString(16, telParent2);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
    /**
     * 
     * @param nom
     * @param prenom
     * @param dateDeNaissance
     * @param lieuDeNaissance
     * @param age
     * @param classeEtude
     * @param telParent1
     * @param telParent2
     * @return
     */
 //Delete Enfant by Criteria
    public static boolean deleteEnfant(String nom, String prenom, LocalDateTime dateDeNaissance, String lieuDeNaissance, int age, String classeEtude, String telParent1, String telParent2) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM enfant WHERE nom = ? AND prenom = ? AND date_naissance = ? AND lieu_naissance = ? AND age = ? AND classe_etude = ? AND tel_parent_1 = ? AND tel_parent_2 = ?";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set criteria
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setTimestamp(3, Timestamp.valueOf(dateDeNaissance));
            stmt.setString(4, lieuDeNaissance);
            stmt.setInt(5, age);
            stmt.setString(6, classeEtude);
            stmt.setString(7, telParent1);
            stmt.setString(8, telParent2);
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
    /**
     * 
     * @param enfant
     * @return
     */
  //Insert New Enfant
    public static boolean insertEnfant(Enfant enfant) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO enfant (nom, prenom, date_naissance, lieu_naissance, age, classe_etude, tel_parent_1, tel_parent_2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set values for the new enfant
            stmt.setString(1, enfant.getNom());
            stmt.setString(2, enfant.getPrenom());
            stmt.setTimestamp(3, Timestamp.valueOf(enfant.getDateDeNaissance()));
            stmt.setString(4, enfant.getLieuDeNaissance());
            stmt.setInt(5, enfant.getAge());
            stmt.setString(6, enfant.getClasseDeEtude());
            stmt.setString(7, enfant.getTelParent().get(0));
            stmt.setString(8, enfant.getTelParent().get(1));
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
    
    /**
     * Consultation adulte
     */
    /**
     * 
     * @return
     */
    //Get all consultation adults
    public static ArrayList<ConsultationAdulte> getConsultationsAdultes() {
        ArrayList<ConsultationAdulte> consultations = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT a.nom, a.prenom, a.date_naissance, a.lieu_naissance, a.age, a.diplome, a.profession, a.tel, " +
                     "c.date_rv, c.duree, c.resume_rv " +
                     "FROM rendez_vous_consultation_adulte c " +
                     "JOIN rendez_vous_consultation_adulte_adulte ca ON ca.rendez_vous_consultation_adulte_id = c.id " +
                     "JOIN adulte a ON a.id = ca.adulte_id";
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                LocalDateTime dateNaissance = rs.getTimestamp("date_naissance").toLocalDateTime();
                String lieuNaissance = rs.getString("lieu_naissance");
                int age = rs.getInt("age");
                String diplome = rs.getString("diplome");
                String profession = rs.getString("profession");
                String tel = rs.getString("tel");

                Adulte adulte = new Adulte(nom, prenom, age, dateNaissance, lieuNaissance, diplome, profession, tel);

                LocalDateTime dateRv = rs.getTimestamp("date_rv").toLocalDateTime();
                double duree = rs.getDouble("duree");
                String resumeRv = rs.getString("resume_rv");

                ConsultationAdulte consultation = new ConsultationAdulte(adulte, dateRv, duree, resumeRv);
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }

        return consultations;
    }
    /**
     * 
     * @return
     */
    //Get all consultation enfants
    public static ArrayList<ConsultationEnfant> getConsultationsEnfants() {
        ArrayList<ConsultationEnfant> consultations = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT a.nom, a.prenom, a.date_naissance, a.lieu_naissance, a.age, a.classe_etude, a.tel_parent_1, a.tel_parent_2," +
                     "c.date_rv, c.duree, c.resume_rv " +
                     "FROM rendez_vous_consultation_enfant c " +
                     "JOIN rendez_vous_consultation_enfant_enfant ca ON ca.rendez_vous_consultation_enfant_id = c.id " +
                     "JOIN enfant a ON a.id = ca.enfant_id";
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                LocalDateTime dateNaissance = rs.getTimestamp("date_naissance").toLocalDateTime();
                String lieuNaissance = rs.getString("lieu_naissance");
                int age = rs.getInt("age");
                String classeEtude = rs.getString("classe_etude");
                String tel1 = rs.getString("tel_parent_1");
                String tel2 = rs.getString("tel_parent_2");

                @SuppressWarnings("serial")
				Enfant enfant = new Enfant(nom, prenom, age, dateNaissance, lieuNaissance, classeEtude, new ArrayList<String>(){{add(tel1);add(tel2);}});

                LocalDateTime dateRv = rs.getTimestamp("date_rv").toLocalDateTime();
                double duree = rs.getDouble("duree");
                String resumeRv = rs.getString("resume_rv");

                ConsultationEnfant consultation = new ConsultationEnfant(enfant, resumeRv, duree, dateRv);
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }

        return consultations;
    }
    /**
     * 
     * @param consultation
     */
    //Insert a consultation to an adult
    @SuppressWarnings("resource")
	public static void insertConsultationAdulte(ConsultationAdulte consultation){
    	Connection connection = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	String sqlInsertConsultation = "INSERT INTO rendez_vous_consultation_adulte (date_rv, duree, resume_rv) VALUES (?, ?, ?)";
    	String sqlGetConsultationId = "SELECT LAST_INSERT_ID() AS consultationId";
    	String sqlCheckAdult = "SELECT id FROM adulte WHERE nom = ? AND prenom = ?";
    	String sqlInsertLink = "INSERT INTO rendez_vous_consultation_adulte_adulte (rendez_vous_consultation_adulte_id, adulte_id) VALUES (?, ?)";

    	try {
    	    connection = getConnection();
    	    
    	    // Insert consultation
    	    stmt = connection.prepareStatement(sqlInsertConsultation);
    	    stmt.setTimestamp(1, Timestamp.valueOf(consultation.getDate()));
    	    stmt.setDouble(2, consultation.getDuree());
    	    stmt.setString(3, consultation.getResume());
    	    stmt.executeUpdate();
    	    
    	    // Get consultation ID
    	    stmt = connection.prepareStatement(sqlGetConsultationId);
    	    rs = stmt.executeQuery();
    	    int consultationId = 0;
    	    if (rs.next()) {
    	        consultationId = rs.getInt("consultationId");
    	    }
    	    
    	    // Check if the adult exists
    	    stmt = connection.prepareStatement(sqlCheckAdult);
    	    stmt.setString(1, consultation.getAdulte().getNom());
    	    stmt.setString(2, consultation.getAdulte().getPrenom());
    	    rs = stmt.executeQuery();
    	    int adulteId = 0;
    	    if (rs.next()) {
    	        adulteId = rs.getInt("id");
    	    } else {
    	        System.out.println("Adulte does not exist.");
    	        return;
    	    }
    	    
    	    // Insert link between consultation and adult
    	    stmt = connection.prepareStatement(sqlInsertLink);
    	    stmt.setInt(1, consultationId);
    	    stmt.setInt(2, adulteId);
    	    stmt.executeUpdate();
  
    	    System.out.println("Consultation inserted successfully.");
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    if (connection != null) {
    	        try {
    	            connection.rollback();
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }
    	    }
    	} finally {
    	    closeResultSet(rs);
    	    closeStatement(stmt);
    	    closeConnection(connection);
    	}
    }
    /**
     * 
     * @param consultation
     */
  //Insert a consultation to an enfant
    @SuppressWarnings("resource")
	public static void insertConsultationEnfant(ConsultationEnfant consultation){
    	Connection connection = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	String sqlInsertConsultation = "INSERT INTO rendez_vous_consultation_enfant (date_rv, duree, resume_rv) VALUES (?, ?, ?)";
    	String sqlGetConsultationId = "SELECT LAST_INSERT_ID() AS consultationId";
    	String sqlCheckAdult = "SELECT id FROM enfant WHERE nom = ? AND prenom = ?";
    	String sqlInsertLink = "INSERT INTO rendez_vous_consultation_enfant_enfant (rendez_vous_consultation_enfant_id, enfant_id) VALUES (?, ?)";

    	try {
    	    connection = getConnection();
    	    
    	    // Insert consultation
    	    stmt = connection.prepareStatement(sqlInsertConsultation);
    	    stmt.setTimestamp(1, Timestamp.valueOf(consultation.getDate()));
    	    stmt.setDouble(2, consultation.getDuree());
    	    stmt.setString(3, consultation.getResume());
    	    stmt.executeUpdate();
    	    
    	    // Get consultation ID
    	    stmt = connection.prepareStatement(sqlGetConsultationId);
    	    rs = stmt.executeQuery();
    	    int consultationId = 0;
    	    if (rs.next()) {
    	        consultationId = rs.getInt("consultationId");
    	    }
    	    
    	    // Check if the adult exists
    	    stmt = connection.prepareStatement(sqlCheckAdult);
    	    stmt.setString(1, consultation.getEnfant().getNom());
    	    stmt.setString(2, consultation.getEnfant().getPrenom());
    	    rs = stmt.executeQuery();
    	    int adulteId = 0;
    	    if (rs.next()) {
    	        adulteId = rs.getInt("id");
    	    } else {
    	        System.out.println("Enfant does not exist.");
    	        return;
    	    }
    	    
    	    // Insert link between consultation and adult
    	    stmt = connection.prepareStatement(sqlInsertLink);
    	    stmt.setInt(1, consultationId);
    	    stmt.setInt(2, adulteId);
    	    stmt.executeUpdate();
  
    	    System.out.println("Consultation inserted successfully.");
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    if (connection != null) {
    	        try {
    	            connection.rollback();
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }
    	    }
    	} finally {
    	    closeResultSet(rs);
    	    closeStatement(stmt);
    	    closeConnection(connection);
    	}
    }
    /**
     * 
     * @param enfant
     * @return
     */
    //update enfant
    public static boolean updateEnfant(Enfant enfant) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE enfant SET nom = ?, prenom = ?, date_naissance = ?, lieu_naissance = ?, age = ?, classe_etude = ?, tel_parent_1 = ?, tel_parent_2 = ? WHERE id = ?";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set values for the updated enfant
            stmt.setString(1, enfant.getNom());
            stmt.setString(2, enfant.getPrenom());
            stmt.setTimestamp(3, Timestamp.valueOf(enfant.getDateDeNaissance()));
            stmt.setString(4, enfant.getLieuDeNaissance());
            stmt.setInt(5, enfant.getAge());
            stmt.setString(6, enfant.getClasseDeEtude());
            stmt.setString(7, enfant.getTelParent().get(0));
            stmt.setString(8, enfant.getTelParent().get(1));
            
            
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
    /**
     * 
     * @param adulte
     * @return
     */
    //update adulte
    public static boolean updateAdulte(Adulte adulte) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE adulte SET nom = ?, prenom = ?, date_naissance = ?, lieu_naissance = ?, age = ?, diplome = ?, profession = ?, tel = ? WHERE id = ?";

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set values for the updated adulte
            stmt.setString(1, adulte.getNom());
            stmt.setString(2, adulte.getPrenom());
            stmt.setTimestamp(3, Timestamp.valueOf(adulte.getDateDeNaissance()));
            stmt.setString(4, adulte.getLieuDeNaissance());
            stmt.setInt(5, adulte.getAge());
            stmt.setString(6, adulte.getDiplome());
            stmt.setString(7, adulte.getProfession());
            stmt.setString(8, adulte.getTel());
         
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
    /**
     * 
     * @param enfant
     * @return
     */
    //delete enfant
    public static boolean deleteEnfant(Enfant enfant) {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        String sql = "DELETE FROM enfant WHERE nom = ? AND prenom = ?";
        
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set the name parameters of the enfant to be deleted
            stmt.setString(1, enfant.getNom());
            stmt.setString(2, enfant.getPrenom());
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }
    //delete adulte
    public static boolean deleteAdulte(Adulte adulte) {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        String sql = "DELETE FROM adulte WHERE nom = ? AND prenom = ?";
        
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(sql);
            
            // Set the name parameters of the adulte to be deleted
            stmt.setString(1, adulte.getNom());
            stmt.setString(2, adulte.getPrenom());
            
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
    }


}
