# Hamburger-OOP
Dự án máy bán Hamburger sử dụng Java Swing GUI

## 🎯 Các tính chất OOP được thể hiện

### 1. **Đóng gói (Encapsulation)**
- Tất cả các thuộc tính trong class `Item` được khai báo `private` (name, type, price, size)
- Truy cập thông qua các phương thức getter/setter public:
  ```java
  public String getName() { return name; }
  public void setSize(String size) { this.size = size; }
  ```
- Class `Burger` đóng gói danh sách toppings và maxToppings, chỉ cho phép thêm topping qua method `addTopping()`

### 2. **Kế thừa (Inheritance)**
- **Cấu trúc kế thừa:**
  - `Item` (abstract class - lớp cha)
    - `Burger` extends `Item`
      - `DeluxeBurger` extends `Burger`
    - `Drink` extends `Item`
    - `SideItem` extends `Item`

- Các class con kế thừa thuộc tính và phương thức từ class cha `Item`
- `DeluxeBurger` kế thừa từ `Burger` và mở rộng `maxToppings = 5`

### 3. **Đa hình (Polymorphism)**
- **Method Overriding:**
  - `getAdjustedPrice()` được override ở các class:
    - `Item`: Tính giá theo size (SMALL, MEDIUM, LARGE)
    - `Burger`: Tính giá burger + toppings
    - `DeluxeBurger`: Giá cố định, không tính thêm topping (đã bao gồm)
  
  ```java
  // Item.java
  public double getAdjustedPrice() {
      return switch (size.toUpperCase()) {
          case "SMALL" -> price - 0.5;
          case "LARGE" -> price + 1.0;
          default -> price;
      };
  }
  
  // DeluxeBurger.java
  @Override
  public double getAdjustedPrice() {
      return getBasePrice(); // Không tính phí topping
  }
  ```

- **Runtime Polymorphism:** Đối tượng `Burger` có thể là `Burger` hoặc `DeluxeBurger`, method `getAdjustedPrice()` được gọi dựa trên loại thực tế

### 4. **Trừu tượng (Abstraction)**
- Class `Item` được khai báo `abstract`:
  ```java
  public abstract class Item { ... }
  ```
- Không thể tạo trực tiếp object `Item`, chỉ có thể tạo các subclass cụ thể (Burger, Drink, SideItem)
- Cung cấp template chung cho tất cả các item trong cửa hàng

### 🎁 Tính năng bổ sung
- **Exception Handling:** `ToppingLimitException` - Xử lý trường hợp thêm quá số lượng topping cho phép
- **File I/O:** Lưu hóa đơn vào file `receipt.txt` thông qua method `printReceipt(true)`

---

## 🚀 Cách chạy code

### Yêu cầu hệ thống
- **Java Development Kit (JDK):** phiên bản 14 trở lên (do sử dụng switch expression)
- **IDE:** IntelliJ IDEA, Eclipse, hoặc VS Code với Java Extension

### Cách 1: Chạy bằng IDE (Khuyến nghị)

1. **Clone repository:**
   ```bash
   git clone https://github.com/fishsauce-05/Hamburger-OOP.git
   cd Hamburger-OOP
   ```

2. **Mở project trong IDE:**
   - IntelliJ IDEA: `File → Open → Chọn thư mục project`
   - Eclipse: `File → Import → Existing Projects into Workspace`

3. **Chạy chương trình:**
   - Tìm file `BurgerAppGUI.java` trong `src/com/burgerstore/ui/`
   - Click chuột phải → `Run 'BurgerAppGUI.main()'`

### Cách 2: Chạy bằng Command Line

1. **Compile code:**
   ```bash
   # Từ thư mục gốc của project
   javac -d bin src/com/burgerstore/**/*.java
   ```

2. **Chạy chương trình:**
   ```bash
   java -cp bin com.burgerstore.ui.BurgerAppGUI
   ```

### Hướng dẫn sử dụng
1. Chọn loại Burger (Classic Beef, Spicy Chicken, hoặc Deluxe Combo)
2. Chọn các topping (tối đa 3 cho burger thường, 5 cho Deluxe)
3. Chọn đồ uống và kích thước (SMALL/MEDIUM/LARGE)
4. Chọn món phụ (Fries, Nuggets, v.v.)
5. Nhấn "Place Order" để xem hóa đơn
6. Hóa đơn sẽ được lưu vào file `receipt.txt`

---

## 📁 Cấu trúc thư mục
```
Hamburger-OOP/
├── src/
│   └── com/
│       └── burgerstore/
│           ├── model/           # Các class mô hình (Item, Burger, Drink...)
│           ├── service/         # Logic xử lý đơn hàng (MealOrder)
│           ├── ui/              # Giao diện GUI (BurgerAppGUI)
│           └── exception/       # Custom exceptions
├── bin/                         # Compiled .class files
└── receipt.txt                  # Hóa đơn xuất ra
```

---

## 📝 Tác giả
- **GitHub:** [@fishsauce-05](https://github.com/fishsauce-05)
- **Project:** Hamburger OOP - Máy bán Hamburger tự động
