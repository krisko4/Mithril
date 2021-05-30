package com.website.demo.notice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RestController
@Data
@CrossOrigin
public class NoticeController {

    private final NoticeService noticeService;
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @RequestMapping("notice-sub")
    public SseEmitter subscribe(){
        SseEmitter emitter = new SseEmitter(0L);
        emitters.add(emitter);
       // emitter.onCompletion(() -> emitters.remove(emitter));
        return emitter;

    }



    @GetMapping("notices")
    public List<NoticeDto> findNotices(){
        return noticeService.findNotices().stream().map(NoticeDto::from).collect(Collectors.toList());
    }

    @GetMapping("notices/after")
    public List<NoticeDto> findNoticesAfter(@RequestParam String date){
        return noticeService.findNoticesAfter(date).stream().map(NoticeDto::from).collect(Collectors.toList());
    }

    @GetMapping("notices/before")
    public List<NoticeDto> findNoticesBefore(@RequestParam String date){
        return noticeService.findNoticesBefore(date).stream().map(NoticeDto::from).collect(Collectors.toList());
    }

    @PostMapping("notices")
    public void addNotice(@RequestBody NoticeRequest noticeRequest){
       NoticeDto noticeDto = NoticeDto.from(noticeService.addNotice(
               noticeRequest.getTitle(),
               noticeRequest.getContent(),
               noticeRequest.getDoctorId(),
               noticeRequest.getDate()
       ));
        for(SseEmitter emitter : emitters){
            try {
                emitter.send(SseEmitter.event().name("notice").data(noticeDto));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


}
