package com.example.notes_api.Controllers;

import com.example.notes_api.DTO.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MessageDto hello(MessageDto messageDto) {
        return messageDto;
    }
}
