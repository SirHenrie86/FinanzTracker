package View;

import Model.TableModel;
import Model.Transaction;
import Controller.TransactionController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class TableView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private TransactionController transactionController;

    public TableView(TableModel tableModel) {
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        // Spaltennamen für die Tabelle
        String[] columnNames = {"ID", "Purpose", "Source", "Sum", "Date", "Type", "Category"};
        tableModel = new DefaultTableModel(columnNames, 0);  // Model für Tabelle
        table = new JTable(tableModel);  // JTable mit Model

        JScrollPane scrollPane = new JScrollPane(table);
        JPanel deleteButtonPanel = new JPanel();
        deleteButton = new JButton("Delete");
        deleteButtonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(deleteButtonPanel, BorderLayout.SOUTH);
    }

    // Getter für die Tabelle, falls ein externer Zugriff nötig ist
    public JTable getTable() {
        return table;
    }

    // ActionListener für AddCashController
    public void addActionListenerToDeleteButton(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
    public JButton getDeleteButton() {
        return deleteButton;
    }

    // Tabelle mit Transaktionsdaten aktualisieren
    public void updateTableModel(List<Transaction> transactions) {
        tableModel.setRowCount(0);  // Alle alten Zeilen löschen

        for (Transaction transaction : transactions) {
            tableModel.addRow(new Object[]{
                    transaction.getId(), transaction.getPurpose(), transaction.getSource(),
                    transaction.getSum(), transaction.getDate(), transaction.getType(), transaction.getCategory()
            });
        }
    }

    // Gibt die Transaktion basierend auf einer Tabellenzeile zurück
    public Transaction getTransactionAt(int rowIndex) {
        int id = (int) tableModel.getValueAt(rowIndex, 0);
        String purpose = (String) tableModel.getValueAt(rowIndex, 1);
        String source = (String) tableModel.getValueAt(rowIndex, 2);
        double sum = (double) tableModel.getValueAt(rowIndex, 3);
        LocalDate date = LocalDate.parse(tableModel.getValueAt(rowIndex, 4).toString());
        String type = (String) tableModel.getValueAt(rowIndex, 5);
        String category = (String) tableModel.getValueAt(rowIndex, 6);

        return new Transaction(id, purpose, source, sum, date, type, category);
    }

    public void deleteRow(int rowIndex) {
        tableModel.removeRow(rowIndex);
    }



}