package pt.ipp.estg.doa.store.model.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Manager extends Employee {
    private String department;
    private BigDecimal bonus;

    public Manager() {
        super();
    }

    public Manager(String name, String nif, String email, String phone, String address, LocalDate hireDate, BigDecimal salary, String department, BigDecimal bonus) {
        super(name, nif, email, phone, address, hireDate, salary);
        this.department = department;
        this.bonus = bonus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    private void validateBonus(BigDecimal bonus) {
        if (bonus.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Bonus cannot be negative.");
        }
    }

    private void validateDepartment(String department) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null.");
        }
    }

}
