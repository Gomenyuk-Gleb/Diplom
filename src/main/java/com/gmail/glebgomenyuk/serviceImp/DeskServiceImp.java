package com.gmail.glebgomenyuk.serviceImp;

import com.gmail.glebgomenyuk.dao.model.*;
import com.gmail.glebgomenyuk.dao.repository.ArchiveDesksRepository;
import com.gmail.glebgomenyuk.dao.repository.ClientRepository;
import com.gmail.glebgomenyuk.dao.repository.DeskRepository;
import com.gmail.glebgomenyuk.dao.repository.TasksRepository;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.dto.DeskDTO;
import com.gmail.glebgomenyuk.service.ArchiveDesksService;
import com.gmail.glebgomenyuk.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.crypto.Des;

import javax.persistence.EntityExistsException;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class DeskServiceImp implements DeskService {

    @Autowired
    DeskRepository deskRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    ArchiveDesksRepository archiveDesksRepository;


    @Transactional
    @Override
    public List<DeskDTO> findAllDesksByClients(String email) {

        List<DeskDTO> deskDTOS = new ArrayList<>();
        ClientEntity clientEntity = clientRepository.findByEmail(email);
        List<DeskEntity> deskEntityList = clientEntity.getDesk();
        deskEntityList.forEach(x -> {
            deskDTOS.add(x.toDTO());
        });
        return deskDTOS;
    }


    @Transactional
    @Override
    public void addDesk(DeskDTO deskDTO, String email) {

        ClientEntity clientEntity = clientRepository.findByEmail(email);
        List<DeskEntity> deskEntityList = clientEntity.getDesk();
        DeskEntity deskEntity = DeskEntity.fromDTO(deskDTO);
        deskEntityList.add(deskEntity);
    }


    @Transactional
    @Override
    public void delDesk(Long id, String email) {
        if (deskRepository.findByIdDesk(id) == null || !deskRepository.findByIdDesk(id).getClients().contains(clientRepository.findByEmail(email))) {
            throw new EntityExistsException("Not found desk. Error");
        }
        else {
            DeskEntity deskDel = deskRepository.findByIdDesk(id);
            List<TasksEntity> tasksEntities = deskDel.getTasksEntities();
            ArchiveDesksEntity archiveDesksEntity = ArchiveDesksEntity.toArchDesk(deskDel);
            List<ArchiveTasksEntity> archiveTasksEntities = archiveDesksEntity.getTasksEntities();
            Set<ClientEntity> clientEntities = deskDel.getClients();
            Set<ClientEntity> clientEntitiesD = archiveDesksEntity.getClients();

            tasksEntities.forEach(x -> { archiveTasksEntities.add(x.toArch()); });

            tasksEntities.forEach(x -> { deskDel.getTasksEntities().remove(x); });

            archiveTasksEntities.forEach(x -> { x.setDeskEntityArch(archiveDesksEntity); });

            clientEntities.forEach(x -> {
                clientEntitiesD.add(x);
                if (x.getDesk().contains(deskDel)) {
                    x.getDesk().remove(deskDel);
                }
            });

            clientEntities.forEach(x -> {
                clientEntitiesD.add(x);
                if (x.getDesk().contains(deskDel)) {
                    deskDel.getClients().remove(x);
                }
            });

            clientEntitiesD.forEach(x -> { x.getDeskArch().add(archiveDesksEntity); });

            archiveDesksRepository.save(archiveDesksEntity);
            deskRepository.delete(deskDel);
        }
    }

    @Transactional
    @Override
    public void addClientForDesk(Long deskId, Long clientId, String email) {

        ClientEntity clientEntity = clientRepository.findByClientId(clientId);
        DeskEntity deskEntity = deskRepository.findByIdDesk(deskId);

        if (clientEntity.getDesk().contains(deskEntity)) {

            throw new EntityExistsException("User have this desk");

        } else if (!clientRepository.findByEmail(email).getDesk().contains(deskEntity)) {

            throw new EntityExistsException("This desk not create");

        } else {

            deskEntity.getClients().add(clientEntity);
            clientEntity.getDesk().add(deskEntity);
        }
    }

    @Transactional
    @Override
    public Long countDesksByClient(String email) {

        ClientEntity clientEntity = clientRepository.findByEmail(email);
        Long count = (long) clientEntity.getDesk().size();

        return count;
    }

    @Transactional
    @Override
    public List<DeskDTO> findAllDesksByClientsAndPage(String email, Pageable pageable) {

        List<DeskDTO> deskDTOS =  new ArrayList<>();
        List<DeskEntity> deskEntityList = deskRepository.findByClientsEmail(email, pageable);

        deskEntityList.forEach(x->{deskDTOS.add(x.toDTO());});

        return deskDTOS;
    }
}
