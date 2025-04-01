package Model;

public class BalanceModel {

    private Double totalBalance;
    private Double lastExpanseCat1, lastExpanseCat2, lastExpanseCat3, lastExpanseCat4; // Bestand


    public BalanceModel() {
        totalBalance = 0.00; // defualt bestand -> amount
        lastExpanseCat1 = 0.00;
        lastExpanseCat2 = 0.00;
        lastExpanseCat3 = 0.00;
        lastExpanseCat4 = 0.00;
    }

    // Balance wird aktualisiert -> addCash im balanceController
    public void updateTotalBalance(double updateTotalBalance) {
        totalBalance += updateTotalBalance;
    }
    // getter for Balance
    public double getTotalBalance() {
        return totalBalance;
    }

    // categoyry 1 --> default Bestand wird aktualisiert
    public void updateLastExpanseCat1(double updateLastExpanse) { // Menge
        lastExpanseCat1 += updateLastExpanse;
       totalBalance -= updateLastExpanse;
    }
    // getter cat 1
    public double getLastExpanse() {
        return lastExpanseCat1; // cat1 hat
    }

    // Category 2
    public void updateLastExpanseCat2(double updateLastExpanseCat2) {
        lastExpanseCat2 += updateLastExpanseCat2;
        totalBalance -= updateLastExpanseCat2;
    }
    // getter gat 2
    public double getLastExpanseCat2() {
        return lastExpanseCat2;
    }

    // Category 3
    public void updateLastExpanseCat3(double updateLastExpanseCat3) {
        lastExpanseCat3 += updateLastExpanseCat3;
        totalBalance -= updateLastExpanseCat3;
    }
    // getter cat 3
    public double getLastExpanseCat3() {
        return lastExpanseCat3;
    }

    // Category 4
    public void updateLastExpanseCat4(double updateLastExpanseCat4) {
        lastExpanseCat4 += updateLastExpanseCat4;
       totalBalance -= updateLastExpanseCat4;
    }
    // getter cat 4
    public double getLastExpanseCat4() {
        return lastExpanseCat4;
    }
}
