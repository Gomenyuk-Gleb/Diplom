package com.gmail.glebgomenyuk.dto;

import com.gmail.glebgomenyuk.dao.model.enumforentity.StatusTasksEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TasksDTO {

    private String tasksName;
    private Long idTask;
    private StatusTasksEnum statusTask;


    public TasksDTO(String tasksName, Long idTask, StatusTasksEnum statusTask) {
        this.tasksName = tasksName;
        this.idTask = idTask;
        this.statusTask = statusTask;
    }

    public static TasksDTO of(String name, Long idTask, StatusTasksEnum statusTask){
        return new TasksDTO(name, idTask, statusTask);
    }
}
