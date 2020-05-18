package com.gmail.glebgomenyuk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminTableDTO {
    private String ip;
    private String city;
    private String region;
    private String country;

    private AdminTableDTO(String ip, String city, String region, String country) {
        this.ip = ip;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public static AdminTableDTO of(String ip, String city, String region, String country) {
        return new AdminTableDTO(ip, city, region, country);
    }
}
