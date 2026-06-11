package com.exmaple.emplyeemanagement.Kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.exmaple.emplyeemanagement.Entity.Employee;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "employee-topic";

    private final KafkaTemplate<String, Employee> kafkaTemplate;

    public KafkaProducerService(
            KafkaTemplate<String, Employee> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmployee(Employee employee) {

        kafkaTemplate.send(TOPIC, employee);

        System.out.println(
                "Employee Published : " +
                        employee.getName());
    }
}