package com.website.demo.message;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Data
public class MessageDto {

    private String content;
    private LocalDateTime date;
    private Long senderId;
    private Long receiverId;

    public MessageDto(String content,
                      LocalDateTime date,
                      Long senderId,
                      Long receiverId){
        this.content = content;
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }


}
