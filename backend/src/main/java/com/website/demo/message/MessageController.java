package com.website.demo.message;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Data
@CrossOrigin
public class MessageController {


    private final MessageService messageService;




    @PostMapping("new-message")
    public void sendMessage(@RequestBody MessageRequest messageRequest){
         messageService.sendMessage(
                messageRequest.getContent(),
                messageRequest.getDate(),
                messageRequest.getReceiverId(),
                messageRequest.getSenderId()
        );

    }


    @GetMapping("messages")
    public List<MessageDto> getMessagesBy(@RequestParam(required = false) Long receiverId,
                                       @RequestParam(required = false) Long senderId){
        return messageService.findMessagesBy(receiverId, senderId);
    }

    @GetMapping("doctors/{id}/messages")
    public List<MessageDto> getMessagesBy(@PathVariable Long id){
        return messageService.findMessagesForDoctor(id);
    }

}
