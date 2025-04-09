package Controller;

import Model.BalanceModel;
import View.BalanceView;

// Enthält die Verbindung zwischen Balance-Modell und Balance-Ansicht.

public class BalanceController {
    private BalanceModel balanceModel;
    private BalanceView balanceView;

    public BalanceController(BalanceView balanceView, BalanceModel balanceModel) {
        this.balanceModel = balanceModel;
        this.balanceView = balanceView;
    }


    public void processExpanseTransaction(double amount, String category) {
        switch (category) {
            case "Shopping":
                balanceModel.updateLastExpanseCat1(amount);
                balanceView.lastExpanseCategoriesUpdate(category, balanceModel.getLastExpanse());
                System.out.println("Eingegebene Kategotrie: " + category);
                break;
            case "Food & Drinks":
                balanceModel.updateLastExpanseCat2(amount);
                balanceView.lastExpanseCategoriesUpdate(category, balanceModel.getLastExpanseCat2());
                System.out.println("Eingegebene Kategotrie: " + category);
                break;
            case "Bills":
                balanceModel.updateLastExpanseCat3(amount);
                balanceView.lastExpanseCategoriesUpdate(category, balanceModel.getLastExpanseCat3());
                System.out.println("Eingegebene Kategotrie: " + category);
                break;
            case "Others":
                balanceModel.updateLastExpanseCat4(amount);
                balanceView.lastExpanseCategoriesUpdate(category, balanceModel.getLastExpanseCat4());
                System.out.println("Eingegebene Kategotrie: " + category);
                break;
        }
        // labelUpdate bekomme
        balanceView.totalBalanceLabelUpdate(balanceModel.getTotalBalance());
    }

    public void processIncomeTransaction(double amount) {
        balanceModel.updateTotalBalance(amount);
        // labelUpdate bekomme
        balanceView.totalBalanceLabelUpdate(balanceModel.getTotalBalance());
    }

}
