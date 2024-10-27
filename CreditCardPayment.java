public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(double amount, String cardNumber, String cardHolderName) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean processPayment() {
        // Simulate payment processing
        System.out.println("Processing credit card payment for " + amount + "...");
        // Assume payment is always successful for now
        return true;
    }

    @Override
    public void generateReceipt() {
        System.out.println("Receipt:");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Paid Amount: $" + amount);
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Payment Method: Credit Card");
    }
}