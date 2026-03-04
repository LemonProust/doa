package pt.ipp.estg.doa.store.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.estg.doa.store.model.dto.request.CreateCustomerRequest;
import pt.ipp.estg.doa.store.model.dto.request.UpdateCustomerRequest;
import pt.ipp.estg.doa.store.model.dto.response.CustomerResponse;
import pt.ipp.estg.doa.store.model.dto.response.CustomerSummaryResponse;
import pt.ipp.estg.doa.store.service.CustomerServiceImpl;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    // ============= CREATE ENDPOINTS =============

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Valid CreateCustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ============= READ ENDPOINTS =============

    @GetMapping
    public ResponseEntity<List<CustomerSummaryResponse>> getAllCustomers() {
        List<CustomerSummaryResponse> responses = customerService.getAllCustomers();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nif/{nif}")
    public ResponseEntity<CustomerResponse> getCustomerByNif(@PathVariable String nif) {
        CustomerResponse response = customerService.getCustomerByNif(nif);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(@PathVariable String email) {
        CustomerResponse response = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerResponse> getCustomerByPhone(@PathVariable String phone) {
        CustomerResponse response = customerService.getCustomerByPhone(phone);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerSummaryResponse>> searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Boolean active) {
        List<CustomerSummaryResponse> responses = customerService.searchCustomers(name, city, active);
        return ResponseEntity.ok(responses);
    }

    // ============= UPDATE ENDPOINTS =============

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request) {
        CustomerResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<CustomerResponse> activateCustomer(@PathVariable Long id) {
        CustomerResponse response = customerService.activateCustomer(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<CustomerResponse> deactivateCustomer(@PathVariable Long id) {
        CustomerResponse response = customerService.deactivateCustomer(id);
        return ResponseEntity.ok(response);
    }

    // ============= BUSINESS OPERATION ENDPOINTS =============

    @PostMapping("/{id}/loyalty-points")
    public ResponseEntity<CustomerResponse> addLoyaltyPoints(
            @PathVariable Long id,
            @RequestParam Integer points) {
        CustomerResponse response = customerService.addLoyaltyPoints(id, points);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/record-purchase")
    public ResponseEntity<CustomerResponse> recordPurchase(
            @PathVariable Long id,
            @RequestParam BigDecimal amount) {
        CustomerResponse response = customerService.recordPurchase(id, amount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top/by-purchases")
    public ResponseEntity<List<CustomerResponse>> getTopCustomersByPurchases(
            @RequestParam(defaultValue = "10") int limit) {
        List<CustomerResponse> responses = customerService.getTopCustomersByPurchases(limit);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/top/by-spent")
    public ResponseEntity<List<CustomerResponse>> getTopCustomersBySpent(
            @RequestParam(defaultValue = "10") int limit) {
        List<CustomerResponse> responses = customerService.getTopCustomersBySpent(limit);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/birthday-today")
    public ResponseEntity<List<CustomerResponse>> getCustomersWithBirthdayToday() {
        List<CustomerResponse> responses = customerService.getCustomersWithBirthdayToday();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/vip")
    public ResponseEntity<List<CustomerResponse>> getVipCustomers() {
        List<CustomerResponse> responses = customerService.getVipCustomers();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/new")
    public ResponseEntity<List<CustomerResponse>> getNewCustomers() {
        List<CustomerResponse> responses = customerService.getNewCustomers();
        return ResponseEntity.ok(responses);
    }

    // ============= STATISTICS ENDPOINTS =============

    @GetMapping("/statistics/active-count")
    public ResponseEntity<Long> getActiveCustomersCount() {
        long count = customerService.getActiveCustomersCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/statistics/average-spent")
    public ResponseEntity<Double> getAverageCustomerSpent() {
        Double average = customerService.getAverageCustomerSpent();
        return ResponseEntity.ok(average);
    }

    @GetMapping("/statistics/total-spent")
    public ResponseEntity<BigDecimal> getTotalCustomerSpent() {
        BigDecimal total = customerService.getTotalCustomerSpent();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/statistics/tier/{tier}")
    public ResponseEntity<Long> getCustomersCountByTier(@PathVariable String tier) {
        long count = customerService.getCustomersCountByTier(tier);
        return ResponseEntity.ok(count);
    }

    // ============= VALIDATION ENDPOINTS =============

    @GetMapping("/exists/nif/{nif}")
    public ResponseEntity<Boolean> existsByNif(@PathVariable String nif) {
        boolean exists = customerService.existsByNif(nif);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        boolean exists = customerService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/phone/{phone}")
    public ResponseEntity<Boolean> existsByPhone(@PathVariable String phone) {
        boolean exists = customerService.existsByPhone(phone);
        return ResponseEntity.ok(exists);
    }

    // ============= DELETE ENDPOINTS =============

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
