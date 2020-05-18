package com.gmail.glebgomenyuk.serviceImp;

import com.gmail.glebgomenyuk.dao.model.*;
import com.gmail.glebgomenyuk.dao.model.enumforentity.StatusTasksEnum;
import com.gmail.glebgomenyuk.dao.repository.ArchiveTasksRepository;
import com.gmail.glebgomenyuk.dao.repository.ClientRepository;
import com.gmail.glebgomenyuk.dao.repository.DeskRepository;
import com.gmail.glebgomenyuk.dao.repository.TasksRepository;
import com.gmail.glebgomenyuk.dto.PageCountDTO;
import com.gmail.glebgomenyuk.dto.TasksDTO;
import com.gmail.glebgomenyuk.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.crypto.Des;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TasksServiceImp implements TasksService {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    DeskRepository deskRepository;

    @Autowired
    ArchiveTasksRepository archiveTasksRepository;

    @Autowired
    ClientRepository clientRepository;


    @Transactional
    @Override
    public List<TasksDTO> findAllTasksByDesks(Long deskID, String clientEmail, Pageable pageable) {

        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskID))) {

            throw new EntityExistsException("User dont have this desk");

        } else {

            DeskEntity deskEntity = deskRepository.findByIdDesk(deskID);

            List<TasksEntity> tasksEntities = tasksRepository.findAllByDeskEntity(deskEntity, pageable);

            List<TasksDTO> tasksDTO = new ArrayList<>();
            tasksEntities.forEach(x -> {
                tasksDTO.add(x.toDTO());
            });
            return tasksDTO;

        }
    }

    @Transactional
    @Override
    public TasksEntity load(String name) {
        return tasksRepository.findByTaskName(name);

    }

    @Transactional
    @Override
    public TasksEntity update(TasksEntity tasksEntity) {
        if (load(tasksEntity.getTaskName()) == null) {
            throw new EntityExistsException("Failed to save, course already exists, id:" + tasksEntity.getTaskName());
        }
        TasksEntity tasksEntityUpdate = tasksRepository.save(tasksEntity);

        return tasksRepository.save(tasksEntityUpdate);
    }



    @Transactional
    @Override
    public void addTask(Long deskID, TasksDTO tasksDTO, String clientEmail) {

        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskID))) {

            throw new EntityExistsException("User dont have this desk");

        } else {

            DeskEntity deskEntity = deskRepository.findByIdDesk(deskID);
            List<TasksEntity> tasksEntities = deskEntity.getTasksEntities();
            TasksEntity tasksEntity = TasksEntity.fromDTO(tasksDTO);
            tasksEntities.add(tasksEntity);
            tasksEntity.setDeskEntity(deskEntity);
        }
    }

    @Transactional
    @Override
    public void delTask(Long deskID, Long taskId, String clientEmail) {

        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskID))) {

            throw new EntityExistsException("User dont have this desk");

        } else {

            TasksEntity tasksEntity = tasksRepository.findByIdTask(taskId);
            DeskEntity deskEntity = deskRepository.findByIdDesk(deskID);
            ArchiveTasksEntity archiveTasksEntity = tasksEntity.toArch();

            archiveTasksEntity.setDeskEntity(deskEntity);
            deskEntity.getArchiveTasksEntities().add(archiveTasksEntity);

            archiveTasksRepository.save(archiveTasksEntity);
            tasksRepository.delete(tasksEntity);
        }

    }

    @Transactional
    @Override
    public void setStatus(TasksDTO tasksDTO, Long deskID, String clientEmail) {

        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskID))) {

            throw new EntityExistsException("User dont have this desk");

        } else {

            TasksEntity tasksEntity = tasksRepository.findByIdTask(tasksDTO.getIdTask());
            tasksEntity.setStatusTask(tasksDTO.getStatusTask());
        }
    }

    @Transactional
    @Override
    public Long countTasksByDescs(Long deskID, String clientEmail) {
        if (!clientRepository.findByEmail(clientEmail).getDesk().contains(deskRepository.findByIdDesk(deskID))) {

            throw new EntityExistsException("User dont have this desk");

        } else {

            DeskEntity deskEntity = deskRepository.findByIdDesk(deskID);
            List<TasksEntity> tasksEntities = deskEntity.getTasksEntities();
            Long size = (long) tasksEntities.size();

            return size;
        }
    }
}
