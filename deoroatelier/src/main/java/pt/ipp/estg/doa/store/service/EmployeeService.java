package pt.ipp.estg.doa.store.service;

import org.springframework.stereotype.Service;
import pt.ipp.estg.doa.store.model.entity.Employee;
import pt.ipp.estg.doa.store.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee findById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }
}
