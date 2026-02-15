package ui;

import customers.CustomerManagement;
import employees.EmployeeManager;
import jewlry.Category;
import jewlry.Jewelry;
import jewlry.JewelryManager;
import orders.Order;
import orders.OrderItem;
import orders.OrderManager;
import payments.PaymentMethod;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final JewelryManager jewelryManager;
    private final OrderManager orderManager;
    private final EmployeeManager employeeManager;
    private final CustomerManagement customerManagement;

    private final Scanner sc = new Scanner(System.in);

    public ConsoleUI(JewelryManager jm,
                     OrderManager om,
                     EmployeeManager em,
                     CustomerManagement cm) {

        this.jewelryManager = jm;
        this.orderManager = om;
        this.employeeManager = em;
        this.customerManagement = cm;
    }

    // ========= APP START =========

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n=== JEWELRY SHOP SYSTEM ===");
            System.out.println("1. Employees");
            System.out.println("2. Jewelry");
            System.out.println("3. Orders");
            System.out.println("4. Payments");
            System.out.println("0. Exit");

            int op = readInt();

            switch (op) {
                case 1 -> employeeMenu();
                case 2 -> jewelryMenu();
                case 3 -> orderMenu();
                case 4 -> paymentMenu();
                case 0 -> running = false;
            }
        }
    }

    // ========= EMPLOYEES =========

    private void employeeMenu() {

        System.out.println("\n--- EMPLOYEES ---");
        System.out.println("1. List employees");
        System.out.println("2. Payroll total");

        int op = readInt();

        switch (op) {
            case 1 -> System.out.println(employeeManager);
            case 2 -> System.out.printf("Total payroll: %.2f €%n",
                    employeeManager.calculateTotalPayroll());
        }
    }

    // ========= JEWELRY =========

    private void jewelryMenu() {

        System.out.println("\n--- JEWELRY ---");
        System.out.println("1. List jewelry");
        System.out.println("2. Add jewelry");

        int op = readInt();

        switch (op) {
            case 1 -> listJewelry();
            case 2 -> addJewelry();
        }
    }

    private void listJewelry() {

        List<Jewelry> list = jewelryManager.findAll();

        if (list.isEmpty()) {
            System.out.println("No jewelry found.");
            return;
        }

        System.out.println("\nID | TYPE | NAME | PRICE | STOCK");

        for (Jewelry j : list) {
            System.out.printf("%d | %s | %s | %.2f€ | %d%n",
                    j.getJewelryId(),
                    j.getJewelryType(),
                    j.getName(),
                    j.getPrice(),
                    j.getStock());
        }
    }

    private void addJewelry() {

        System.out.println("1. Necklace  2. Ring  3. Earring");
        int type = readInt();

        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Material: ");
        String material = sc.next();

        System.out.print("Weight: ");
        double weight = sc.nextDouble();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Stock: ");
        int stock = sc.nextInt();

        System.out.print("Category (LUXURY/CASUAL/BRIDAL): ");
        Category category = Category.valueOf(sc.next().toUpperCase());

        switch (type) {

            case 1 -> {
                System.out.print("Length: ");
                double length = sc.nextDouble();
                jewelryManager.addNecklace(name, material, weight, price, stock, category, length);
            }

            case 2 -> {
                System.out.print("Size: ");
                int size = sc.nextInt();
                jewelryManager.addRing(name, material, weight, price, stock, category, size);
            }

            case 3 -> {
                System.out.print("Clasp: ");
                String clasp = sc.next();
                jewelryManager.addEarring(name, material, weight, price, stock, category, clasp);
            }

            default -> System.out.println("Invalid type.");
        }
    }

    // ========= ORDERS =========

    private void orderMenu() {

        System.out.println("\n--- ORDERS ---");
        System.out.println("1. Create order");
        System.out.println("2. List orders");
        System.out.println("3. Cancel order");

        int op = readInt();

        switch (op) {
            case 1 -> createOrder();
            case 2 -> listOrders();
            case 3 -> cancelOrder();
        }
    }

    private void createOrder() {

        Order order = orderManager.createOrder();

        while (true) {

            System.out.print("Jewelry id (0 to finish): ");
            int id = readInt();

            if (id == 0) break;

            System.out.print("Quantity: ");
            int qty = readInt();

            try {
                orderManager.addItem(order.getOrderId(), id, qty);
                System.out.println("Item added.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Order total: €" + order.getTotal());
    }

    private void listOrders() {

        for (Order order : orderManager.findAllOrders()) {

            System.out.println("\nOrder #" + order.getOrderId() +
                    " | date=" + order.getOrderDate() +
                    " | total=€" + order.getTotal());

            for (OrderItem item : order.getItems()) {
                System.out.println(" - " + item.getJewelryName()
                        + " x" + item.getQuantity()
                        + " = €" + item.getTotalPrice());
            }
        }
    }

    private void cancelOrder() {
        System.out.print("Order ID: ");
        int id = readInt();
        orderManager.cancelOrder(id);
        System.out.println("Order cancelled.");
    }

    // ========= PAYMENTS =========

    private void paymentMenu() {

        System.out.println("\n--- PAYMENTS ---");
        System.out.println("1. Pay order");

        int op = readInt();

        if (op == 1) {
            System.out.print("Order ID: ");
            int id = readInt();

            System.out.print("Amount: ");
            int amount = sc.nextInt();

            System.out.print("Method (CREDIT_CARD/BANK_TRANSFER/CASH): ");
            PaymentMethod method =
                    PaymentMethod.valueOf(sc.next().toUpperCase());

            try {
                orderManager.payOrder(id, amount, method);
                System.out.println("Payment successful.");
            } catch (Exception e) {
                System.out.println("Payment failed: " + e.getMessage());
            }
        }
    }

    // ========= INPUT SAFE =========

    private int readInt() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Enter a number: ");
        }
        return sc.nextInt();
    }
}
