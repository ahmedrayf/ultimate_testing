package com.ultimate.testing.repo;

import com.ultimate.testing.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    public Student findByEmailAddress(String mail);
}
