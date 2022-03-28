package com.website.demo.webSocket;

import com.website.demo.message.MessageDto;
import com.website.demo.message.MessageService;
import com.website.demo.notice.Notice;
import com.website.demo.notice.NoticeDto;
import com.website.demo.notice.NoticeRequest;
import com.website.demo.notice.NoticeService;
import com.website.demo.visit.VisitDto;
import com.website.demo.visit.request.NewUnfinishedVisitRequest;
import lombok.Data;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.w3c.dom.Text;

@Controller
@Data
public class WebSocketController {

    private final MessageService messageService;
    private final NoticeService noticeService;


    @MessageMapping("/new-notice")
    @SendTo("/topic/notice-board")
    public NoticeDto saveNotice(NoticeRequest noticeRequest){
        Notice notice = noticeService.addNotice(
                noticeRequest.getTitle(),
                noticeRequest.getContent(),
                noticeRequest.getDoctorId(),
                noticeRequest.getDate()
        );
        return NoticeDto.from(notice);
    }

    @MessageMapping("/new-visit/{id}")
    @SendTo("/topic/{id}/today-visits")
    public String saveNewUnfinishedVisit(@DestinationVariable String id, String date){
        return date;
    }


    @MessageMapping("/hey")
    @SendTo("/topic/chat")
    public TextMessage sayHello(){
        return new TextMessage("Hey guys");
    }

    @SubscribeMapping("/chat")
    public TextMessage yo(){
        return new TextMessage("Heyoo");
    }


    @MessageMapping("/hello/{id}")
    @SendTo("/topic/{id}")
    public WebSocketMessage sendMessage(WebSocketMessage message, @DestinationVariable String id) {
        messageService.sendMessage(message.getContent(),
                message.getDate(),
                message.getReceiverId(),
                message.getSenderId());
        return message;
    }



}
