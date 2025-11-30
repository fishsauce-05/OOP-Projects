package com.burgerstore.ui;

import com.burgerstore.model.*;
import com.burgerstore.service.MealOrder;
import com.burgerstore.exception.ToppingLimitException;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BurgerAppGUI extends JFrame {
    
    // Data Setup
    private final String[] BURGER_OPTIONS = {"Classic Beef ($5.0)", "Spicy Chicken ($5.5)", "Deluxe Combo Special ($15.0)"};
    private final String[] DRINK_OPTIONS = {"Coke", "Pepsi", "Sprite", "Water", "Orange Juice"};
    private final String[] SIDE_OPTIONS = {"French Fries ($2.5)", "Onion Rings ($3.0)", "Nuggets ($4.0)", "Coleslaw ($2.0)"};
    
    // Components
    private JComboBox<String> cmbBurger, cmbDrink, cmbDrinkSize, cmbSide;
    private List<JCheckBox> toppingCheckboxes;
    private JTextArea txtReceipt;
    
    // Colors
    private final Color THEME_COLOR = new Color(255, 102, 0); // Orange Brand
    private final Color BG_COLOR = new Color(250, 250, 250);

    public BurgerAppGUI() {
        setTitle("Burger Store - OOP Project");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BG_COLOR);

        // --- HEADER ---
        JLabel lblTitle = new JLabel("THE BURGER STORE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(THEME_COLOR);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(lblTitle, BorderLayout.NORTH);

        // --- CENTER PANEL ---
        JPanel pnlCenter = new JPanel(new GridLayout(1, 2, 20, 0));
        pnlCenter.setBackground(BG_COLOR);
        pnlCenter.setBorder(new EmptyBorder(10, 20, 10, 20));

        // LEFT: Input Form
        JPanel pnlInput = new JPanel();
        pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.Y_AXIS));
        pnlInput.setBackground(BG_COLOR);

        // 1. Burger Section
        pnlInput.add(createSectionHeader("1. Select Your Burger"));
        cmbBurger = new JComboBox<>(BURGER_OPTIONS);
        cmbBurger.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbBurger.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        pnlInput.add(cmbBurger);

        // 2. Toppings Section
        pnlInput.add(createSectionHeader("2. Add Extras (Max 3, Deluxe 5)"));
        JPanel pnlToppings = new JPanel(new GridLayout(0, 2, 5, 5));
        pnlToppings.setBackground(BG_COLOR);
        toppingCheckboxes = new ArrayList<>();
        String[] tops = {"Cheese ($1.0)", "Bacon ($1.5)", "Egg ($1.0)", "Lettuce ($0.5)", "Tomato ($0.5)", "Pickles ($0.5)", "Onion ($0.5)", "Mayo ($0.3)"};
        
        for (String t : tops) {
            JCheckBox cb = new JCheckBox(t);
            cb.setBackground(BG_COLOR);
            cb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            toppingCheckboxes.add(cb);
            pnlToppings.add(cb);
        }
        pnlInput.add(pnlToppings);

        // 3. Sides & Drinks
        pnlInput.add(createSectionHeader("3. Sides & Beverages"));
        JPanel pnlSides = new JPanel(new GridLayout(2, 1, 5, 5));
        pnlSides.setBackground(BG_COLOR);
        
        // Drink Row
        JPanel pDrink = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pDrink.setBackground(BG_COLOR);
        pDrink.add(new JLabel("Drink: "));
        cmbDrink = new JComboBox<>(DRINK_OPTIONS);
        cmbDrinkSize = new JComboBox<>(new String[]{"SMALL", "MEDIUM", "LARGE"});
        pDrink.add(cmbDrink);
        pDrink.add(cmbDrinkSize);
        
        // Side Row
        JPanel pSide = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pSide.setBackground(BG_COLOR);
        pSide.add(new JLabel("Side Item: "));
        cmbSide = new JComboBox<>(SIDE_OPTIONS);
        pSide.add(cmbSide);
        
        pnlSides.add(pDrink);
        pnlSides.add(pSide);
        pnlInput.add(pnlSides);

        // RIGHT: Receipt Output
        JPanel pnlReceipt = new JPanel(new BorderLayout());
        pnlReceipt.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Receipt Preview", TitledBorder.CENTER, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 14)));
        txtReceipt = new JTextArea();
        txtReceipt.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtReceipt.setEditable(false);
        pnlReceipt.add(new JScrollPane(txtReceipt), BorderLayout.CENTER);

        pnlCenter.add(pnlInput);
        pnlCenter.add(pnlReceipt);
        add(pnlCenter, BorderLayout.CENTER);

        // --- FOOTER PANEL ---
        JPanel pnlFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        
        JButton btnOrder = new JButton("PLACE CUSTOM ORDER");
        styleButton(btnOrder, new Color(46, 204, 113)); // Green
        
        JButton btnDefault = new JButton("QUICK DEFAULT MEAL");
        styleButton(btnDefault, new Color(52, 152, 219)); // Blue

        pnlFooter.add(btnOrder);
        pnlFooter.add(btnDefault);
        add(pnlFooter, BorderLayout.SOUTH);

        // --- EVENTS ---
        btnOrder.addActionListener(e -> processOrder(false));
        btnDefault.addActionListener(e -> processOrder(true));
    }

    private JLabel createSectionHeader(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(THEME_COLOR);
        label.setBorder(new EmptyBorder(15, 0, 5, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200, 45));
    }

    private void processOrder(boolean isDefault) {
        try {
            MealOrder order;
            if (isDefault) {
                // Sử dụng Default Constructor cho bữa ăn mặc định
                order = new MealOrder();
            } else {
                // 1. Tạo Burger
                String bChoice = (String) cmbBurger.getSelectedItem();
                Burger burger;
                
                if (bChoice.contains("Deluxe")) {
                    burger = new DeluxeBurger();
                } else {
                    double price = bChoice.contains("Chicken") ? 5.5 : 5.0;
                    burger = new Burger(bChoice.split("\\(")[0].trim(), price);
                }

                // 2. Thêm Topping (Xử lý Exception tại đây)
                for (JCheckBox cb : toppingCheckboxes) {
                    if (cb.isSelected()) {
                        String raw = cb.getText();
                        String name = raw.split("\\(")[0].trim();
                        double price = Double.parseDouble(raw.split("\\$")[1].replace(")", ""));
                        burger.addTopping(new SideItem(name, price));
                    }
                }

                // 3. Drink & Side
                Drink drink = new Drink((String) cmbDrink.getSelectedItem(), (String) cmbDrinkSize.getSelectedItem(), 1.5);
                
                String sRaw = (String) cmbSide.getSelectedItem();
                SideItem side = new SideItem(sRaw.split("\\(")[0].trim(), Double.parseDouble(sRaw.split("\\$")[1].replace(")", "")));

                // 4. Tạo MealOrder
                order = new MealOrder(burger, drink, side);
            }
            
            // In hóa đơn ra màn hình và lưu file
            txtReceipt.setText(order.printReceipt(true));
            
        } catch (ToppingLimitException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Topping Limit Exceeded", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new BurgerAppGUI().setVisible(true));
    }
}
