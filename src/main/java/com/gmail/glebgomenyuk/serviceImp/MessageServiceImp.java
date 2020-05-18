package com.gmail.glebgomenyuk.serviceImp;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.MessageEntity;
import com.gmail.glebgomenyuk.dao.repository.ClientRepository;
import com.gmail.glebgomenyuk.dao.repository.MessageRepository;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.dto.MessageDTO;
import com.gmail.glebgomenyuk.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ClientRepository clientRepository;

    @Transactional
    @Override
    public List<MessageDTO> allMessage(String clientEmail) {

        ClientEntity clientEntity = clientRepository.findByEmail(clientEmail);
        List<MessageEntity> messageEntities = clientEntity.getMessageEntities();

        List<MessageDTO> messageDTOList = new ArrayList<>();
        messageEntities.forEach(x->{
            messageDTOList.add(x.toDTO());
        });

        return messageDTOList;
    }

    @Transactional
    @Override
    public List<MessageDTO> messageByClient(String byEmail) {
        String emailTo = "glebgomenyuk@gmail.com";
        List<MessageDTO> messageDTOS = new ArrayList<>();
        ClientEntity clientEntity = clientRepository.findByEmail(byEmail);
        ClientEntity clientEntityTo = clientRepository.findByEmail(emailTo);

        List<MessageEntity> messageEntities = clientEntityTo.getMessageEntities();

        messageEntities.forEach(x->{
            if(x.getEmmailFor().equals(clientEntity.getEmail()))
            messageDTOS.add(x.toDTO());
        });

        return messageDTOS;
    }

    @Transactional
    @Override
    public void messageSend(MessageDTO messageDTO) {

        ClientEntity clientTo = clientRepository.findByEmail(messageDTO.getEmailTo());
        ClientEntity clientFrom = clientRepository.findByEmail(messageDTO.getEmmailFor());

        MessageEntity messageEntity = MessageEntity.fromDTO(messageDTO);

        clientFrom.getMessageEntities().add(messageEntity);
        clientTo.getMessageEntities().add(messageEntity);
    }
}
