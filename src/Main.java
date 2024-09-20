import com.ecommerce.*;
import ecommerce.orders.Order;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Main extends JFrame {
    // Information about the customer and their shopping cart
    private Customer customer;

    // Constructor for Main class
    public Main() {
        // Customer Selena
        customer = new Customer(1, "Selena Gomez");
        // Initialize the UI
        initUI();
    }

    // Method to set up the UI
    private void initUI() {
        // window title and dimensions
        setTitle("E-Commerce Store Unit 2");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Close the application
        setLocationRelativeTo(null);  // Center the window on the screen
        getContentPane().setBackground(new Color(173, 216, 230));

        // Title label
        JLabel titleLabel = new JLabel("E-Commerce Store Unit 2", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Oswald", Font.BOLD, 24));  // Set font style and size
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(65, 105, 225));
        add(titleLabel, BorderLayout.NORTH);  // Add the label

        // Display the products
        JPanel productPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Space between products
        productPanel.setBackground(new Color(135, 206, 250));

        // Diff Products
        Product product1 = new Product(101, "Laptop", 799.99, "https://picsum.photos/200/300", "A powerful laptop for all your computing needs.");
        Product product2 = new Product(102, "Smartphone", 499.99, "https://picsum.photos/200/300", "A sleek smartphone with a stunning display.");
        Product product3 = new Product(103, "Headphones", 29.99, "https://picsum.photos/200/300", "Comfortable headphones with superior sound quality.");
        Product product4 = new Product(104, "Smartwatch", 199.99, "https://picsum.photos/200/300", "A stylish smartwatch with fitness tracking features.");
        Product product5 = new Product(105, "Tablet", 299.99, "https://picsum.photos/200/300", "A lightweight tablet perfect for entertainment.");
        Product product6 = new Product(106, "Bluetooth Speaker", 59.99, "https://picsum.photos/200/300", "A portable Bluetooth speaker with excellent sound.");

        // Add each product
        addProductToPanel(productPanel, product1);
        addProductToPanel(productPanel, product2);
        addProductToPanel(productPanel, product3);
        addProductToPanel(productPanel, product4);
        addProductToPanel(productPanel, product5);
        addProductToPanel(productPanel, product6);

        // Add the product panel to the center of the window
        add(productPanel, BorderLayout.CENTER);

        // View Cart & Place Order button
        JButton viewCartButton = new JButton("View Cart & Place Order");
        viewCartButton.setBackground(new Color(0, 102, 204));  // Blue button
        viewCartButton.setForeground(Color.WHITE);
        // Add action listener to handle
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCartAndPlaceOrder();  // Show cart and allow customer to place an order
            }
        });

        // View Cart button
        add(viewCartButton, BorderLayout.SOUTH);
    }

    // Add a product to the product panel
    private void addProductToPanel(JPanel panel, Product product) {
        // Create a sub-panel for each product
        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setBackground(new Color(135, 206, 250)); // Sky blue

        // Label to show the product's name and price
        JLabel nameLabel = new JLabel(product.getName() + " - $" + product.getPrice(), SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);

        try {
            // Load the product image from the given URL
            BufferedImage image = ImageIO.read(new URL(product.getImageUrl()));
            // Scale the image to the desired size
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(200, 300, Image.SCALE_DEFAULT));
            JLabel imageLabel = new JLabel(imageIcon);
            // Add the image label to the product panel
            productPanel.add(imageLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            // Handle error if image fails to load
            System.err.println("Error loading image for " + product.getName() + ": " + e.getMessage());
            JLabel imageLabel = new JLabel("Image not available");
            productPanel.add(imageLabel, BorderLayout.CENTER);
        }

        // Buttons to add the product to the cart and view more information
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.setBackground(new Color(135, 206, 250)); // Sky blue

        // Button to add the product to the cart
        JButton addButton = new JButton("Add to Cart");
        addButton.setBackground(new Color(0, 102, 204));  // Blue button
        addButton.setForeground(Color.WHITE);
        // Add action listener to handle adding the product to the cart
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the product to the customer's shopping cart
                customer.addProductToCart(product);
                // Show a popup message confirming the product was added to the cart
                JOptionPane.showMessageDialog(null, product.getName() + " added to cart.");
            }
        });

        // Button to show more information about the product
        JButton moreInfoButton = new JButton("More Info");
        moreInfoButton.setBackground(new Color(0, 102, 204));  // Blue button
        moreInfoButton.setForeground(Color.WHITE);
        // Add action listener to display the product's description
        moreInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a dialog with the product's description
                JOptionPane.showMessageDialog(null, product.getDescription(), product.getName() + " Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add the buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(moreInfoButton);

        // Add the product name label and buttons to the product panel
        productPanel.add(nameLabel, BorderLayout.NORTH);
        productPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the product panel to the main panel
        panel.add(productPanel);
    }

    // View the shopping cart and place the order
    private void viewCartAndPlaceOrder() {
        // Check if the shopping cart is empty
        if (customer.getShoppingCart().isEmpty()) {
            // Show a popup message indicating that the cart is empty
            JOptionPane.showMessageDialog(null, "Your cart is empty!");
            return;
        }

        // Display the products in the cart
        JPanel cartPanel = new JPanel(new GridLayout(customer.getShoppingCart().size(), 1));
        StringBuilder cartDetails = new StringBuilder("Cart Details:\n");

        // Loop through the products in the cart
        for (Product product : customer.getShoppingCart()) {
            cartDetails.append(product.getName()).append(" - $").append(product.getPrice()).append("\n");

            JPanel productPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel(product.getName() + " - $" + product.getPrice(), SwingConstants.LEFT);

            // Button to remove the product from the cart
            JButton removeButton = new JButton("Remove");
            removeButton.setBackground(new Color(0, 102, 204));  // Blue button
            removeButton.setForeground(Color.WHITE);
            // Add action listener to handle removing the product from the cart
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Remove the product from the customer's shopping cart
                    customer.removeProductFromCart(product);
                    // Show a popup message confirming the product was removed
                    JOptionPane.showMessageDialog(null, product.getName() + " removed from cart.");
                    // Refresh the cart view after removing the product
                    viewCartAndPlaceOrder();
                }
            });

            // Add the name label and remove button to the product panel
            productPanel.add(nameLabel, BorderLayout.CENTER);
            productPanel.add(removeButton, BorderLayout.EAST);

            // Add the product panel to the cart panel
            cartPanel.add(productPanel);
        }

        cartDetails.append("\nTotal: $").append(customer.calculateTotalCost());

        // Show a confirmation dialog for placing the order
        int choice = JOptionPane.showConfirmDialog(null, cartPanel, "Place Order?", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Create a new order and show the order summary
            Order order = new Order(1001, customer);
            order.generateOrderSummary();
            // Show a message indicating the order was successfully placed
            JOptionPane.showMessageDialog(null, "Order placed successfully!\nOrder Total: $" + order.getTotal());
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Run the application on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();  // Create an instance of the Main class
            app.setVisible(true);  // Make the application window visible
        });
    }
}




