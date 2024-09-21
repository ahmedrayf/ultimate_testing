package com.ultimate.testing.service;

import com.ultimate.testing.repo.Student;
import com.ultimate.testing.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAndGradeService {


    @Autowired
    private StudentRepo studentRepo;

    public void createStudent(String firstname, String lastname,  String email){
        Student student = new Student(firstname , lastname,email);

        studentRepo.save(student);
    }
}
