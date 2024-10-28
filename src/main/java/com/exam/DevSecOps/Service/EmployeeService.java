package com.exam.DevSecOps.Service;

import com.exam.DevSecOps.Entity.Employee;
import com.exam.DevSecOps.Exception.ResourceNotFoundException;
import com.exam.DevSecOps.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MessageSource messageSource;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id, Locale locale) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.employee.notfound", null, locale)));
    }

    public Employee addEmployee(Employee employee, Locale locale) {
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (existingEmployee.isPresent()) {
            throw new ResourceNotFoundException(
                    messageSource.getMessage("error.email.inuse", null, locale));
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails, Locale locale) {
        Employee employee = getEmployeeById(id, locale);
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id, Locale locale) {
        Employee employee = getEmployeeById(id, locale);
        employeeRepository.delete(employee);
    }
}
