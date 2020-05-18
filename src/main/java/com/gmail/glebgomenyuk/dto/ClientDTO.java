package com.gmail.glebgomenyuk.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private  Long id;
    private String email;
    private String name;
    private String pictureURL;

    public ClientDTO(String email, String name,  String pictureURL, Long id) {
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
        this.id = id;
    }

    public static ClientDTO of(String email, String name,  String pictureURL, Long id){
        return new ClientDTO(email, name, pictureURL, id);
    }

}
