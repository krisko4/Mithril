package com.website.demo.notice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
public class NoticeControllerUnitTest {



    @Test
    public void shouldReturnDefaultMessage() {

        NoticeService noticeService = Mockito.mock(NoticeService.class);
        NoticeController noticeController = new NoticeController(noticeService);
        when(noticeService.sayHello("man")).thenReturn("Heyy man");
        assertEquals("Heyy man", noticeController.hey("man"));

    }

    @Test
    public void testGetNotice(){
        NoticeRepository noticeRepository = Mockito.mock(NoticeRepository.class);
        Notice notice  = noticeRepository.findById(14L).orElseThrow(IllegalStateException::new);
        assertThat(notice).isNotNull();

    }




//
//    @Test
//    public void contextLoads() throws Exception {
//        assertThat(noticeController).isNotNull();
//    }

//    @Test
//    void testFindingAllNotices() throws Exception {
//        MvcResult result = mockMvc.perform(get("/notices"))
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andReturn();
//    }


}