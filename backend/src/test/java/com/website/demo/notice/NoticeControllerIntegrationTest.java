package com.website.demo.notice;

import com.website.demo.security.utils.JWTRequestFilter;
import com.website.demo.security.utils.JWTUtil;
import com.website.demo.user.AppUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@WebMvcTest(NoticeController.class)
@AutoConfigureMockMvc(addFilters = false)
class NoticeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticeService noticeService;





    @Test
    void shouldGetDefaultWelcomeMessage() throws Exception {
        when(noticeService.sayHello("Roman"))
                .thenReturn("Heyy Roman");
        mockMvc.perform(get("/yoooo"))
                .andExpect(status().isOk())
        .andExpect(content().string(equalTo("Heyy Roman")));
        verify(noticeService).sayHello("Roman");
    }

    @Test
    void shouldGetCustomWelcomeMessage() throws Exception {
        when(noticeService.sayHello("Rysiek"))
                .thenReturn("Heyy Rysiek");
        mockMvc.perform(get("/yoooo?name=Rysiek"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Heyy Rysiek")));
      //  verify(noticeService).sayHello("John");
    }
}