package Sweet.System;

public class Order {
    private String ProductName;
    private String orderID;
    private int Quantity;
    private String  orderStatus;
    private boolean isProcessed;

    public Order(String productName, int quantity, String orderID) {
        ProductName = productName;
        Quantity = quantity;
        this.orderID = orderID;
    }

    public  String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public String getOrderID() {
        return orderID;
    }

    @Override
    public String toString() {
        return "      ProductName:     " + ProductName + '\n' +
                "      Order ID:        " + orderID + '\n' +
                "      Quantity:        " + Quantity + '\n';
    }
}
