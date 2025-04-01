package View;

import Controller.*;
import Model.BalanceModel;
import Model.TableModel;
import Model.Transaction;
import dao.TransactionDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

// Eine zentrale Ansichtsklasse, die vermutlich verschiedene Views wie SidebarView oder TableView koordiniert.

public class MainFrameView extends JFrame {

    private JPanel mainPanel; // CardLayout-Panel
    private SidebarView sidebarView;
    private BalanceView balanceView;
    private BalanceModel balanceModel;
    private TableModel tableModel;
    private TableView tableView;
    private ExpanseView expanseView;
    private IncomeView incomeView;
    private TableController tableController;
    private ExpanseController expanseController;
    private IncomeController incomeController;
    private BalanceController balanceController;
    private SidebarController sidebarController;

    private TransactionDAO transactionDAO;



    private CardLayout cardLayout;

    // Konstruktor
    public MainFrameView() {
        init(); // GUI-Komponenten initialisieren
        setTitle("Finanz Tracker");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);

        add(mainPanel, BorderLayout.CENTER);
        add(sidebarView, BorderLayout.WEST);

        setVisible(true);
    }

    // Initialisiert alle GUI-Komponenten
    private void init() {

        balanceModel = new BalanceModel();
        this.balanceView = new BalanceView();
        this.balanceController = new BalanceController(balanceView, balanceModel);
        sidebarView = new SidebarView();
        this.sidebarController = new SidebarController(this);
        expanseView = new ExpanseView();
        tableModel = new TableModel();
        tableView = new TableView(tableModel);
        incomeView = new IncomeView(this); // MainFrameView übergeben
        transactionDAO = new TransactionDAO();
        // **TableController korrekt initialisieren!**
        tableController = new TableController(tableView, sidebarView);
        // **ExpanseController initialisieren mit tableController!**
        this.expanseController = new ExpanseController(expanseView, balanceController, tableController);
        // IncomeController ebenfalls initialisieren
        incomeController = new IncomeController(incomeView, expanseView, balanceController, balanceModel, balanceView, tableController);

        cardLayout = new CardLayout(); // CardLayout erstellen
        mainPanel = new JPanel(cardLayout); // Panel mit CardLayout setzen
        // Panels mit CardLayout-Keys hinzufügen
        mainPanel.add(createDashboardPanel(), "Dashboard");
        mainPanel.add(incomeView, "AddCash");
    }

    // Erstellt ein Panel für das Dashboard
    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.add(balanceView, BorderLayout.NORTH);
        dashboardPanel.add(expanseView, BorderLayout.EAST);
        dashboardPanel.add(tableView, BorderLayout.CENTER);
        return dashboardPanel;
    }

    // Methode zum Aktualisieren der Tabelle aus dem Controller -> Tabelle ins tableModel
    public void updateMainTable(List<Transaction> transactions) {
        tableView.updateTableModel(transactions);
    }

    // Zeigt ein Panel basierend auf dem Namen (CardLayout)
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName); // CardLayout richtig wechseln
    }

    public SidebarView getSidebarView() {
        return sidebarView;
    }

    public ExpanseView getExpanseView() {
        return expanseView;
    }

    public BalanceView getBalanceView() {
        return balanceView;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public IncomeView getMyAddCashView() {
        return incomeView;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public TableView getTableView() {
        return tableView;
    }

    public IncomeView getIncomeView() {
        return incomeView;
    }
}
