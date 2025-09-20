public class Order {
    private int orderId;
    private int itemCount;
    private double totalCost;

    public Order(int orderId, int itemCount, double totalCost) {
        this.orderId = orderId;
        this.itemCount = itemCount;
        this.totalCost = totalCost;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public static double getAverage(Order[] orders) {
        double sum = 0;
        for(Order order : orders) sum += order.getTotalCost();
        return sum / orders.length;
    }

    public static void sortOrders(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalCost() < orders[j + 1].getTotalCost()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }
}
