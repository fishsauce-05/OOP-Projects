package com.burgerstore.model;

public class DeluxeBurger extends Burger {
    public DeluxeBurger() {
        super("Deluxe Combo Special", 15.00); // Giá trọn gói cao
        this.maxToppings = 5; // Deluxe cho phép 5 topping
    }

    @Override
    public double getAdjustedPrice() {
        // Polymorphism: Deluxe không tính tiền thêm topping (đã bao gồm trong giá gốc)
        return getBasePrice();
    }
}
