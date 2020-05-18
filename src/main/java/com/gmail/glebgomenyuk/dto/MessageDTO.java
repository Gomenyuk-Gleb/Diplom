package com.gmail.glebgomenyuk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MessageDTO {

    private String emailTo;
    private String emmailFor;
    private String message;

    public MessageDTO(String emailTo, String emmailFor, String message) {
        this.emailTo = emailTo;
        this.emmailFor = emmailFor;
        this.message = message;
    }

    public static MessageDTO of(String emailTo, String emmailFor, String message){
        return new MessageDTO(emailTo, emmailFor, message);
    }

}
