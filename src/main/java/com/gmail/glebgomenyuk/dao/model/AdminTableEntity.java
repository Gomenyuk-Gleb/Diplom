package com.gmail.glebgomenyuk.dao.model;

import com.gmail.glebgomenyuk.dto.AdminTableDTO;
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

    public static AdminTableEntity of(String ip, String city, String region, String country){
        return new AdminTableEntity(ip, city, region, country);
    }

    public AdminTableDTO toDTO() {
        return AdminTableDTO.of(ip, city, region, country);
    }

    public static AdminTableEntity fromDTO(AdminTableDTO dto) {
        return  AdminTableEntity.of(dto.getIp(), dto.getCity(), dto.getRegion(), dto.getCountry());
    }

}
