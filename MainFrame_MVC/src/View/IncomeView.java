package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class IncomeView extends JPanel { // Panel für Cardlayout

    //
    private JLabel incomeTitleLabel, sourceTitleLabel, incomeLabel, dateTitleLabel;
    private JTextField incomeSourceInputField, incomeInputField, incomeDateInputField;
    private JButton submitIncomeButton;
    private MainFrameView mainFrameView;

    public IncomeView(MainFrameView mainFrameView) {

        this.mainFrameView = mainFrameView;
        setLayout(new GridBagLayout()); // zuvor Border
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        incomeTitleLabel = new JLabel("Income");
        incomeTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(incomeTitleLabel, gbc);
        gbc.gridwidth = 2; // Zurücksetzen

        // Labels und Textfelder
        sourceTitleLabel = new JLabel("Source:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(sourceTitleLabel, gbc);

        incomeSourceInputField = new JTextField(10);
        gbc.gridx = 1;
        add(incomeSourceInputField, gbc);

        incomeLabel = new JLabel("Sum:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(incomeLabel, gbc);

        incomeInputField = new JTextField(10);
        gbc.gridx = 1;
        add(incomeInputField, gbc);

        dateTitleLabel = new JLabel("Date:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(dateTitleLabel, gbc);

        incomeDateInputField = new JTextField(10);
        gbc.gridx = 1;
        add(incomeDateInputField, gbc);

        // Submit-Button linksbündig
        submitIncomeButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(submitIncomeButton, gbc);

        setVisible(true);

    }

    // ActionListener für AddCashController
    public void getSubmitIncomeButton(ActionListener listener) {
        System.out.println("DEBUG: ActionListener wird hinzugefügt");
        submitIncomeButton.addActionListener(listener);
    }

    // getter für Nutzereingabe Sum -> addCash im Controller

    public double getIncomeInputField() {
        double incomeInput = 0.0;
        try {
            incomeInput = Double.parseDouble(incomeInputField.getText());  // Korrekt auf das Textfeld zugreifen
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bitte eine gültige Zahl eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        return incomeInput;
    }
        /*
        try {
            return Double.parseDouble(incomeInputField.getText()); // -> returns the text contained in the TextField -> zu Double
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe für die Summe!");
            return 0.00;

         */


    //public JButton getsubmitCashButton() {
    //return submitIncomeButton;
    // }
    public void clearIncomeFields() {
        incomeInputField.setText("");
        incomeSourceInputField.setText("");
        incomeDateInputField.setText("");
    }

    public JTextField getIncomeSourceInputField() {
        return incomeSourceInputField;
    }

    public JTextField getIncomeDateInputField() {
        return incomeDateInputField;
    }
}

