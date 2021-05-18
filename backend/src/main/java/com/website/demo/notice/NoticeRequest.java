package com.website.demo.notice;

import lombok.Data;

@Data
public class NoticeRequest {

    private String title;
    private String content;
    private Long doctorId;
    private String date;

}
