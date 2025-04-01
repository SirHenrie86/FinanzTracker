package View;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExpanseView extends JPanel {

    private JTextField expansePurposeInputField, expanseInputField, expanseDateField;
    private JComboBox<String> cbSelectedCategory;
    private JButton submitExpanseButton;

    // Konstruktor --- für MainPanel East--
    public ExpanseView() {

        JPanel expanseArea = new JPanel(new GridBagLayout());
        expanseArea.setBackground(new Color(211, 211, 211));
        JLabel expanseHeadline = new JLabel("Add Expense", SwingConstants.CENTER);
        expanseHeadline.setFont(new Font("Arial", Font.BOLD, 18));
        expanseArea.add(expanseHeadline);

        GridBagConstraints gbcE = new GridBagConstraints();
        gbcE.insets = new Insets(5, 5, 5, 5);
        gbcE.anchor = GridBagConstraints.CENTER;
        gbcE.fill = GridBagConstraints.HORIZONTAL;

        // Platzhalter
        gbcE.gridy = 1;
        gbcE.weighty = 1.0; // Platzhalter nimmt kompletten Platz ein
        JPanel expansePlaceholder = new JPanel();
        expansePlaceholder.setBackground(new Color(211, 211, 211));
        expanseArea.add(expansePlaceholder, gbcE);

        // Zweck der Ausgabe
        gbcE.gridy = 2;
        gbcE.gridx = 0;
        expanseArea.add(new JLabel("Purpose", SwingConstants.CENTER), gbcE);

        gbcE.gridx = 1;
        gbcE.weightx = 1.0;
        expansePurposeInputField = new JTextField(12);
        expanseArea.add(expansePurposeInputField, gbcE);
        gbcE.weightx = 0;

        // Höhe der Ausgabe
        gbcE.gridy = 3;
        gbcE.gridx = 0;
        expanseArea.add(new JLabel("Sum", SwingConstants.CENTER), gbcE);

        gbcE.gridx = 1;
        gbcE.weightx = 1.0;
        expanseInputField = new JTextField(12);
        expanseArea.add(expanseInputField, gbcE);
        gbcE.weightx = 0;

        // Datum der Ausgabe
        gbcE.gridy = 4;
        gbcE.gridx = 0;
        expanseArea.add(new JLabel("Date", SwingConstants.CENTER), gbcE);

        gbcE.gridx = 1;
        gbcE.weightx = 1.0;
        expanseDateField = new JTextField(12);
        expanseArea.add(expanseDateField, gbcE);
        gbcE.weightx = 0;

        // Kategorie der Ausgabe
        gbcE.gridy = 5;
        gbcE.gridx = 0;
        expanseArea.add(new JLabel("Category", SwingConstants.CENTER), gbcE);

        // Combobox für Kategorien
        gbcE.gridx = 1;
        cbSelectedCategory = new JComboBox<>(new String[]{" ", "Shopping", "Food & Drinks", "Bills", "Others"});
        gbcE.fill = GridBagConstraints.HORIZONTAL; // is used when the component's display area is larger than the component's requested size
        expanseArea.add(cbSelectedCategory, gbcE);
        gbcE.fill = GridBagConstraints.NONE;

        // Submit Button --> Actionslistener zu Expanse Controller
        gbcE.gridy = 6;
        gbcE.gridx = 0;
        gbcE.gridwidth = 2;
        gbcE.anchor = GridBagConstraints.CENTER;
        submitExpanseButton = new JButton("Submit");
        submitExpanseButton.setEnabled(true);
        expanseArea.add(submitExpanseButton, gbcE);

        // Layout des Panels für MainFrame
        setLayout(new BorderLayout());
        add(expanseArea, BorderLayout.CENTER);

    }

    // ActionListener -> ExpanseController
    public void addActionListenerToSubmitButton(ActionListener listener) {
        submitExpanseButton.addActionListener(listener);
    }

    // Methode zum Abrufen des Betrags
    public double getExpanseInputField() {
        try {
            return Double.parseDouble(expanseInputField.getText());
        } catch (NumberFormatException e) {
            return 0.00;
        }
    }

    // Methode um Categories in CombBox anzuzeigen
    public void setCategoryOptions(List<String> categories) {
        cbSelectedCategory.removeAllItems();
        for (String category : categories) {
            cbSelectedCategory.addItem(category);
        }
    }

    // Methode zum Abrufen der ausgewählten Kategorie
    public String getSelectedCategory() {
        return (String) cbSelectedCategory.getSelectedItem();
    }

    public JTextField getExpansePurposeInputField() {
        return expansePurposeInputField;
    }

    public JTextField getExpanseDateField() {
        return expanseDateField;
    }

    // Methode um Eingabefelder zu leeren
    public void clearFields() {
        expansePurposeInputField.setText("");
        expanseInputField.setText("");
        expanseDateField.setText("");
        cbSelectedCategory.setSelectedIndex(0);
    }
    // getter für Submitbutton
    public JButton getSubmitExpanseButton() {
        return submitExpanseButton;
    }

}

