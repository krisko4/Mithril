package com.website.demo.message;

import lombok.Data;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Data
@CrossOrigin
public class MessageController {

    private final MessageService messageService;
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @MessageMapping("hello")
    @SendTo("topic/greetings")
    public TextMessage greeting(String message) throws Exception {
        return new TextMessage("hello");
    }


    @PostMapping("new-message")
    public void sendMessage(@RequestBody MessageRequest messageRequest){
        Message message = messageService.sendMessage(
                messageRequest.getContent(),
                messageRequest.getDate(),
                messageRequest.getReceiverId(),
                messageRequest.getSenderId()
        );
        for(SseEmitter emitter : emitters){
            try {
                emitter.send(SseEmitter.event().name("spring").data(message));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
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
