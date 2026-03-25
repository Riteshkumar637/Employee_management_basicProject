package com.exmaple.emplyeemanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exmaple.emplyeemanagement.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
