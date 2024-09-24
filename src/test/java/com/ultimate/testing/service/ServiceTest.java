package com.ultimate.testing.service;

import com.ultimate.testing.repo.Student;
import com.ultimate.testing.repo.StudentRepo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class ServiceTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentAndGradeService studentService;
    @Autowired
    private StudentRepo studentRepo;

    @BeforeEach
    public void setUpDatabase(){
        jdbcTemplate.execute("insert into student(id,  firstname , lastname, email_address) values(1,'Ahmed' , 'Rayf' , 'ahmedrayf@hotmail.com') ");
    }
    @AfterEach
    public void cleanUpDatabase(){
        jdbcTemplate.execute("Delete from student");
    }

    @Test
    public void checkStudentExistTest() {
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Sql("/insertData.sql")
    @Test
    public void findAllTest() {
        List<Student> students = studentService.findAllStudents();
        assertEquals(5,students.size());
    }
    @Test
    public void findStudentByEmailTest() {
        Student student = studentRepo.findByEmailAddress("ahmedrayf@hotmail.com");
        assertEquals("ahmedrayf@hotmail.com", student.getEmailAddress(), "find by email");
    }

    @Test
    public void deleteStudentTest() {
        assertTrue(studentRepo.findById(1).isPresent() , "Student is not exist ");
        studentService.deleteStudent(1);
        assertFalse(studentRepo.findById(1).isPresent());

    }

}
