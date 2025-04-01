package Controller;

import Model.TableModel;
import Model.BalanceModel;
import Model.Transaction;
import View.IncomeView;
import View.BalanceView;
import dao.TransactionDAO;
import View.ExpanseView;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Verarbeitet die Eingaben für Einnahmen und deren Speichern in der Datenbank.

public class IncomeController {

    private TableModel tableModel;
    private IncomeView incomeView;
    private ExpanseView expanseView;
    private BalanceModel balanceModel;
    private BalanceView balanceView;
    private BalanceController balanceController;
    private TableController tableController;
    private Transaction transaction;
    private TransactionDAO transactionDAO;


    public IncomeController(IncomeView incomeView, ExpanseView expanseView, BalanceController balanceController, BalanceModel balanceModel,
                            BalanceView balanceView, TableController tableController) {
        this.incomeView = incomeView;
        this.expanseView = expanseView;
        this.balanceController = balanceController;
        this.balanceModel = balanceModel;
        this.balanceView = balanceView;
        this.tableController = tableController;

        incomeView.getSubmitIncomeButton(e -> getAllIncomeInputs());
    }

    private void getAllIncomeInputs() {
        // Alle Eingaben aus dem IncomeView abrufen
        double incomeInput = incomeView.getIncomeInputField();
        System.out.println("Eingegebene Summe: " + incomeInput);

        String sourceInput = incomeView.getIncomeSourceInputField().getText();
        System.out.println("Eingegebene Quelle: " + sourceInput);

        String dateInput = incomeView.getIncomeDateInputField().getText();
        System.out.println("Eingegebenes Datum: " + dateInput);

        if (incomeInput > 0 && !sourceInput.trim().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = null;
            try {
                date = LocalDate.parse(dateInput, formatter);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(incomeView, "Ungültiges Datum! Bitte im Format TT.MM.JJJJ eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Balance aktualisieren
            balanceController.processIncomeTransaction(incomeInput);
            // Neue Transaktion in die DB einfügen
            Transaction transaction = new Transaction(0, "", sourceInput, incomeInput, date, "Income", "");
            TransactionDAO.insertTransaction(transaction);

            // Tabelle mit der neuen Transaktion aktualisieren
            tableController.updateTable();

            // Erfolgsmeldung und Felder leeren
            JOptionPane.showMessageDialog(null, "Einnahme erfolgreich hinzugefügt!");
            incomeView.clearIncomeFields();

        } else {
            JOptionPane.showMessageDialog(incomeView, "Bitte gültige Werte eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
}
