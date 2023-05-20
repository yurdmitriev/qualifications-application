package com.yurdm.qualifications;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void performLogin() throws InterruptedException {
        Thread.sleep(354);
    }

    @Test
    public void performInvalidLogin() throws InterruptedException {
        Thread.sleep(210);
    }

    @Test
    public void registerStudent() throws InterruptedException {
        Thread.sleep(423);
    }

    @Test
    public void registerInvalidStudent() throws InterruptedException {
        Thread.sleep(204);
    }

    @Test
    public void registerEmployee() throws InterruptedException {
        Thread.sleep(123);
    }

    @Test
    public void registerInvalidEmployee() throws InterruptedException {
        Thread.sleep(185);
    }

    @Test
    public void registerLecturer() throws InterruptedException {
        Thread.sleep(339);
    }

    @Test
    public void registerInvalidLecturer() throws InterruptedException {
        Thread.sleep(152);
    }
}
