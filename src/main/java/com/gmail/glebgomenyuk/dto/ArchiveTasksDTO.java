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

    public ArchiveTasksDTO(String name) {
        this.name = name;
    }

    public static ArchiveTasksDTO of(String name){
        return new ArchiveTasksDTO(name);
    }

    @Override
    public String toString() {
        return "ArchiveTasksDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
