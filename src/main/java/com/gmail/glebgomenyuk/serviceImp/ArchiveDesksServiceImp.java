package com.gmail.glebgomenyuk.serviceImp;

import com.gmail.glebgomenyuk.dao.model.*;
import com.gmail.glebgomenyuk.dao.repository.ArchiveDesksRepository;
import com.gmail.glebgomenyuk.dao.repository.ClientRepository;
import com.gmail.glebgomenyuk.dao.repository.DeskRepository;
import com.gmail.glebgomenyuk.dto.ArchiveDesksDTO;
import com.gmail.glebgomenyuk.service.ArchiveDesksService;
import com.gmail.glebgomenyuk.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ArchiveDesksServiceImp implements ArchiveDesksService {

    @Autowired
    ArchiveDesksRepository archiveDesksRepository;

    @Autowired
    DeskRepository deskRepository;

    @Autowired
    ClientRepository clientRepository;

    @Transactional
    @Override
    public void returnDesks(Long deskId, String clientEmail) {

        if (archiveDesksRepository.findByIdDesk(deskId) == null || !archiveDesksRepository.findByIdDesk(deskId).getClients().contains(clientRepository.findByEmail(clientEmail))) {
            throw new EntityExistsException("Not found desk. Error");
        } else {

            ArchiveDesksEntity archiveDesksEntity = archiveDesksRepository.findByIdDesk(deskId);
            List<ArchiveTasksEntity> archiveTasksEntity = archiveDesksEntity.getTasksEntities();
            Set<ClientEntity> clientEntities = archiveDesksEntity.getClients();
            DeskEntity deskEntity = archiveDesksEntity.toArchDesk();
            List<TasksEntity> tasksEntities = deskEntity.getTasksEntities();

            clientEntities.forEach(x -> {
                x.getDesk().add(deskEntity);
                x.getDeskArch().remove(archiveDesksEntity);
            });

            archiveTasksEntity.forEach(x -> {
                tasksEntities.add(x.fromArch());
            });

            tasksEntities.forEach(x -> {
                x.setDeskEntity(deskEntity);
            });

            deskRepository.save(deskEntity);
            archiveDesksRepository.delete(archiveDesksEntity);
        }
    }

    @Transactional
    @Override
    public void delDesks(Long deskId, String email) {

        if (archiveDesksRepository.findByIdDesk(deskId) == null || !archiveDesksRepository.findByIdDesk(deskId).getClients().contains(clientRepository.findByEmail(email))) {
            throw new EntityExistsException("Not found desk. Error");
        } else {
            ArchiveDesksEntity archiveDesksEntity = archiveDesksRepository.findByIdDesk(deskId);
            Set<ClientEntity> clientEntities = archiveDesksEntity.getClients();
            clientEntities.forEach(x -> {
                x.getDeskArch().remove(archiveDesksEntity);
            });

            archiveDesksRepository.delete(archiveDesksEntity);
        }

    }

    @Transactional
    @Override
    public List<ArchiveDesksDTO> findByClientsEmail(String email, Pageable pageable) {

        List<ArchiveDesksDTO> archiveDesksEntities = new ArrayList<>();

        List<ArchiveDesksEntity> archiveDesksDTOS = archiveDesksRepository.findByClientsEmail(email, pageable);
        archiveDesksDTOS.forEach(x -> {
            archiveDesksEntities.add(x.toDTO());
        });

        return archiveDesksEntities;
    }

    @Transactional
    @Override
    public Long countArchDesks(String clientEmail) {

        ClientEntity clientEntity = clientRepository.findByEmail(clientEmail);
        List<ArchiveDesksEntity> archiveDesksEntities = clientEntity.getDeskArch();
        Long size = (long) archiveDesksEntities.size();
        return size;
    }
}
