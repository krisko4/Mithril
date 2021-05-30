package com.website.demo.message;

import com.website.demo.user.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity(name = "message")
@Data
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AppUser sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private AppUser receiver;

    public Message(String content,
                   LocalDateTime date,
                   AppUser sender,
                   AppUser receiver) {
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }
}
