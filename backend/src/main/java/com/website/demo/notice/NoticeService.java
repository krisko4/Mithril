package com.website.demo.notice;

import com.website.demo.user.AppUser;
import com.website.demo.user.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Data
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final AppUserRepository appUserRepository;

    public List<Notice> findNotices(String date) {
        if(date != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            return noticeRepository.findBeforeDate(localDateTime);
        }
        return noticeRepository.findTopThree();
    }

    public void addNotice(String title, String content, Long doctorId, String date) {
        AppUser appUser = appUserRepository.findById(doctorId).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        Notice notice = new Notice(title, content, localDateTime,  appUser);
        noticeRepository.save(notice);
    }
}
