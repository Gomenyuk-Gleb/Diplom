package com.gmail.glebgomenyuk.controller;

import com.gmail.glebgomenyuk.dao.repository.ClientRepository;
import com.gmail.glebgomenyuk.dto.PageCountDTO;
import com.gmail.glebgomenyuk.dto.TasksDTO;
import com.gmail.glebgomenyuk.dto.results.BadRequestResult;
import com.gmail.glebgomenyuk.dto.results.ResultDTO;
import com.gmail.glebgomenyuk.dto.results.SuccessResult;
import com.gmail.glebgomenyuk.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TasksController {

    private static final int COUNT_TASKS = 12;

    @Autowired
    TasksService tasksService;

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/{desks}/count")
    public PageCountDTO countTasksByDesk(@PathVariable(value = "desks") Long desksId) {

        String clientEmail = "gomenyukgleb@gmail.com";

        return new PageCountDTO(tasksService.countTasksByDescs(desksId, clientEmail), COUNT_TASKS);
    }

    @GetMapping
    public List<TasksDTO> getAllTasksByDesk(@RequestParam(value = "desks") Long desksId, @RequestParam(name = "page", required = false, defaultValue = "0") Integer pageCount) {

        String clientEmail = "gomenyukgleb@gmail.com";

        return tasksService.findAllTasksByDesks(desksId, clientEmail, PageRequest.of(pageCount, COUNT_TASKS, Sort.Direction.DESC, "id"));
    }

    @PostMapping
    public ResponseEntity<ResultDTO> addDesk(@RequestBody TasksDTO tasksDTO, @RequestParam(name = "desks") Long idDesk) {

        String clientEmail = "gomenyukgleb@gmail.com";

        tasksService.addTask(idDesk, tasksDTO, clientEmail);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResultDTO> delDesk(@RequestParam(name = "desks") Long desksId, @RequestParam (name = "task") Long idTask) {

        String clientEmail = "gomenyukgleb@gmail.com";

        tasksService.delTask(desksId, idTask, clientEmail);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResultDTO> setStatys(@RequestParam(name = "desks") Long desksId, @RequestBody TasksDTO tasksDTO) {

        String clientEmail = "gomenyukgleb@gmail.com";

        tasksService.setStatus(tasksDTO, desksId, clientEmail);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {

        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }
}
