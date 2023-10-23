package com.vitya.controller;

import com.vitya.model.dao.StudentDao;
import com.vitya.model.dto.StudentDto;
import com.vitya.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/student")
@RestController
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity postStudent(@RequestBody StudentDto studentDto){
        StudentDao studentDao = studentService.processStudent(studentDto);
        return ResponseEntity.ok(studentDao);
    }
}
