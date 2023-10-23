package com.vitya.service;

import com.vitya.model.dao.StudentDao;
import com.vitya.model.dto.StudentDto;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;


@Service
public class StudentService {

    private KafkaStudentProducerService kafkaStudentProducerService;

    public StudentService(KafkaStudentProducerService kafkaStudentProducerService) {
        this.kafkaStudentProducerService = kafkaStudentProducerService;
    }

    public StudentDao processStudent(StudentDto studentDto){
        double averageGrade = countAverageGrade(studentDto.getGrades());
        StudentDao studentDao = new StudentDao(UUID.randomUUID().toString(), studentDto.getFullName(), averageGrade);
        kafkaStudentProducerService.sendStudent(studentDao);
        return studentDao;
    }
    private double countAverageGrade(List<Double> grades) {
        return   grades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}

