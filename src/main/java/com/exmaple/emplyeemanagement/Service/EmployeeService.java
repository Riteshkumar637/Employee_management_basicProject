package com.exmaple.emplyeemanagement.Service;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.exmaple.emplyeemanagement.Entity.Employee;
import com.exmaple.emplyeemanagement.Repository.EmployeeRepository;
import com.exmaple.emplyeemanagement.Kafka.KafkaProducerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final KafkaProducerService kafkaProducerService;

    public List<Employee> create(List<Employee> employees) {
        List<Employee> savedEmployees= repository.saveAll(employees);
        savedEmployees.forEach(employee -> kafkaProducerService.sendEmployee(employee));

        return savedEmployees;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee update(Long id, Employee employee) {
        Employee existing = getById(id);
        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());
        Employee updatedEmployee =
                repository.save(existing);

        kafkaProducerService.sendEmployee(updatedEmployee);

        return updatedEmployee;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
