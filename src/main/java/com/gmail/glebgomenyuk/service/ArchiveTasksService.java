package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dao.model.ArchiveDesksEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dto.ArchiveTasksDTO;
import com.gmail.glebgomenyuk.dto.TasksDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArchiveTasksService {

    public void returnTasks(ArchiveTasksDTO archiveTasksDTO, Long deskId,  String clientEmail);

    public void delTasks(Long deskID, Long taskId, String clientEmail);

    //public List<ArchiveTasksDTO> findAll(Long deskId, String clientEmail);

    public Long countTasksByDesks(Long deskID, String clientEmail);

    public List<ArchiveTasksDTO> findAllTasksByDesks(Long deskID, String clientEmail, Pageable pageable);


}
