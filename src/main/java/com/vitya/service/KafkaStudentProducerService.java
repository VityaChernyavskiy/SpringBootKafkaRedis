package com.vitya.service;

import com.google.gson.Gson;
import com.vitya.model.dao.StudentDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaStudentProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String kafkaTopicName;
    private Gson gson;


    public KafkaStudentProducerService(KafkaTemplate<String, String> kafkaTemplate, @Value("${kafka.student.topic.name}") String kafkaTopicName) {
        this.kafkaTemplate = kafkaTemplate;

        this.kafkaTopicName = kafkaTopicName;

        this.gson = new Gson();

    }

    public void sendStudent(StudentDao studentDao){
        String kafkaTopicKey = new String(studentDao.getFullName());
        kafkaTemplate.send(kafkaTopicName, kafkaTopicKey, gson.toJson(studentDao));
    }
}
