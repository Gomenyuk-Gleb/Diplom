package com.gmail.glebgomenyuk.serviceImp;

import com.gmail.glebgomenyuk.dao.model.ArchiveDesksEntity;
import com.gmail.glebgomenyuk.dao.model.ArchiveTasksEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import com.gmail.glebgomenyuk.dao.repository.ArchiveDesksRepository;
import com.gmail.glebgomenyuk.dao.repository.ArchiveTasksRepository;
import com.gmail.glebgomenyuk.dao.repository.DeskRepository;
import com.gmail.glebgomenyuk.dto.ArchiveTasksDTO;
import com.gmail.glebgomenyuk.service.ArchiveTasksService;
import com.gmail.glebgomenyuk.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    @Override
    public void returnTasks(ArchiveTasksDTO archiveTasksDTO) {

        ArchiveTasksEntity archiveTasksEntity = archiveTasksRepository.findByTaskName(archiveTasksDTO.getName());
        DeskEntity deskEntity = archiveTasksEntity.getDeskEntity();
        TasksEntity tasksEntity = archiveTasksEntity.fromArch();
        deskEntity.getTasksEntities().add(tasksEntity);
        tasksEntity.setDeskEntity(deskEntity);
        deskEntity.getArchiveTasksEntities().remove(archiveTasksEntity);
        archiveTasksRepository.delete(archiveTasksEntity);

    }

    @Transactional
    @Override
    public void delTasks(ArchiveTasksDTO archiveTasksDTO) {
        ArchiveTasksEntity archiveTasksEntity = archiveTasksRepository.findByTaskName(archiveTasksDTO.getName());
        DeskEntity deskEntity = archiveTasksEntity.getDeskEntity();
        deskEntity.getArchiveTasksEntities().remove(archiveTasksEntity);

        archiveTasksRepository.delete(archiveTasksEntity);
    }

    @Transactional
    @Override
    public List<ArchiveTasksDTO> findAll(String desksName) {

        List<ArchiveTasksDTO> archiveTasksEntities = new ArrayList<>();
        DeskEntity archiveDesksEntity = deskRepository.findByDeskName(desksName);

        archiveDesksEntity.getArchiveTasksEntities().forEach(x->{
            archiveTasksEntities.add(x.toDTO());
        });

        return archiveTasksEntities;
    }
}
