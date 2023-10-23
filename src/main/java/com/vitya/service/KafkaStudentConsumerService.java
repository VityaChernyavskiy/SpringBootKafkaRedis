package com.vitya.service;

import com.google.gson.Gson;
import com.vitya.model.dao.StudentDao;
import com.vitya.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaStudentConsumerService {

    @Value("${kafka.student.topic.name}") String kafkaTopicName;

    private StudentRepository studentRepository;

    private Gson gson;

    public KafkaStudentConsumerService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.gson = new Gson();
    }

    @KafkaListener(topics = "${kafka.student.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenStudent(@Payload String studentMessage) {
        StudentDao studentDao = gson.fromJson(studentMessage, StudentDao.class);
        studentRepository.save(studentDao);
        StudentDao studentFound = studentRepository.findById(studentDao.getId()).get();
        System.out.println("Found Student:" + gson.toJson(studentFound));
    }

}
