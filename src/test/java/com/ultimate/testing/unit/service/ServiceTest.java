package com.ultimate.testing.unit.service;

import com.ultimate.testing.entity.Student;
import com.ultimate.testing.repo.StudentRepo;
import com.ultimate.testing.service.StudentAndGradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    private StudentRepo repo;

    @InjectMocks
    private StudentAndGradeService service;

    @Test
    public void findAllStudentsTestFail() {
        when(repo.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class , () -> service.findAllStudents() );
    }

    @Test
    public void findAllStudentsTest(){
        when(repo.findAll()).thenReturn(List.of(new Student(3,"Ahmed","Rayef","Cairo")));
        assertEquals(1, service.findAllStudents().size());
        assertEquals("Ahmed", service.findAllStudents().get(0).getFirstname());

    }
}
