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
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final AppUserRepository appUserRepository;

    public List<Notice> findNotices() {
        return noticeRepository.findTopFour();
    }

    public Notice addNotice(String title, String content, Long doctorId, String date) {
        AppUser appUser = appUserRepository.findById(doctorId).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        Notice notice = new Notice(title, content, localDateTime,  appUser);
        noticeRepository.save(notice);
        return notice;
    }

    public List<Notice> findNoticesBefore(String date) {
        return noticeRepository.findBeforeDate(date);
    }

    public List<Notice> findNoticesAfter(String date) {
        return noticeRepository.findAfterDate(date);
    }
}
