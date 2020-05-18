package com.gmail.glebgomenyuk.serviceImp;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.enumforentity.RoleEnum;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import com.gmail.glebgomenyuk.dao.repository.ClientRepository;
import com.gmail.glebgomenyuk.dao.repository.DeskRepository;
import com.gmail.glebgomenyuk.dao.repository.TasksRepository;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.*;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DeskRepository deskRepository;

    @Autowired
    TasksRepository tasksRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ClientDTO> findAllClients() {

        List<ClientDTO> clientDTO = new ArrayList<>();

        clientRepository.findAll().forEach(x ->
        {
            clientDTO.add(x.toClienDTO());
        });

        return clientDTO;
    }

    @Transactional
    @Override
    public ClientEntity load(String email) {
        return clientRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public ClientEntity update(ClientEntity clientEntity) {
        if(load(clientEntity.getEmail())==null){
            throw new EntityExistsException("Failed to save, course already exists, id:" + clientEntity.getEmail());
        }
        ClientEntity clientEntityUpdate = clientRepository.save(clientEntity);

        return  clientRepository.save(clientEntityUpdate);
    }

    @Transactional
    @Override
    public void  delete(String email) {
        if(load(email)==null){
            throw new EntityExistsException("Failed to save, course already exists, id:" + email);
        }
          clientRepository.deleteByEmail(email);
    }

    @Transactional
    @Override
    public void save(ClientEntity clientEntity, List<DeskEntity> deskEntities, List<TasksEntity> tasksEntities) {
        if (clientRepository.existsByEmail(clientEntity.getEmail()))
            return; // do nothing

        tasksEntities.forEach(x -> {
            deskEntities.get(0).getTasksEntities().add(x);
            x.setDeskEntity(deskEntities.get(0));
        });

        List<DeskEntity> deskEntityList = clientEntity.getDesk();
        deskEntities.forEach(x -> {
            deskEntityList.add(x);
        });

        if(clientEntity.getEmail().equals("glebgomenyuk@gmail.com"))
           clientEntity.setRoleEntity(RoleEnum.ADMIN);
        else
            clientEntity.setRoleEntity(RoleEnum.USER);

        clientRepository.save(clientEntity);
    }

    @Override
    public ClientDTO findByEmail(String email) {

        return clientRepository.findByEmail(email).toClienDTO();
    }
}
