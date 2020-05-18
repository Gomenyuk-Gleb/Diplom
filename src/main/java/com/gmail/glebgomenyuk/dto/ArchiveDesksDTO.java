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

    public ArchiveDesksDTO(String name) {
        this.name = name;
    }

    public static ArchiveDesksDTO of(String name){
        return new ArchiveDesksDTO(name);
    }

    @Override
    public String toString() {
        return "ArchiveDesksDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
