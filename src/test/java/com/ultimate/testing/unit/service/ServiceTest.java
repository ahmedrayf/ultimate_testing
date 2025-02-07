package com.ultimate.testing.unit.service;

import com.ultimate.testing.repo.StudentRepo;
import com.ultimate.testing.service.StudentAndGradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @MockBean
    private StudentRepo repo;

    @InjectMocks
    private StudentAndGradeService service;

    @Test
    public void doTest(){

    }
}
