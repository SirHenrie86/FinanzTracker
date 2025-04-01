package Controller;

import Model.Transaction;
import dao.TransactionDAO;
import java.util.List;

// Diese Klasse stellt die Verbindung zur Datenbank her und verarbeitet Transaktionen.

public class TransactionController {

    private TransactionDAO transactionDAO;
    private MainController mainController;

    // Konstruktor, der das DAO übergibt
    public TransactionController(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;  // Weist die übergebene Instanz zu
    }

    // Methode zum Laden aller Transaktionen
    public List<Transaction> getAllTransactions() {
        return TransactionDAO.getAllTransactions(); // DAO verwenden, um Transaktionen zu laden
    }

    // Methode, um eine Transaktion hinzuzufügen
    public void addTransaction(Transaction addTransaction) {
        transactionDAO.insertTransaction(addTransaction); // DAO verwenden, um Transaktionen zu speichern
    }

    // Methode zum Setzen des MainControllers
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // Methode zum Aktualisieren einer Transaktion
    public void updateTransaction(Transaction updateTransaction) {
        transactionDAO.updateTransaction(updateTransaction); // DAO verwenden, um eine Transaktion zu aktualisieren
    }

    // Methode zum Löschen einer Transaktion
    public void deleteTransaction(int transactionId) {
        transactionDAO.deleteTransaction(transactionId); // DAO verwenden, um eine Transaktion zu löschen
    }
}
