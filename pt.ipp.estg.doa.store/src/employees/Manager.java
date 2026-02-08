package employees;

import address.Address;

import java.time.LocalDate;

public class Manager extends Employee {
    private String department;
    private double bonus; //Annual performance

    public Manager(String firstName, String lastName, String email, String phone, Address address, LocalDate hireDate, double salary, String department, double bonus) {
        super(firstName, lastName, email, phone, address, hireDate, salary);
        this.department = department;
        this.bonus = bonus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void relocateDepartment(String newDepartment) {
        this.department = newDepartment;
    }

    @Override
    public Double calculateMonthlyPay() {
        return super.calculateMonthlyPay();
    }
}
