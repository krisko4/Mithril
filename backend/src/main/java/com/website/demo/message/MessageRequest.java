package com.website.demo.message;

import lombok.Data;

@Data
public class MessageRequest {

    private Long senderId;
    private Long receiverId;
    private String content;
    private String date;
}
