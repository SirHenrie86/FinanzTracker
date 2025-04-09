package Controller;


import Model.Transaction;
import Model.BalanceModel;
import View.ExpanseView;
import dao.TransactionDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

// Verarbeitet Eingaben aus der ExpanseView, wie submitExpenseButton.

public class ExpanseController {

    private ExpanseView expanseView;
    private BalanceModel balanceModel;
    private BalanceController balanceController;

    //private BalanceController balanceController;
    private TableController tableController;
    private TransactionDAO transactionDAO;

    public ExpanseController(ExpanseView expanseView, BalanceController balanceController, TableController tableController) {
        this.expanseView = expanseView;
        this.balanceController = balanceController;
        this.tableController = tableController;


        expanseView.addActionListenerToSubmitButton(e -> getAllExpanseInputs());

        // Kategorien beim Start in die ComboBox laden
        // loadCategories();
    }

    //  Methode zum Laden der Kategorien aus der Datenbank
    private void loadCategories() {
        List<String> categories = transactionDAO.getCategories();
        System.out.println("Kategorien aus der DB: " + categories);  // Überprüfen, ob Kategorien korrekt geladen werden

        if (categories != null && !categories.isEmpty()) {
            expanseView.setCategoryOptions(categories);  // Setze die Kategorien in der View
        } else {
            System.out.println("Keine Kategorien zum Anzeigen vorhanden.");
        }
    }


    private void getAllExpanseInputs() {
        double expanseInput = expanseView.getExpanseInputField();
        String categoryInput = expanseView.getSelectedCategory();
        String purposeInput = expanseView.getExpansePurposeInputField().getText();
        String dateInput = expanseView.getExpanseDateField().getText();

        // Eingabewerte überprüfen und validieren
        if (expanseInput > 0 && categoryInput != null && !categoryInput.trim().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = null;
            try {
                //Datum Validieren
                date = LocalDate.parse(dateInput, formatter);
                System.out.println("Eingegebenes Datum: " + dateInput);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(expanseView, "Ungültiges Datum! Bitte im Format TT.MM.JJJJ eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            balanceController.processExpanseTransaction(expanseInput, categoryInput);


            Transaction transaction = new Transaction(0, purposeInput, "", expanseInput, date, "Expense", categoryInput);

            transactionDAO.insertTransaction(transaction);
            System.out.println("Eingegebener Purpose: " + purposeInput + "\nHöhe der Ausgabe: " + expanseInput + "\nTyp: Expense");


            // Tabelle mit der neuen Transaktion aktualisieren
            tableController.updateTable();
            System.out.println("Tabelle wurde aktualisiert");

            JOptionPane.showMessageDialog(null, "Einnahme erfolgreich hinzugefügt!");

            expanseView.clearFields();
        } else {
            JOptionPane.showMessageDialog(expanseView, "Bitte gültige Werte eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }


}
