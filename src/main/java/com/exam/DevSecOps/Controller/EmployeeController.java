package com.exam.DevSecOps.Controller;

import com.exam.DevSecOps.Entity.Employee;
import com.exam.DevSecOps.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id, Locale locale) {
        Employee employee = employeeService.getEmployeeById(id, locale);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee, Locale locale) {
        employeeService.addEmployee(employee, locale);
        return ResponseEntity.ok(messageSource.getMessage("success.employee.added", null, locale));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employeeDetails,
            Locale locale) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails, locale);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id, Locale locale) {
        employeeService.deleteEmployee(id, locale);
        return ResponseEntity.ok(messageSource.getMessage("success.employee.deleted", null, locale));
    }
}
