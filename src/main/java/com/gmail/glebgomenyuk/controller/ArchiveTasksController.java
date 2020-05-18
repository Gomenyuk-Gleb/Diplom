package com.gmail.glebgomenyuk.controller;
import com.gmail.glebgomenyuk.dto.ArchiveTasksDTO;
import com.gmail.glebgomenyuk.service.ArchiveTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasksarch")
public class ArchiveTasksController {

    @Autowired
    ArchiveTasksService archiveTasksService;

    @GetMapping("/{desks}")
    public List<ArchiveTasksDTO> getAll(@PathVariable(value = "desks") String desksName, OAuth2AuthenticationToken aou){

        return archiveTasksService.findAll(desksName);
    }

    @PostMapping("/del")
    public void delTask(@RequestBody ArchiveTasksDTO archiveTasksDTO){
        archiveTasksService.delTasks(archiveTasksDTO);
    }

    @PostMapping("/reset")
    public void resTask(@RequestBody ArchiveTasksDTO archiveTasksDTO){
        archiveTasksService.returnTasks(archiveTasksDTO);
    }

}
