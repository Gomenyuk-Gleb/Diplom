package com.gmail.glebgomenyuk.controller;

import com.gmail.glebgomenyuk.dto.AdminTableDTO;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.dto.DeskDTO;
import com.gmail.glebgomenyuk.retrivers.GeoRetrievers;
import com.gmail.glebgomenyuk.service.AdminTableService;
import com.gmail.glebgomenyuk.service.ClientService;
import com.gmail.glebgomenyuk.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    DeskService deskService;

    @Autowired
    GeoRetrievers geoRetriever;

    @Autowired
    AdminTableService adminTableService;

    @GetMapping("/account")
    public ClientDTO clientDTO(OAuth2AuthenticationToken auth, HttpServletRequest request) {

        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");
        String name = (String) attrs.get("name");
        String picture = (String) attrs.get("picture");
        Long id = (Long) attrs.get("id");

        String ip = request.getRemoteAddr();

        AdminTableDTO location = geoRetriever.getLocation(ip);
        adminTableService.save(location);

        return ClientDTO.of(email, name, picture, id);
    }

    @GetMapping("/all")
    public List<ClientDTO> getAllClients() {

        return clientService.findAllClients();
    }


    @GetMapping("/desks")
    public List<DeskDTO> getAllDesksByClients(OAuth2AuthenticationToken aou) {

        String email = (String) aou.getPrincipal().getAttributes().get("email");
        return deskService.findAllDesksByClients(email);
    }
}
