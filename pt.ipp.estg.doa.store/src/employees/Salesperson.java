package employees;

import address.Address;
import utils.ValidationUtils;

import java.time.LocalDate;

public class Salesperson extends Employee{
    private double commissionRate;
    private double totalSales; // Cumulative sales amount

    public Salesperson(int employeeId, String name, String nif, String email, String phone, Address address, LocalDate hireDate, double salary, double commissionRate, double totalSales) {
        super(employeeId, name, nif, email, phone, address, hireDate, salary);
        validateCommissionRate(commissionRate);
        validateTotalSales(totalSales);
        this.commissionRate = commissionRate;
        this.totalSales = totalSales;
    }

    // Internal validation
    private void validateTotalSales(double totalSales) {
        if (totalSales < 0) {
            throw new IllegalArgumentException("Total sales cannot be less than 0.");
        }
    }

    private void validateCommissionRate(double commissionRate) {
        if (commissionRate < 0 || commissionRate > 100) {
            throw new IllegalArgumentException("Commission rate must be between 0 and 100.");
        }
    }

    @Override
    public double calculatePay() {
        return getSalary() + (totalSales * commissionRate/100.00);
    }

    @Override
    public String toCSV() {
        return String.format(
                "%d,SALESPERSON,%s,%s,%s,%.2f,%.2f,%.2f",
                getEmployeeId(),
                getName(),
                getNif(),
                getHireDate(),
                getSalary(),
                commissionRate,
                totalSales
        );
    }

    @Override
    public void fromCSV(String csvLine) {

    }
}
