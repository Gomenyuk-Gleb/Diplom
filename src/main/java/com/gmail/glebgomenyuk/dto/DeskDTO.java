package com.gmail.glebgomenyuk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Builder
@NoArgsConstructor
@Getter
@Setter
public class DeskDTO {
    private String name;
    private Long id;

    public DeskDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public static DeskDTO of(String name, Long id){
        return new DeskDTO(name, id);
    }
}
