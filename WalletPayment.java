public class WalletPayment extends Payment {
    private String walletID;

    public WalletPayment(double amount, String walletID) {
        super(amount);
        this.walletID = walletID;
    }

    @Override
    public boolean processPayment() {
        System.out.println("Processing wallet payment for $" + amount + " using Wallet ID: " + walletID + "...");
        // Simulate success
        return true;
    }

    @Override
    public void generateReceipt() {
        System.out.println("Receipt:");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Paid Amount: $" + amount);
        System.out.println("Payment Method: Wallet");
        System.out.println("Wallet ID: " + walletID);
    }
}