package be.reactiveprogramming.paymentprocessor.gateway.command;

public class CreatePaymentCommand {

    private String id;
    private String creditCardNumber;
    private String amount;

    public CreatePaymentCommand() {
    }

    public CreatePaymentCommand(String id, String creditCardNumber, String amount) {
        this.id = id;
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getAmount() {
        return amount;
    }

}
