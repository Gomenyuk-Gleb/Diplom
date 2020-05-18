package com.gmail.glebgomenyuk.dao.model.enumforentity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


public enum RoleEnum {

    USER,MODERATOR,ADMIN;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
