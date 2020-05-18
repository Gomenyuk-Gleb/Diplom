package com.gmail.glebgomenyuk.dao.model;


import com.gmail.glebgomenyuk.dao.model.enumforentity.RoleEnum;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Builder
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "user_table")
public class ClientEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    private String pictureURL;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEntity;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "client_desk", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "desk_id"))
    private List<DeskEntity> desk = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "client_desk_arch", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "desk_id"))
    private List<ArchiveDesksEntity> deskArch = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "client_message", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "message_id"))
    private List<MessageEntity> messageEntities = new ArrayList<>();


    public ClientEntity(String email, String name, String pictureURL) {
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
    }

    public static ClientEntity of(String email, String name, String pictureURL) {
        return new ClientEntity(email, name, pictureURL);
    }

    public static ClientEntity fromClientDTO(ClientDTO clientDTO) {
        return of(clientDTO.getEmail(), clientDTO.getName(), clientDTO.getPictureURL());
    }

    public ClientDTO toClienDTO() {
        return ClientDTO.of(email, name, pictureURL, id);
    }

}
