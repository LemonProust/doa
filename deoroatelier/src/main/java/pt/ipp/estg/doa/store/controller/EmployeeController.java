package pt.ipp.estg.doa.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ipp.estg.doa.store.model.dto.response.ManagerResponse;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping
    public ResponseEntity<ManagerResponse> findAllEmployee() {
        return null;
    }
}
