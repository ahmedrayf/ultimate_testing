package com.ultimate.testing.service;

import com.ultimate.testing.repo.Student;
import com.ultimate.testing.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAndGradeService {


    @Autowired
    private StudentRepo studentRepo;

    public void createStudent(String firstname, String lastname,  String email){
        Student student = new Student(firstname , lastname,email);
        studentRepo.save(student);
    }

    public void deleteStudent (int id){
        if (checkIfStudentIsNull(id))
            studentRepo.deleteById(id);
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
}
