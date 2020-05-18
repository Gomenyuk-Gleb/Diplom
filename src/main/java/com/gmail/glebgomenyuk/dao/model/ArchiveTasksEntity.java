package com.gmail.glebgomenyuk.dao.model;

import com.gmail.glebgomenyuk.dao.model.enumforentity.StatusTasksEnum;
import com.gmail.glebgomenyuk.dto.ArchiveDesksDTO;
import com.gmail.glebgomenyuk.dto.ArchiveTasksDTO;
import com.gmail.glebgomenyuk.dto.TasksDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "archivetasksentity")
@NoArgsConstructor
@Getter
@Setter
public class ArchiveTasksEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String taskName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "deskEntityArch_id")
    private ArchiveDesksEntity deskEntityArch;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "deskEntity_id")
    private DeskEntity deskEntity;


    private StatusTasksEnum statusTask;

    public ArchiveTasksEntity(String name) {
        this.taskName = name;
    }

    public static ArchiveTasksEntity fromDTO(ArchiveTasksDTO archiveTasksEntity){ return new ArchiveTasksEntity(archiveTasksEntity.getName()); }

    public ArchiveTasksDTO toDTO(){
        return  ArchiveTasksDTO.of(this.getTaskName(), this.id);
    }


    public static ArchiveTasksEntity toArch(TasksEntity tasksEntity){
        return  new ArchiveTasksEntity(tasksEntity.getTaskName());
    }
    public TasksEntity fromArch(){
        return new TasksEntity(this.taskName);
    }

    @Override
    public String toString() {
        return "ArchiveTasksEntity{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", deskEntityArch=" + deskEntityArch +
                ", deskEntity=" + deskEntity +
                ", statusTask=" + statusTask +
                '}';
    }
}
