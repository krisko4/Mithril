package com.website.demo.webSocket;

import lombok.Data;

@Data
public class WebSocketMessage {

    private String date;
    private String content;
    private Long senderId;
    private Long receiverId;

}
