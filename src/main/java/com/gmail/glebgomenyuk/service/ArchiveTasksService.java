package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dto.ArchiveTasksDTO;

import java.util.List;

public interface ArchiveTasksService {
    public void returnTasks(ArchiveTasksDTO archiveTasksDTO);

    public void delTasks(ArchiveTasksDTO archiveTasksDTO);

    public List<ArchiveTasksDTO> findAll(String desksName);

}
