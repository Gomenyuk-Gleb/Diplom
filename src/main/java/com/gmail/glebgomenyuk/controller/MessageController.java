package com.gmail.glebgomenyuk.controller;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.MessageEntity;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.dto.MessageDTO;
import com.gmail.glebgomenyuk.service.MessageService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/all")
    public List<MessageDTO> allMessage(String email) {
        email = "glebgomenyuk@gmail.com";
        return messageService.allMessage(email);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody  MessageDTO messageDTO) {
        messageService.messageSend(messageDTO);
    }

    @GetMapping("/{email}")
    public List<MessageDTO> messageByClient(@PathVariable (value = "email") String email) {
       return messageService.messageByClient(email);
    }

}
