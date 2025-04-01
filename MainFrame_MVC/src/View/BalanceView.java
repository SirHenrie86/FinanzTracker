package View;

import javax.swing.*;
import java.awt.*;

public class BalanceView extends JPanel {

    private JPanel balanceAreaPanel, balancePanel, category1Panel, category2Panel, category3Panel, category4Panel;
    private JLabel balanceLabel, totalBalanceLabel, lastExpanse1Label, lastExpanse2Label, lastExpanse3Label, lastExpanse4Label;

    // Konstruktor
    public BalanceView() {

        // Area
        balanceAreaPanel = new JPanel(new GridBagLayout());
        balanceAreaPanel.setPreferredSize(new Dimension(750, 200));

        GridBagConstraints gbcN = new GridBagConstraints();
        gbcN.fill = GridBagConstraints.BOTH;
        gbcN.insets = new Insets(45, 10, 45, 10);

        //  Balance Panel
        gbcN.gridx = 0;
        gbcN.gridy = 0;
        gbcN.gridwidth = 2;
        gbcN.gridheight = 2;
        gbcN.weightx = 2.0;
        gbcN.weighty = 1.0;

        balancePanel = new JPanel();
        balancePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        balancePanel.setLayout(new GridLayout(2, 1));
        balancePanel.setPreferredSize(new Dimension(180, 120));
        balancePanel.setBackground(new Color(123, 213, 123));

        balanceLabel = new JLabel("Balance", SwingConstants.CENTER); // balanceLabelText
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalBalanceLabel = new JLabel("Euro: 0.00", SwingConstants.CENTER);// balanceLabel
        totalBalanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        balancePanel.add(balanceLabel);
        balancePanel.add(totalBalanceLabel);

        balanceAreaPanel.add(balancePanel, gbcN);

        Dimension categorySize = new Dimension(90, 80); // one Size for all
        Font categoryFont = new Font("Arial", Font.BOLD, 18); // one Font for all

        // Größe Position Categories
        gbcN.gridwidth = 1;
        gbcN.gridheight = 1;
        gbcN.weightx = 0.5; // kleiner macht balance Panel größer
        gbcN.weighty = 1.0;

        // category 1 panel
        category1Panel = new JPanel(new GridLayout(2, 1));
        category1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        category1Panel.setPreferredSize(categorySize);
        category1Panel.setBackground(new Color(173, 216, 230));

        // ACHTUNG: wenn Betrag sich ändert, ändert sich Label
        lastExpanse1Label = new JLabel("Euro: 0.00 ", SwingConstants.CENTER);  // Model -> Controller
        lastExpanse1Label.setFont(categoryFont);
        category1Panel.add(new JLabel("Shopping", SwingConstants.CENTER));
        category1Panel.add(lastExpanse1Label);

        gbcN.gridx = 2;
        balanceAreaPanel.add(category1Panel, gbcN);

        // category 2
        category2Panel = new JPanel(new GridLayout(2, 1));
        category2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        category2Panel.setPreferredSize(categorySize);
        category2Panel.setBackground(new Color(210, 180, 140));

        lastExpanse2Label = new JLabel("Euro: 0.00", SwingConstants.CENTER); // Model -> Controller
        lastExpanse2Label.setFont(categoryFont);
        category2Panel.add(new JLabel("Food & Drinks", SwingConstants.CENTER));
        category2Panel.add(lastExpanse2Label);

        gbcN.gridx = 3;
        balanceAreaPanel.add(category2Panel, gbcN);

        // category 3
        category3Panel = new JPanel(new GridLayout(2, 1));
        category3Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        category3Panel.setPreferredSize(categorySize);
        category3Panel.setBackground(new Color(255, 255, 153));

        lastExpanse3Label = new JLabel("Euro: 0.00", SwingConstants.CENTER); // Model -> Controller
        lastExpanse3Label.setFont(categoryFont);
        category3Panel.add(new JLabel("Bills", SwingConstants.CENTER));
        category3Panel.add(lastExpanse3Label);

        gbcN.gridx = 4;
        balanceAreaPanel.add(category3Panel, gbcN);

        // Category 4
        category4Panel = new JPanel(new GridLayout(2, 1));
        category4Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        category4Panel.setPreferredSize(categorySize);
        category4Panel.setBackground(new Color(255, 250, 205));

        lastExpanse4Label = new JLabel("Euro: 0.00", SwingConstants.CENTER); // --> controller
        lastExpanse4Label.setFont(categoryFont);
        category4Panel.add(new JLabel("Others", SwingConstants.CENTER));
        category4Panel.add(lastExpanse4Label);

        gbcN.gridx = 5;
        balanceAreaPanel.add(category4Panel, gbcN);
        setLayout(new BorderLayout());
        add(balanceAreaPanel, BorderLayout.CENTER);
    }

    // setter BalanceSum -> je nch von sum okmmt
    public void totalBalanceLabelUpdate(double sum) {
        totalBalanceLabel.setText("Euro: " + String.format("%.2f", sum));  // String.format() evtl.
    }

    public void lastExpanseCategoriesUpdate(String category, double sum) {
        switch (category) {
            case "Shopping":
                lastExpanse1Label.setText(String.format("Euro: " + sum)); // String.format() evtl.
                break;
            case "Food & Drinks":
                lastExpanse2Label.setText(String.format("Euro: " + sum));
                break;
            case "Bills":
                lastExpanse3Label.setText(String.format("Euro: " + sum));
                break;
            case "Others":
                lastExpanse4Label.setText(String.format("Euro: " + sum));
                break;
        }
    }
}