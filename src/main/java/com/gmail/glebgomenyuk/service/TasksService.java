package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import com.gmail.glebgomenyuk.dao.model.enumforentity.StatusTasksEnum;
import com.gmail.glebgomenyuk.dto.PageCountDTO;
import com.gmail.glebgomenyuk.dto.TasksDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface  TasksService {
    public List<TasksDTO> findAllTasksByDesks(String deskID, String clientEmail, Pageable pageable);

    public TasksEntity load(String name);

    public TasksEntity update(TasksEntity tasksEntity);

    public void delete(String name);

    public TasksEntity save(TasksEntity tasksEntity);

    public void addTask(Long deskID, TasksDTO tasksDTO, String clietEmail);

    public void delTask(Long deskID, Long taskId, String clientEmail);

    public void setStatus(TasksDTO tasksDTO, Long deskID, String clientEmail);

    public Long countTasksByDescs(Long deskID, String clientEmail);
}
