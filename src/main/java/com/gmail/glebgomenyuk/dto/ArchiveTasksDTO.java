package com.gmail.glebgomenyuk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@Getter
@Setter
public class ArchiveTasksDTO {
    private String name;
    private Long id;

    public ArchiveTasksDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public static ArchiveTasksDTO of(String name, Long id){
        return new ArchiveTasksDTO(name, id);
    }

    @Override
    public String toString() {
        return "ArchiveTasksDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
