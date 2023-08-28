package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.repository.ShowTimeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class ShowTimeServiceImplTests {

    @SpyBean
    @Autowired
    private ShowTimeService toTest;

    @Test
    public void test_deleteById_ok(){
        Mockito.doReturn(new ShowTime()).when(toTest).findById(Mockito.any());
        toTest.delete(1);
    }
}
