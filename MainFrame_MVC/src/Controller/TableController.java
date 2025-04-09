package Controller;

import View.SidebarView;
import View.TableView;
import Model.Transaction;
import dao.TransactionDAO;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TableController {
    private SidebarView sidebarView;
    private TableView tableView;


    public TableController(TableView tableView, SidebarView sidebarView) {
        this.tableView = tableView;
        this.sidebarView = sidebarView;
        updateTable();  // Synchronisiert Tabelle mit Datenbank

        //PrintButton Listener
        sidebarView.addActionListenerToPrintButton(e -> printAllTransactionsToFile());
        tableView.addActionListenerToDeleteButton(e -> deleteSelectedRows());
    }

    // Tabelle mit den neuesten Transaktionen aus der DB aktualisieren
    public void updateTable() {
        List<Transaction> transactions = TransactionDAO.getAllTransactions();
        tableView.updateTableModel(transactions);
    }

    // Transaktion löschen (aus Tabelle und DB)
    public void deleteRow(int rowIndex) {
        Transaction transaction = tableView.getTransactionAt(rowIndex);
        TransactionDAO.deleteTransaction(transaction.getId());  // Löschen aus der DB
        tableView.deleteRow(rowIndex);  // Zeile aus der Tabelle entfernen
    }

    // Neue Transaktion hinzufügen und Tabelle aktualisieren
    public void addTransaction(Transaction transaction) {
        TransactionDAO.insertTransaction(transaction);
        updateTable();  // Tabelle mit der neuen Transaktion aktualisieren
    }

    public void printAllTransactionsToFile() {
        List<Transaction> transactions = TransactionDAO.getAllTransactions(); // Alle Transaktionen abrufen
        String fileName = "transactions.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            // Kopfzeile der CSV-Datei schreiben
            writer.append("ID,Purpose,Source,Sum,Date,Type,Category\n");

            // Jede Transaktion in die CSV-Datei schreiben
            for (Transaction transaction : transactions) {
                writer.append(String.valueOf(transaction.getId())).append(",")
                        .append(transaction.getPurpose()).append(",")
                        .append(transaction.getSource()).append(",")
                        .append(String.valueOf(transaction.getSum())).append(",")
                        .append(transaction.getDate().toString()).append(",")
                        .append(transaction.getType()).append(",")
                        .append(transaction.getCategory()).append("\n");
            }

            // Erfolgsmeldung ausgeben
            JOptionPane.showMessageDialog(null, "Transaktionen wurden erfolgreich in " + fileName + " gespeichert.");
            System.out.println("Transaktionen erfolgreich in Datei gespeichert: " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Transaktionen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void deleteSelectedRows() {
        JTable table = tableView.getTable();
        int[] selectedRows = table.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(null, "Bitte mindestens eine Zeile auswählen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Sortiere die Zeilen in absteigender Reihenfolge, um sie sicher zu löschen
        Arrays.sort(selectedRows);

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int rowIndex = selectedRows[i];
            Transaction transaction = tableView.getTransactionAt(rowIndex);

            // Transaktion aus der Datenbank entfernen
            TransactionDAO.deleteTransaction(transaction.getId());

            // Zeile aus der Tabelle entfernen
            tableView.deleteRow(rowIndex);
        }

        JOptionPane.showMessageDialog(null, "Ausgewählte Transaktionen wurden gelöscht.");
    }

}
