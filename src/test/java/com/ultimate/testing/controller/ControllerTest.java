package com.ultimate.testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultimate.testing.entity.Student;
import com.ultimate.testing.repo.MathGradesRepo;
import com.ultimate.testing.repo.StudentRepo;
import com.ultimate.testing.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private StudentAndGradeService service;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private MathGradesRepo mathGradesRepo;
    @Autowired
    ObjectMapper objectMapper;
    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

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
    public void getAllStudentsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$",hasSize(1)));
    }

    @Test
    public void saveStudentTest() throws Exception {
        Student student = new Student();
        student.setFirstname("test");
        student.setLastname("create");
        student.setEmailAddress("testCreate@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());

        Student verifyStudent = studentRepo.findByEmailAddress("testCreate@gmail.com");
        assertNotNull(verifyStudent);
    }
}
