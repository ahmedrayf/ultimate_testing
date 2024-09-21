package com.ultimate.testing.service;

import com.ultimate.testing.repo.Student;
import com.ultimate.testing.repo.StudentRepo;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest
public class ServiceTest {

    @Autowired
    private StudentAndGradeService studentService;
    @Autowired
    private StudentRepo studentRepo;


    @Test
    public void findStudentByEmailTest() {
        studentService.createStudent("Ahmed", "Rayf", "ahmedrayf@hotmail.com");

        Student student = studentRepo.findByEmailAddress("ahmedrayf@hotmail.com");

        assertEquals("chad.darby@luv2code_school.com", student.getEmailAddress(), "find by email");

    }
}
