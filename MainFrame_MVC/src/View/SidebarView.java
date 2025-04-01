package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SidebarView extends JPanel {

    JPanel sidebar, sidePlatzhalter1, sidePlatzhalter2;
    JButton dashboardButton, addCashButton, settingsButton, printButton;

    // Konstruktor
    SidebarView() {

        sidebar = new JPanel(new GridBagLayout());
        sidebar.setPreferredSize(new Dimension(150, 600));
        sidebar.setBorder(BorderFactory.createLineBorder(Color.black));
        sidebar.setBackground(new Color(51, 178, 231));
        GridBagConstraints gbcS = new GridBagConstraints();
        gbcS.gridx = 0;
        gbcS.fill = GridBagConstraints.HORIZONTAL;
        gbcS.insets = new Insets(5, 5, 5, 5);

        // Platzhalter
        gbcS.gridy = 0;
        gbcS.weighty = 1.0;
        sidePlatzhalter1 = new JPanel();
        sidePlatzhalter1.setBackground(new Color(51, 178, 231));
        sidebar.add(sidePlatzhalter1, gbcS);

        dashboardButton = new JButton("Dashboard"); // -> controller
        gbcS.gridy = 1;
        gbcS.weighty = 0;  // Wert zurücksetzen
        sidebar.add(dashboardButton, gbcS);

        addCashButton = new JButton("Add Cash"); // --> controller
        gbcS.gridy = 2;
        sidebar.add(addCashButton, gbcS);

        settingsButton = new JButton("Settings"); // --> controller
        gbcS.gridy = 3;
        sidebar.add(settingsButton, gbcS);

        // Platzhalter
        gbcS.gridy = 4;
        gbcS.weighty = 1.0;
        sidePlatzhalter2 = new JPanel();
        sidePlatzhalter2.setBackground(new Color(51, 178, 231));
        sidebar.add(sidePlatzhalter2, gbcS);

        printButton = new JButton("Print Sheet"); // --> controller
        gbcS.gridy = 5;
        gbcS.weighty = 0; // Wert zurücksetzen
        gbcS.anchor = GridBagConstraints.SOUTH;
        sidebar.add(printButton, gbcS);
        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.CENTER);
    }

    // ActionListener für AddCashController
    public void addActionListenerToPrintButton(ActionListener listener) {
        printButton.addActionListener(listener);
    }


    // getter
    public JButton getAddCashButton() {
        return addCashButton;
    }

    public JButton getDashboardButton() {
        return dashboardButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }


}
