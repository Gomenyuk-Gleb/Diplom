package com.gmail.glebgomenyuk.dao.model;

import com.gmail.glebgomenyuk.dto.ArchiveDesksDTO;
import com.gmail.glebgomenyuk.dto.DeskDTO;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "deskEntityArch")
public class ArchiveDesksEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String deskName;

    @ManyToMany(mappedBy = "deskArch")
    private Set<ClientEntity> clients = new HashSet<>();

    @OneToMany(mappedBy = "deskEntityArch", cascade = CascadeType.ALL)
    private List<ArchiveTasksEntity> tasksEntities = new ArrayList<>();

    public static ArchiveDesksEntity toArchDesk(DeskEntity deskEntity){
        return new ArchiveDesksEntity(deskEntity.getDeskName());
    }


    public DeskEntity toArchDesk(){
        return new DeskEntity(this.getDeskName());
    }

    public ArchiveDesksEntity(String name) {
        this.deskName = name;
    }

    public static ArchiveDesksEntity fromDTO(ArchiveDesksDTO archiveDesksDTO){ return new ArchiveDesksEntity(archiveDesksDTO.getName()); }


    public ArchiveDesksDTO toDTO(){
        return  ArchiveDesksDTO.of(this.deskName);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiveDesksEntity that = (ArchiveDesksEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deskName, that.deskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deskName);
    }
}
