package com.ultimate.testing.repo;

import com.ultimate.testing.entity.MathGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MathGradesRepo extends JpaRepository<MathGrade, Integer> {

    List<MathGrade> findGradeByStudentId (int id);

    void deleteByStudentId(int id);
}
