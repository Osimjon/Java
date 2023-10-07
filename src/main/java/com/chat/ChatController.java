package com.chat;
import com.chat.message.Message;
import com.chat.message.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("chat")
public class ChatController {
    private final MessageRepository messageRepository;

    @Autowired
    public ChatController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> getAllMessages(){
        List<Message> allMessages = messageRepository.findAll();
        return allMessages;
    }

    @PostMapping
    public ResponseEntity<String> postMessage(@RequestBody Message message){
        message.setMessageTime(Timestamp.valueOf(LocalDateTime.now()));
        messageRepository.save(message);
        String response = "Successfully sent!";
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}

