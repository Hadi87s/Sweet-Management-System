package system;

public class Order {
    private String productName;
    private String orderID;
    private int quantity;
    private String  orderStatus;
    private boolean isProcessed;

    public Order(String productName, int quantity, String orderID) {
        this.productName = productName;
        this.quantity = quantity;
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

        return " Product name: "+productName + ", Order Id: " + orderID + "\n" ;
    }
}
