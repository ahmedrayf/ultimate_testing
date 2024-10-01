package com.ultimate.testing.controller;

import com.ultimate.testing.entity.Student;
import com.ultimate.testing.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class GradeBookController {

    @Autowired
    private StudentAndGradeService service;

    @GetMapping
    public List<Student> getAllStudents(){
        return service.findAllStudents();
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        service.createStudent(student.getFirstname(), student.getLastname(), student.getEmailAddress());
    }

}