package com.exmaple.emplyeemanagement.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.exmaple.emplyeemanagement.Entity.Employee;

@Service
public class KafkaConsumerServices {

    @KafkaListener(
            topics = "employee-topic",
            groupId = "employee-group")
    public void consume(Employee employee) {

        System.out.println("Employee Received");

        System.out.println(employee.getId());
        System.out.println(employee.getName());
        System.out.println(employee.getDepartment());
    }
}