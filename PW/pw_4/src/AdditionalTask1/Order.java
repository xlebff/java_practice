package AdditionalTask1;

public class Order implements Payable, Refundable {
    private double amount;
    private boolean isRefund;
    private Status status;

    private enum Status {
        CREATED, PAID, REFUNDED;
    }

    public Order(double amount) {
        this.amount = amount;
        this.isRefund = false;
        this.status = Status.CREATED;
    }

    public double getAmount() { return this.amount; }
    public String getStatus() { return this.status.name(); }

    public void setRefund() {
        if (status == Status.PAID) {
            isRefund = true;
            System.out.println("The \"refund\" status is set.");
        }
    }

    @Override
    public void processPayment(double amount) {
        if (!isRefund && checkStatus(Status.CREATED)) paymentOperation(amount);
        else if(isRefund && checkStatus(Status.PAID)) refundOperation(amount);
    }

    private void paymentOperation(double amount) {
        this.status = Status.PAID;
        System.out.println("Payment in the amount of " +
                amount + " rubles has been made.");
    }

    private void refundOperation(double amount) {
        this.status = Status.REFUNDED;
        System.out.println("A refund of " +
                amount + " rubles has been made.");
    }

    private boolean checkStatus(Status requiredStatus) {
        if (this.status != requiredStatus) System.out.println("The operation has already been performed.");
        return this.status == requiredStatus;
    }
}
