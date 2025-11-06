import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {
    private final String BACKGROUND_COLOR = "#E5E5E5";
    private final String BUTTONS_COLOR = "#FFFFFF";
    private final String BUTTONS_HOVER_COLOR = "#F0F0F0";
    private final String BUTTONS_BORDER_COLOR = "#CCCCCC";
    private final String ACCENT_COLOR = "#2196F3";
    private final String TEXT_COLOR = "#333333";

    private final int BUTTON_CORNER_RADIUS = 12;
    private final int BUTTON_BORDER_WIDTH = 1;

    private ShoppingCart cart;
    private JLabel statusLabel;
    private JTextArea cartArea;

    public Main() {
        cart = new ShoppingCart();
        initializeUI();
        setupEventHandlers();
    }

    private void initializeUI() {
        setTitle("Online Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        Border roundedBorder = BorderFactory.createLineBorder(Color.decode(BUTTONS_BORDER_COLOR), BUTTON_BORDER_WIDTH);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 15, 10, 15);
        Border compoundBorder = BorderFactory.createCompoundBorder(roundedBorder, emptyBorder);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.decode(BACKGROUND_COLOR));

        JPanel productsPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        productsPanel.setBackground(Color.decode(BACKGROUND_COLOR));
        productsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Available Products"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        Map<String, Double> products = new HashMap<>();
        products.put("Samsung Galaxy S24", 849.99);
        products.put("Sony WH-1000XM5", 399.99);
        products.put("Nintendo Switch", 349.99);
        products.put("PlayStation 5", 499.99);
        products.put("Xbox Series X", 499.99);

        JButton[] productButtons = new JButton[products.size() + 1];

        int i = 0;
        for (Map.Entry<String, Double> entry : products.entrySet()) {
            String productName = entry.getKey();
            double price = entry.getValue();

            productButtons[i] = createStyledButton(
                    String.format("%s - $%.2f", productName, price),
                    compoundBorder
            );
            productButtons[i].addActionListener(e -> addProduct(productName, price, 1));
            productsPanel.add(productButtons[i]);
            i++;
        }

        productButtons[i] = createStyledButton(
                "Add Custom Product",
                compoundBorder
        );
        productButtons[i].addActionListener(e -> addCustomProduct());
        productsPanel.add(productButtons[i]);

        JPanel cartPanel = new JPanel(new BorderLayout(10, 10));
        cartPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Shopping Cart"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        cartPanel.setBackground(Color.decode(BACKGROUND_COLOR));

        cartArea = new JTextArea(10, 35);
        cartArea.setEditable(false);
        cartArea.setBackground(Color.WHITE);
        cartArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode(BUTTONS_BORDER_COLOR)),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        cartArea.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(cartArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        controlPanel.setBackground(Color.decode(BACKGROUND_COLOR));

        JButton clearBtn = createStyledButton("Clear Cart", compoundBorder);
        JButton checkoutBtn = createStyledButton("Place Order", compoundBorder);

        checkoutBtn.setBackground(Color.decode(ACCENT_COLOR));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#1976D2"), BUTTON_BORDER_WIDTH),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        controlPanel.add(clearBtn);
        controlPanel.add(checkoutBtn);

        cartPanel.add(scrollPane, BorderLayout.CENTER);
        cartPanel.add(controlPanel, BorderLayout.SOUTH);

        statusLabel = new JLabel("Ready for shopping!", SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        statusLabel.setBackground(Color.WHITE);
        statusLabel.setOpaque(true);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));

        mainPanel.add(productsPanel, BorderLayout.NORTH);
        mainPanel.add(cartPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);

        clearBtn.addActionListener(e -> cart.clearCart());
        checkoutBtn.addActionListener(e -> checkout());
    }

    private JButton createStyledButton(String text, Border border) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), BUTTON_CORNER_RADIUS, BUTTON_CORNER_RADIUS);

                super.paintComponent(g2);
                g2.dispose();
            }
        };

        button.setBackground(Color.decode(BUTTONS_COLOR));
        button.setForeground(Color.decode(TEXT_COLOR));
        button.setBorder(border);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!button.getBackground().equals(Color.decode(ACCENT_COLOR))) {
                    button.setBackground(Color.decode(BUTTONS_HOVER_COLOR));
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!button.getBackground().equals(Color.decode(ACCENT_COLOR))) {
                    button.setBackground(Color.decode(BUTTONS_COLOR));
                }
            }
        });

        return button;
    }

    private void setupEventHandlers() {
        cart.addCartListener(new CartListener() {
            @Override
            public void productAdded(CartEvent event) {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText(String.format(
                            "Added: %s x%d - $%.2f",
                            event.getProductName(),
                            event.getQuantity(),
                            event.getTotalPrice()
                    ));
                    updateCartDisplay();
                });
            }

            @Override
            public void productRemoved(CartEvent event) {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText(String.format("Removed: %s", event.getProductName()));
                    updateCartDisplay();
                });
            }

            @Override
            public void cartCleared(CartEvent event) {
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Cart cleared!");
                    updateCartDisplay();
                });
            }
        });
    }

    private void addProduct(String name, double price, int quantity) {
        cart.addProduct(name, price, quantity);
    }

    private void addCustomProduct() {
        String name = JOptionPane.showInputDialog(this, "Enter product name:");
        if (name != null && !name.trim().isEmpty()) {
            String priceStr = JOptionPane.showInputDialog(this, "Enter price:");
            try {
                double price = Double.parseDouble(priceStr);
                cart.addProduct(name, price, 1);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price!");
            }
        }
    }

    private void updateCartDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Items in cart: %d\n", cart.getItemCount()));
        sb.append(String.format("Total amount: $%.2f\n\n", cart.getTotal()));
        cartArea.setText(sb.toString());
    }

    private void checkout() {
        if (cart.getItemCount() > 0) {
            JOptionPane.showMessageDialog(this,
                    String.format("Order placed!\nItems: %d\nTotal: $%.2f",
                            cart.getItemCount(), cart.getTotal()));
            cart.clearCart();
        } else {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}