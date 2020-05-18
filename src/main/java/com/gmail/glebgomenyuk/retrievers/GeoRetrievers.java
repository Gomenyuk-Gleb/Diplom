package com.gmail.glebgomenyuk.retrievers;

import com.gmail.glebgomenyuk.dto.AdminTableDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeoRetrievers {
    private static final String URL = "http://ipinfo.io/";

    public AdminTableDTO getLocation(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AdminTableDTO> response = restTemplate.getForEntity(URL + ip, AdminTableDTO.class);
        return response.getBody();
    }
}
