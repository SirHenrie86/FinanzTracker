package Model;

import java.time.LocalDate;

// Speichert die Datenbankattribute für Transaktionen, wie purpose, sum, und LocalDate.

public class Transaction {
    private int id;
    private String purpose;    // Für Ausgaben: z. B. "Hose"
    private String source;     // Nur für Einnahmen: z. B. "Gehalt"
    private double sum;        // Betrag
    private LocalDate date;    // Datum der Transaktion
    private String type;       // "Einnahme" oder "Ausgabe"
    private String category; // Speichert den Namen der Kategorie

    public Transaction(int id, String purpose, String source, double sum, LocalDate date, String type, String category) {
        this.id = id;
        this.purpose = purpose;
        this.source = source;
        this.sum = sum;
        this.date = date;
        this.type = type;
        this.category = category;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}


