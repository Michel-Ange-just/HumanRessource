package org.michel.api.services;


import lombok.Data;
import org.michel.api.model.Employee;
import org.michel.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployeeById(final Integer id){
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(final Integer id){
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
}
