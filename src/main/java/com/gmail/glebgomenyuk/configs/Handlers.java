package com.gmail.glebgomenyuk.configs;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import com.gmail.glebgomenyuk.facade.AddElementsForStart;
import com.gmail.glebgomenyuk.service.ClientService;
import com.gmail.glebgomenyuk.service.DeskService;
import com.gmail.glebgomenyuk.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Handlers implements AuthenticationSuccessHandler {

    @Autowired
    ClientService clientService;

    @Autowired
    DeskService deskService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();
        Map<String, Object> atribute = user.getAttributes();
        ClientEntity clientEntity = ClientEntity.of(
                (String) atribute.get("email"),
                (String) atribute.get("name"),
                (String) atribute.get("picture")
        );

        AddElementsForStart facade = new AddElementsForStart();
        List<DeskEntity> deskEntityList = facade.addDesks();
        List<TasksEntity> tasksEntities = facade.addTasks();

        clientService.save(clientEntity, deskEntityList, tasksEntities);

        httpServletResponse.sendRedirect("/");
    }

}