import employees.EmployeeManager;
import orders.OrderManager;
import payments.PaymentMethod;

import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String EMPLOYEE_CSV = "employees.csv";
    private static final String JEWELRY_CSV = "employees.csv";
    private static final String ORDER_CSV = "employees.csv";
    private static final String CUSTOMER_CSV = "employees.csv";

    public static void main(String[] args) {
        EmployeeManager em = new EmployeeManager();
        em.addSalesPerson("Valdemar Buco", "931-931-931", "va@gm.co", "+351939363999", "rua dad00", LocalDate.now(), 2500.00, 50.00);

        System.out.println(em);
        System.out.printf("Monthly Pay: %.2f €%n", em.calculateTotalPayroll());


    // ---------- FILTER ----------
        System.out.println("\n=== Salespeople ===");
        em.listSalespeople().forEach(System.out::println);

        System.out.println("\n=== Managers ===");
        em.listManagers().forEach(System.out::println);

    // ---------- PAYROLL ----------
        System.out.println("\n=== Total Payroll ===");
        System.out.printf("Total: %.2f €%n",
                em.calculateTotalPayroll());

    // ---------- SAVE ----------
        em.save(EMPLOYEE_CSV);

        System.out.println("\n=== Employees saved to CSV ===");
    }

    private static void payOrder(Scanner scanner, OrderManager orderManager) {
        System.out.println("Order ID: ");
        int orderID = scanner.nextInt();
        System.out.println("Amount: ");
        int amount = scanner.nextInt();

        System.out.print("Method (CREDIT_CARD/BANK_TRANSFER/CASH): ");
        PaymentMethod paymentMethod = PaymentMethod.valueOf(scanner.next().toUpperCase());

        try{
            orderManager.payOrder(orderID, amount, paymentMethod);
            System.out.println("Order Payment Successful!");
        }catch (Exception e){
            System.out.println("Order Payment Failed!");
        }
    }
}