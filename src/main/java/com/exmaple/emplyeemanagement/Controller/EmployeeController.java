package com.exmaple.emplyeemanagement.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.exmaple.emplyeemanagement.Entity.Employee;
import com.exmaple.emplyeemanagement.Service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
   private final EmployeeService service;

    @PostMapping
    public List<Employee> create(@RequestBody List<Employee> employee) {
        return service.create(employee);
    }
 
    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id,
                           @RequestBody Employee employee) {
        return service.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted Successfully";
    }
 
}
