package pt.ipp.estg.doa.store.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ManagerResponse(
        Integer employeeId,
        String name,
        String nif,
        String email,
        String phone,
        String address,
        LocalDate hireDate,
        BigDecimal salary,
        BigDecimal Bonus,
        BigDecimal TotalSalary
) {
}
