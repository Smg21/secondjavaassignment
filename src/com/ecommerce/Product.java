package com.ecommerce;

public class Product {
    // Attributes of the product
    private int productID;        // Product ID
    private String name;          // Name of the product
    private double price;         // Price of the product
    private String imageUrl;      // URL for the product image
    private String description;   // Description of the product

    // Constructor to initialize the product with validation for price
    public Product(int productID, String name, double price, String imageUrl, String description) {
        // Validate that the price is non-negative
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    // Getter and Setter methods for each attribute

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    // Setter for price with validation to ensure it's non-negative
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method to provide a readable format of product information
    @Override
    public String toString() {
        return "Product [ID=" + productID + ", Name=" + name + ", Price=" + price + "]";
    }
}



