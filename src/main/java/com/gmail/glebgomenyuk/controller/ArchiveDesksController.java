package com.gmail.glebgomenyuk.controller;

import com.gmail.glebgomenyuk.dto.ArchiveDesksDTO;
import com.gmail.glebgomenyuk.dto.PageCountDTO;
import com.gmail.glebgomenyuk.dto.results.BadRequestResult;
import com.gmail.glebgomenyuk.dto.results.ResultDTO;
import com.gmail.glebgomenyuk.dto.results.SuccessResult;
import com.gmail.glebgomenyuk.service.ArchiveDesksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desksarch")
public class ArchiveDesksController {

    private static final int COUNT = 1;

    @Autowired
    ArchiveDesksService archiveDesksService;

    @GetMapping("count")
    public PageCountDTO countArchDesks() {

        String email = "gomenyukgleb@gmail.com";
        return new PageCountDTO(archiveDesksService.countArchDesks(email), COUNT);

    }

    @GetMapping
    public List<ArchiveDesksDTO> allArchDesks(@RequestParam(name = "page", required = false, defaultValue = "0") Integer pageCount) {

        String email = "gomenyukgleb@gmail.com";
        return archiveDesksService.findByClientsEmail(email, PageRequest.of(
                pageCount,
                COUNT,
                Sort.Direction.DESC,
                "id"

        ));
    }

    @PostMapping
    public ResponseEntity<ResultDTO> returnDesk(@RequestBody ArchiveDesksDTO archiveDesksDTO) {

        String clientEmail = "gomenyukgleb@gmail.com";

        archiveDesksService.returnDesks(archiveDesksDTO.getId(), clientEmail);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @DeleteMapping("/{deskid}")
    public ResponseEntity<ResultDTO> delDesk(@PathVariable(value = "deskid") Long deskId) {

        String email = "gomenyukgleb@gmail.com";
        archiveDesksService.delDesks(deskId, email);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {

        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }
}
