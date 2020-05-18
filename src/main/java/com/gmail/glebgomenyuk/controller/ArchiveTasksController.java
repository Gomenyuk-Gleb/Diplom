package com.gmail.glebgomenyuk.controller;

import com.gmail.glebgomenyuk.dto.ArchiveTasksDTO;
import com.gmail.glebgomenyuk.dto.PageCountDTO;
import com.gmail.glebgomenyuk.dto.results.BadRequestResult;
import com.gmail.glebgomenyuk.dto.results.ResultDTO;
import com.gmail.glebgomenyuk.dto.results.SuccessResult;
import com.gmail.glebgomenyuk.service.ArchiveTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasksarch")
public class ArchiveTasksController {

    private static final int COUNT = 1;

    @Autowired
    ArchiveTasksService archiveTasksService;

    @GetMapping("/{desks}/count")
    public PageCountDTO countTasksByDesk(@PathVariable(value = "desks") Long desksId) {

        String clientEmail = "gomenyukgleb@gmail.com";

        return new PageCountDTO(archiveTasksService.countTasksByDesks(desksId, clientEmail), COUNT);
    }

    @GetMapping("/{desks}")
    public List<ArchiveTasksDTO> getAll(@PathVariable(value = "desks") Long deskId, @RequestParam(name = "page", required = false, defaultValue = "0") Integer pageCount) {

        String clientEmail = "gomenyukgleb@gmail.com";

        return archiveTasksService.findAllTasksByDesks(deskId, clientEmail, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id"));
    }

    @DeleteMapping
    public ResponseEntity<ResultDTO> delTask(@RequestParam(name = "desk") Long idDask, @RequestParam(name = "task") Long idTask) {

        String clientEmail = "gomenyukgleb@gmail.com";

        archiveTasksService.delTasks(idDask, idTask, clientEmail);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);

    }

    @PostMapping("/{desks}")
    public ResponseEntity<ResultDTO> resTask(@RequestBody ArchiveTasksDTO archiveTasksDTO, @PathVariable(value = "desks") Long deskId) {

        String clientEmail = "gomenyukgleb@gmail.com";

        archiveTasksService.returnTasks(archiveTasksDTO, deskId, clientEmail);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {
        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }

}
