package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Transaction;

// Verwendet das TransactionDAO, um Transaktionen mit der Datenbank zu synchronisieren.

public class TransactionDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/Finanztracker";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Baum123"; // ist geheim!

    public static List<Transaction> getAllTransactions() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) { // Autoclose in den Klammern
            System.out.printf("connection success %n");

            // SELECT
            String query = "SELECT * from transaction"; // gibt mir alle transactions zurück -> auslesen
            Statement statement = connection.createStatement(); // anfrage stellen -> auslesen
            ResultSet rs = statement.executeQuery(query); // ergebnisse gespeichert die die Datenbank zurück gibt in rs

            List<Transaction> transactions = new ArrayList<>();
            while (rs.next()) {
                //rsmeta.getColumnName(1);
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setPurpose(rs.getString("purpose"));
                transaction.setSource(rs.getString("source"));
                transaction.setSum(rs.getDouble("sum"));
                transaction.setDate(LocalDate.parse(rs.getString("date")));
                transaction.setType(rs.getString("type"));
                transaction.setCategory(rs.getString("category"));
                transactions.add(transaction);
            }
            rs.close();
            statement.close();
            return transactions;
        } catch (SQLException e) {
            //connection.rollback(); // anfrage rückgängig machen -> bei manipulation _>
            System.out.print("connection failed"); // Printf()
            throw new RuntimeException(e);
        }
    }

    // INSERT
    public static void insertTransaction(Transaction transaction) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            connection.setAutoCommit(false); // Auto-Commit deaktivieren

            // SQL-Insert-Abfrage vorbereiten + RETURNING id
            String query = "INSERT INTO transaction (purpose, source, sum, date, type, category) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, transaction.getPurpose());
                pstmt.setString(2, transaction.getSource());
                pstmt.setDouble(3, transaction.getSum());
                pstmt.setDate(4, Date.valueOf(transaction.getDate()));
                pstmt.setString(5, transaction.getType());
                pstmt.setString(6, transaction.getCategory());

                // Ausführen und ID zurückgeben
                try (ResultSet generatedKeys = pstmt.executeQuery()) { // executeQuery für RETURNING
                    if (generatedKeys.next()) {
                        transaction.setId(generatedKeys.getInt(1));
                    }
                }

                connection.commit(); // Erfolgreiches Commit
            } catch (SQLException e) {
                System.out.println("Fehler beim Einfügen des Datensatzes: " + e.getMessage());
                connection.rollback(); // Rollback bei Fehler
                throw e; // Exception weiterwerfen
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
    }

    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();

        System.out.println("Geladene Kategorien vor DB-Query: " + categories);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT name FROM category";
            try (PreparedStatement pstmt = connection.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Laden der Kategorien: " + e.getMessage());
        }

        System.out.println("Kategorien aus der DB: " + categories);
        return categories;
    }

    // UPDATE
    // Diese Methode aktualisiert eine bestehende Transaktion, die anhand ihrer id gefunden wird.
    // Alle Werte der Transaktion (außer der id) werden mit den neuen Werten aus dem transaction-Objekt überschrieben.

    public static void updateTransaction(Transaction transaction) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // SQL-Abfrage zum Aktualisieren der Transaktion
            String query = "UPDATE transaction SET purpose=?, source=?, sum=?, date=?, type=?, category=? WHERE id=?";

            // PreparedStatement erstellen
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {

                // Setze die Werte für die Platzhalter
                pstmt.setString(1, transaction.getPurpose());
                pstmt.setString(2, transaction.getSource());
                pstmt.setDouble(3, transaction.getSum());
                pstmt.setDate(4, Date.valueOf(transaction.getDate()));
                pstmt.setString(5, transaction.getType());
                pstmt.setString(6, transaction.getCategory());
                pstmt.setInt(7, transaction.getId()); // ID der Transaktion, die aktualisiert werden soll

                // Führe das Update aus
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Transaktion erfolgreich aktualisiert.");
                } else {
                    System.out.println("Keine Transaktion mit dieser ID gefunden.");
                }

            } catch (SQLException e) {
                System.out.println("Fehler beim Aktualisieren der Transaktion: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
    }
    // DELETE
    // Diese Methode löscht eine Transaktion, die anhand ihrer id gefunden wird.
    // Wenn eine Transaktion mit der angegebenen id existiert, wird sie gelöscht.
    // Ansonsten erhält der Benutzer eine Meldung, dass keine Transaktion mit dieser ID gefunden wurde.

    public static void deleteTransaction(int transactionId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // SQL-Abfrage zum Löschen der Transaktion
            String query = "DELETE FROM transaction WHERE id = ?";

            // PreparedStatement erstellen
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {

                // Setze die ID der zu löschenden Transaktion
                pstmt.setInt(1, transactionId);

                // Führe das Delete aus
                int rowsDeleted = pstmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Transaktion erfolgreich gelöscht.");
                } else {
                    System.out.println("Keine Transaktion mit dieser ID gefunden.");
                }

            } catch (SQLException e) {
                System.out.println("Fehler beim Löschen der Transaktion: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
    }
}
