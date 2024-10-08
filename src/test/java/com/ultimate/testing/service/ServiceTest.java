package com.ultimate.testing.service;

import com.ultimate.testing.entity.MathGrade;
import com.ultimate.testing.entity.Student;
import com.ultimate.testing.repo.MathGradesRepo;
import com.ultimate.testing.repo.StudentRepo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class ServiceTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentAndGradeService studentService;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private MathGradesRepo mathGradesRepo;
    @Value("${student.id}")
    private  int studentId;

    @Value("${sql.script.create.student}")
    private String sqlAddStudent;
    @Value("${sql.script.create.math.grade}")
    private String sqlAddMathGrade;
    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;
    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;

    @BeforeEach
    public void setUpDatabase() {
        jdbcTemplate.execute(sqlAddStudent);
        jdbcTemplate.execute(sqlAddMathGrade);

    }

    @AfterEach
    public void cleanUpDatabase() {
        jdbcTemplate.execute(sqlDeleteStudent);
        jdbcTemplate.execute(sqlDeleteMathGrade);

    }

    @Test
    public void checkStudentExistTest() {
        assertTrue(studentService.checkIfStudentIsNull(studentId));
        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Sql("/insertData.sql")
    @Test
    public void findAllTest() {
        List<Student> students = studentService.findAllStudents();
        assertEquals(5, students.size());
    }

    @Test
    public void findStudentByEmailTest() {
        Student student = studentRepo.findByEmailAddress("ahmedrayf@hotmail.com");
        assertEquals("ahmedrayf@hotmail.com", student.getEmailAddress(), "find by email");
    }

    @Test
    public void deleteStudentTest() {
        assertTrue(studentRepo.findById(studentId).isPresent(), "Student is not exist ");
        studentService.deleteStudent(studentId);
        assertFalse(studentRepo.findById(studentId).isPresent());
        assertTrue(mathGradesRepo.findGradeByStudentId(studentId).isEmpty());

    }

    @Test
    public void createGradeTest() {
        assertTrue(studentService.createGrade(90.0, studentId, "math"));

        List<MathGrade> mathGrades = mathGradesRepo.findGradeByStudentId(studentId);

        assertEquals(2, mathGrades.size());
    }

    @Test
    public void failCreateGradeTest() {
        assertFalse(studentService.createGrade(115.0, studentId, "math"), "Value more than 100");
        assertFalse(studentService.createGrade(-5.0, studentId, "math"), "Negative value");
    }

    @Test
    public void deleteMathGradeTest() {
        assertEquals(100, studentService.deleteMathGrade(100) , "returns deleted grade's studentId" );
    }

}
