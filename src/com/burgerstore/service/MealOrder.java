package com.burgerstore.service;

import com.burgerstore.model.*;
import java.io.FileWriter;
import java.io.IOException;

public class MealOrder {
    private Burger burger;
    private Drink drink;
    private SideItem side;

    // Constructor tùy chỉnh
    public MealOrder(Burger burger, Drink drink, SideItem side) {
        this.burger = burger;
        this.drink = drink;
        this.side = side;
    }

    // Constructor mặc định (Default Meal - Yêu cầu bài toán)
    public MealOrder() {
        this.burger = new Burger("Classic Beef (Default)", 5.00);
        this.drink = new Drink("Coke", "SMALL", 1.50);
        this.side = new SideItem("Fries", 2.50);
    }

    public double getTotalPrice() {
        return burger.getAdjustedPrice() + drink.getAdjustedPrice() + side.getAdjustedPrice();
    }

    public String printReceipt(boolean saveToFile) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n============= BURGER STORE RECEIPT =============\n");
        
        // 1. In Burger và Topping
        sb.append(String.format("BURGER: %-30s $%.2f\n", burger.getName(), burger.getBasePrice()));
        for (Item t : burger.getToppings()) {
            double tPrice = (burger instanceof DeluxeBurger) ? 0.0 : t.getAdjustedPrice();
            sb.append(String.format(" + Extra: %-29s $%.2f\n", t.getName(), tPrice));
        }
        
        // 2. In Drink
        sb.append(String.format("DRINK:  %-30s $%.2f\n", drink.getName() + " (" + drink.getSize() + ")", drink.getAdjustedPrice()));
        
        // 3. In Side
        sb.append(String.format("SIDE:   %-30s $%.2f\n", side.getName(), side.getAdjustedPrice()));
        
        sb.append("------------------------------------------------\n");
        sb.append(String.format("TOTAL DUE: %36s\n", String.format("$%.2f", getTotalPrice())));
        sb.append("================================================\n");
        sb.append("       Thank you for choosing Burger Store!     \n");

        // Ghi file (File I/O)
        if (saveToFile) {
            try (FileWriter writer = new FileWriter("receipt.txt")) {
                writer.write(sb.toString());
            } catch (IOException e) {
                System.err.println("Error saving receipt: " + e.getMessage());
            }
        }
        return sb.toString();
    }
}
