public abstract class Payment {
    protected double amount;
    protected String transactionID;

    public Payment(double amount) {
        this.amount = amount;
        this.transactionID = generateTransactionID();
    }

    // Simulate a transaction ID generator
    private String generateTransactionID() {
        return "TXN" + System.currentTimeMillis();
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    // Abstract method for processing payment
    public abstract boolean processPayment();

    // Abstract method to generate a receipt
    public abstract void generateReceipt();
}