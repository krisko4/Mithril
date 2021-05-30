package com.website.demo.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {




    @Query(value = "select new com.website.demo.message.MessageDto(m.content, m.date, m.sender.id, m.receiver.id) from message m where (m.sender.id = ?2 and m.receiver.id = ?1) or (m.sender.id = ?1 and m.receiver.id = ?2)")
    List<MessageDto> findMessagesBy(Long receiverId, Long senderId);

    @Query(value = "select new com.website.demo.message.MessageDto(m.content, m.date, m.sender.id, m.receiver.id) from message m where m.receiver.id = ?1 ")
    List<MessageDto> findMessagesForDoctor(Long id);
}
