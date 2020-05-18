package com.gmail.glebgomenyuk.dao.model;


import com.gmail.glebgomenyuk.dto.MessageDTO;
import com.gmail.glebgomenyuk.dto.TasksDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "massage")
@NoArgsConstructor
@Getter
@Setter
public class MessageEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String emailTo;
    private String emmailFor;
    private String message;

    public MessageEntity(String emailTo, String emmailFor, String message) {
        this.emailTo = emailTo;
        this.emmailFor = emmailFor;
        this.message = message;
    }

    @ManyToMany(mappedBy = "messageEntities")
    Set<ClientEntity> clientEntities = new HashSet<>();

    public static MessageEntity fromDTO(MessageDTO messageDTO){
        return new MessageEntity(messageDTO.getEmailTo(), messageDTO.getEmmailFor(), messageDTO.getMessage());
    }
    public MessageDTO toDTO(){
        return MessageDTO.of(this.emailTo, this.emmailFor, this.message);
    }

}
