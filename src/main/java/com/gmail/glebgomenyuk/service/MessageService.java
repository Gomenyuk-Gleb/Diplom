package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dao.model.MessageEntity;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.dto.MessageDTO;

import java.util.List;

public interface MessageService {

    public void messageSend(MessageDTO messageDTO);

    public List<MessageDTO> allMessage(String email);

    public List<MessageDTO> messageByClient(String email);

}
