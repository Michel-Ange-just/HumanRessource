package org.michel.api.controller;

import org.michel.api.model.Employee;
import org.michel.api.repository.EmployeeRepository;
import org.michel.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Read - Gel all employeees
     * @return - An iterable object of Employee full filled
     */

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    /**
     * create - add a new Empoyee
     * @param employee an object employee
     * @return the employee object saved
     */

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    /**
     * Read - Get one employee
     * @param id the id of the Employee
     * @return An Employee object full filled
     */
    @GetMapping("employee/{id}")
    public Employee getEmployee(@PathVariable Integer id){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else {
            return null;
        }
    }

    /**
     * Update - Update an existing employee
     * @param id - the id of the employee to update
     * @param employee - the employee object updated
     * @return
     */
    @PutMapping("employee/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
        Optional<Employee> e = employeeService.getEmployeeById(id);
        if(e.isPresent()){
            Employee currentEmployee = e.get();
            String firstName = employee.getFirstName();
            if(firstName != null){
                currentEmployee.setFirstName(firstName);
            }

            String lastName = employee.getLastName();
            if(lastName != null){
                currentEmployee.setLastName(lastName);
            }
            String  email = employee.getEmail();
            if(email != null){
                currentEmployee.setEmail(email);
            }
            String password = employee.getPassword();
            if(password != null){
                currentEmployee.setPassword(password);
            }
            employeeService.saveEmployee(currentEmployee);
            return currentEmployee;
        }
        else{
            return null;
        }
    }

    /**
     * Delete - Delete an Employee
     * @param id - the id of the employee to delete
     */
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployeeById(id);
    }

}
