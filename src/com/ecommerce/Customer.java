package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    // Attributes of the customer
    private int customerID;              // Customer ID
    private String name;                 // Customer's name
    private List<Product> shoppingCart;  // List to store products in the shopping cart

    // Constructor Customer with a name validation
    public Customer(int customerID, String name) {
        // Validate that the name is not null or empty
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();  // Initialize an empty shopping cart
    }

    // Getter and Setter methods for each attribute

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    // Setter for name with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        this.name = name;
    }

    // Add a product to the customer's shopping cart
    public void addProductToCart(Product product) {
        shoppingCart.add(product);
    }

    // Get the list of products in the shopping cart
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    // Calculate the total cost of products in the shopping cart
    public double calculateTotalCost() {
        return shoppingCart.stream().mapToDouble(Product::getPrice).sum();
    }

    // Remove a product from the shopping cart
    public void removeProductFromCart(Product product) {
        shoppingCart.remove(product);
    }

    // Provide a readable format of customer information
    @Override
    public String toString() {
        return "Customer [ID=" + customerID + ", Name=" + name + "]";
    }
}



