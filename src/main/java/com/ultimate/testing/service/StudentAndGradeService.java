package com.ultimate.testing.service;

import com.ultimate.testing.entity.MathGrade;
import com.ultimate.testing.entity.Student;
import com.ultimate.testing.repo.MathGradesRepo;
import com.ultimate.testing.repo.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private MathGradesRepo mathGradesRepo;
    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrade;


    public void createStudent(String firstname, String lastname,  String email){
        Student student = new Student(firstname , lastname,email);
        studentRepo.save(student);
    }

    public void deleteStudent (int id){
        if (checkIfStudentIsNull(id)){
            studentRepo.deleteById(id);
            mathGradesRepo.deleteByStudentId(id);
        }
    }

    public boolean checkIfStudentIsNull(int id){
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent())
            return true;
        return false;
    }

    public List<Student> findAllStudents() {
        List<Student> students = studentRepo.findAll();

        return students;
    }

    public boolean createGrade(double grade , int studentId , String gradeType){
        if (!checkIfStudentIsNull(studentId))
            return false;
        if (grade>=0 && grade<=100){
            mathGrade.setGrade(grade);
            mathGrade.setStudentId(studentId);
            mathGrade.setId(0);
            mathGradesRepo.save(mathGrade);
            return true;
        }
        return false;
    }

    public int deleteMathGrade(int id) {
        int studentId = 0;
        Optional<MathGrade> optionalMathGrade = mathGradesRepo.findById(id);
        if (!optionalMathGrade.isPresent())
            return studentId;
        studentId = optionalMathGrade.get().getStudentId();
        return studentId;
    }
}
