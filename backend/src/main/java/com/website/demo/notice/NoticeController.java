package com.website.demo.notice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Data
@CrossOrigin
public class NoticeController {

    private final NoticeService noticeService;



    @GetMapping("notices")
    public List<NoticeDto> findNotices(@RequestParam(required = false) String date){
        return noticeService.findNotices(date).stream().map(NoticeDto::from).collect(Collectors.toList());
    }

    @PostMapping("notices")
    public void addNotice(NoticeRequest noticeRequest){
        noticeService.addNotice(
                noticeRequest.getTitle(),
                noticeRequest.getContent(),
                noticeRequest.getDoctorId(),
                noticeRequest.getDate()
        );
    }


}
