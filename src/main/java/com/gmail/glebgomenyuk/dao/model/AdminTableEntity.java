package com.gmail.glebgomenyuk.dao.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "admintable")
public class AdminTableEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String ip;
    private String city;
    private String region;
    private String country;

    private AdminTableEntity(String ip, String city, String region, String country) {
        this.ip = ip;
        this.city = city;
        this.region = region;
        this.country = country;
    }

}
