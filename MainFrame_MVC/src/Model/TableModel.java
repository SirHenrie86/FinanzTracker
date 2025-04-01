package Model;

import javax.swing.table.DefaultTableModel;
import java.util.List;

// Diese Klasse verwaltet die Tabellenlogik mit einem DefaultTableModel.

public class TableModel {

    private DefaultTableModel defaultTableModel;

    // Konstruktor
    public TableModel() { //
        defaultTableModel = new DefaultTableModel();
        // Initialisierung der Tabellenspalten
        String[] columNames = {"Id", "Purpose", "Source", "Sum", "Date", "Type", "Category"};
        defaultTableModel = new DefaultTableModel(columNames, 0); // Start mit leerem Modell
    }

    // getter für TableModel
    public DefaultTableModel getTableModel() {
        return defaultTableModel;
    }

    // Zeile zur Tabelle hinzufügen
    public void addRow(Transaction transaction) {
        defaultTableModel.addRow(new Object[]{ // neue Zeile mit Daten zur Tabelle hinzufügen
                transaction.getId(),
                transaction.getPurpose(),
                transaction.getSource(),
                transaction.getSum(),
                transaction.getDate(),
                transaction.getType(),
                transaction.getCategory()
        });
    }

    // Tabelle leeren
    public void clearTable() {
        defaultTableModel.setRowCount(0);  // Setzt die Zeilenanzahl auf 0 und löscht damit alle Einträge
    }

    // Methode zum Aktualisieren der Tabelle (dies wird im Controller verwendet)
    public void updateTable(List<Transaction> transactions) {
        clearTable();  // Löscht vorherige Zeilen
        for (Transaction transaction : transactions) {
            addRow(transaction); // Fügt jede Transaktion zur Tabelle hinzu
        }
    }

    // Methode zum Löschen einer Zeile anhand der ID (index)
    public void deleteRow(int rowIndex) {
        defaultTableModel.removeRow(rowIndex);
    }

}





