package com.gmail.glebgomenyuk.dao.model;

import com.gmail.glebgomenyuk.dto.DeskDTO;
import lombok.*;
import sun.security.krb5.internal.crypto.Des;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "deskEntity")
public class DeskEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String deskName;

    @ManyToMany(mappedBy = "desk", cascade = CascadeType.DETACH)
    private Set<ClientEntity> clients = new HashSet<>();

    @OneToMany(mappedBy = "deskEntity", cascade = CascadeType.ALL)
    private List<TasksEntity> tasksEntities = new ArrayList<>();

    @OneToMany(mappedBy = "deskEntity", cascade = CascadeType.ALL)
    private List<ArchiveTasksEntity> archiveTasksEntities = new ArrayList<>();


    public DeskEntity(String name) {
        this.deskName = name;
    }

    public void addTask(TasksEntity task) {
        task.setDeskEntity(this);
        tasksEntities.add(task);
    }

    public static DeskEntity fromDTO(DeskDTO deskDTO){
        return new DeskEntity(deskDTO.getName());
    }

    public DeskDTO toDTO(){
        return DeskDTO.of(this.deskName, this.id);
    }

    public static DeskEntity fromArch(ArchiveDesksEntity archiveDesksEntity){return  new DeskEntity(archiveDesksEntity.getDeskName());}

    @Override
    public String toString() {
        return "DeskEntity{" +
                "id=" + id +
                ", deskName='" + deskName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeskEntity that = (DeskEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deskName, that.deskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deskName);
    }
}
