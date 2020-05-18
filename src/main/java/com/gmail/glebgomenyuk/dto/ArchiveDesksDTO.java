package com.gmail.glebgomenyuk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@Getter
@Setter
public class ArchiveDesksDTO {

    private String name;
    private Long id;

    public ArchiveDesksDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public static ArchiveDesksDTO of(String name, Long id){
        return new ArchiveDesksDTO(name, id);
    }

    @Override
    public String toString() {
        return "ArchiveDesksDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
