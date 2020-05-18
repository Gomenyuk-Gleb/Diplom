package com.gmail.glebgomenyuk.dao.model;

import com.gmail.glebgomenyuk.dao.model.enumforentity.StatusTasksEnum;
import com.gmail.glebgomenyuk.dto.TasksDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(name = "Tasks")
public class TasksEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String taskName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "deskEntity_id")
    private DeskEntity deskEntity;

    private StatusTasksEnum statusTask;

    public TasksEntity(String name) {
        this.taskName = name;
    }

    public TasksDTO toDTO(){
        return TasksDTO.of(this.taskName, this.id, this.statusTask);
    }
    public static TasksEntity fromDTO(TasksDTO tasksDTO){
        return new TasksEntity(tasksDTO.getTasksName());
    }
    public ArchiveTasksEntity toArch(){
        return new ArchiveTasksEntity(this.taskName);
    }

}
