package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface ClientService {

        public List<ClientDTO> findAllClients();

        public ClientEntity load(String email);

        public ClientEntity update(ClientEntity clientEntity);

        public void delete(String email);

        public void save(ClientEntity clientEntity, List<DeskEntity> deskEntities, List<TasksEntity> tasksEntities);

}
