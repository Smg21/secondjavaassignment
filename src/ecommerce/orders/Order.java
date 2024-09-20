package ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;

import java.util.List;

public class Order {
    // Attributes of the order
    private int orderID;            // Order ID
    private Customer customer;      // The customer placing the order
    private List<Product> products; // List of products in the order
    private double total;           // Total cost of the order

    // Constructor to create an order, using the customer's shopping cart
    public Order(int orderID, Customer customer) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = customer.getShoppingCart();  // Get the products from the customer's cart
        this.total = customer.calculateTotalCost();  // Calculate the total cost
    }

    // Method to generate and print the order summary
    public void generateOrderSummary() {
        System.out.println("Order Summary for Order ID: " + orderID);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(" - " + product.getName() + ": $" + product.getPrice());
        }
        System.out.println("Total: $" + total);
    }

    // Getter for the total cost of the order
    public double getTotal() {
        return total;
    }
}



