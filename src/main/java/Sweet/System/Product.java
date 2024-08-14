package Sweet.System;

public class Product {
    private String name;
    private String description;
    private double price;
    private double rawMaterialCost;
    private int sellingTimes;
    private double discount;
    private boolean discountApplied;
    public Product() {
        discount=0.0;
        discountApplied=false;
        sellingTimes=0;
    }

    public Product(String name, String description, double price, double rawMaterialCost) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rawMaterialCost = rawMaterialCost;
        sellingTimes=0;
    }

    public Product(String name, double price, double rawMaterialCost) {
        this.name = name;
        this.price = price;
        this.rawMaterialCost = rawMaterialCost;
        discount=0.0;
        sellingTimes=0;
    }

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        discount=0.0;
        sellingTimes=0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRawMaterialCost() {
        return rawMaterialCost;
    }

    public double getProfit() {
        return price - rawMaterialCost;
    }

    public int getSellingTimes() {
        return sellingTimes;
    }

    public void setSellingTimes(int sellingTimes) {
        this.sellingTimes = sellingTimes;
    }

    public void incrementSellingTimes() {
        sellingTimes++;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRawMaterialCost(double rawMaterialCost) {
        this.rawMaterialCost = rawMaterialCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void updatePrice(double discountPercentage) {
        // Ensure discount percentage is between 0 and 100
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }

        // Calculate the discount amount
        double discountAmount = (discountPercentage / 100) * price;

        // Subtract the discount amount from the original price
       price -= discountAmount;
        discountApplied=true;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }

    @Override
    public String toString() {
        return "        "+name + ", " + description + ", " + price;
    }
}


