package com.gmail.glebgomenyuk.serviceImp;

import com.gmail.glebgomenyuk.dao.model.ArchiveDesksEntity;
import com.gmail.glebgomenyuk.dao.model.ArchiveTasksEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import com.gmail.glebgomenyuk.dao.repository.ArchiveDesksRepository;
import com.gmail.glebgomenyuk.dao.repository.ArchiveTasksRepository;
import com.gmail.glebgomenyuk.dao.repository.ClientRepository;
import com.gmail.glebgomenyuk.dao.repository.DeskRepository;
import com.gmail.glebgomenyuk.dto.ArchiveTasksDTO;
import com.gmail.glebgomenyuk.service.ArchiveTasksService;
import com.gmail.glebgomenyuk.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArchiveTasksServiceImp implements ArchiveTasksService {

    @Autowired
    ArchiveDesksRepository archiveDesksRepository;

    @Autowired
    ArchiveTasksRepository archiveTasksRepository;

    @Autowired
    DeskRepository deskRepository;

    @Autowired
    ClientRepository clientRepository;


    @Transactional
    @Override
    public void returnTasks(ArchiveTasksDTO archiveTasksDTO, Long deskId, String clientEmail) {
        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskId))) {

            throw new EntityExistsException("User dont have this desk");

        } else {
            ArchiveTasksEntity archiveTasksEntity = archiveTasksRepository.findByIdTask(archiveTasksDTO.getId());
            DeskEntity deskEntity = archiveTasksEntity.getDeskEntity();
            TasksEntity tasksEntity = archiveTasksEntity.fromArch();
            deskEntity.getTasksEntities().add(tasksEntity);
            tasksEntity.setDeskEntity(deskEntity);
            deskEntity.getArchiveTasksEntities().remove(archiveTasksEntity);
            archiveTasksRepository.delete(archiveTasksEntity);
        }
    }

    @Transactional
    @Override
    public void delTasks(Long deskId, Long taskId, String clientEmail) {
        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskId))) {

            throw new EntityExistsException("User dont have this desk");

        } else {

            ArchiveTasksEntity archiveTasksEntity = archiveTasksRepository.findByIdTask(taskId);
            DeskEntity deskEntity = archiveTasksEntity.getDeskEntity();
            deskEntity.getArchiveTasksEntities().remove(archiveTasksEntity);

            archiveTasksRepository.delete(archiveTasksEntity);
        }
    }

    @Transactional
    @Override
    public List<ArchiveTasksDTO> findAllTasksByDesks(Long deskId, String clientEmail, Pageable pageable) {

        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskId)) &&
                !clientRepository.findByEmail(clientEmail).getDeskArch().contains(archiveDesksRepository.findByIdDesk(deskId))) {

            throw new EntityExistsException("User dont have this desk");

        } else {
            DeskEntity desksEntity = deskRepository.findByIdDesk(deskId);
            List<ArchiveTasksEntity> archiveTasksEntities = archiveTasksRepository.findAllByDeskEntity(desksEntity, pageable);
            List<ArchiveTasksDTO> archiveTasksDTOS = new ArrayList<>();

            archiveTasksEntities.forEach(x -> {
                archiveTasksDTOS.add(x.toDTO());
            });

            return archiveTasksDTOS;
        }
    }

    @Transactional
    @Override
    public Long countTasksByDesks(Long deskId, String clientEmail) throws NullPointerException {

        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskId)) &&
                !clientRepository.findByEmail(clientEmail).getDeskArch().contains(archiveDesksRepository.findByIdDesk(deskId))) {

            throw new EntityExistsException("User dont have this desk");

        } else {
            Long size = 0l;
            DeskEntity deskEntity = deskRepository.findByIdDesk(deskId);
            ArchiveDesksEntity archiveDesksEntity = archiveDesksRepository.findByIdDesk(deskId);

            if (deskEntity != null) {
                size += deskEntity.getArchiveTasksEntities().size();
            } else if (archiveDesksEntity != null) {
                size += archiveDesksEntity.getTasksEntities().size();
            }

            return size;
        }
    }
}
