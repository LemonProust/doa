package pt.ipp.estg.doa.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ipp.estg.doa.store.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
