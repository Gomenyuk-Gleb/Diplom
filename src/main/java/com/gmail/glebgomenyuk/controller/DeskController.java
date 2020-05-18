package com.gmail.glebgomenyuk.controller;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.repository.ClientRepository;
import com.gmail.glebgomenyuk.dao.repository.DeskRepository;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.dto.DeskDTO;
import com.gmail.glebgomenyuk.dto.PageCountDTO;
import com.gmail.glebgomenyuk.dto.results.BadRequestResult;
import com.gmail.glebgomenyuk.dto.results.ResultDTO;
import com.gmail.glebgomenyuk.dto.results.SuccessResult;
import com.gmail.glebgomenyuk.service.ClientService;
import com.gmail.glebgomenyuk.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.endpoint.OAuth2ClientCredentialsGrantRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desks")
public class DeskController {

    private static final int COUNT = 6;

    @Autowired
    DeskService deskService;

    @Autowired
    ClientService clientService;

    @GetMapping("count")
    public PageCountDTO countDesks() {

        String email = "gomenyukgleb@gmail.com";

        return new PageCountDTO(deskService.countDesksByClient(email), COUNT);
    }

    @GetMapping
    public List<DeskDTO> allDesksByClientAndPage(@RequestParam(name = "page", required = false, defaultValue = "0") Integer pageCount) {

        String email = "gomenyukgleb@gmail.com";
        return deskService.findAllDesksByClientsAndPage(email, PageRequest.of(
                pageCount,
                COUNT,
                Sort.Direction.DESC,
                "id"
        ));
    }

    @PostMapping
    public ResponseEntity<ResultDTO> addDesk(@RequestBody DeskDTO desk) {

        String email = "gomenyukgleb@gmail.com";
        deskService.addDesk(desk, email);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{deskId}")
    public ResponseEntity<ResultDTO> delDesk(@PathVariable Long deskId, String email) {

        email = "gomenyukgleb@gmail.com";
        deskService.delDesk(deskId, email);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @PutMapping(value = "/{deskId}")
    public ResponseEntity<ResultDTO> addClientForDesk(@RequestBody ClientDTO clientDTO, @PathVariable Long deskId) {

        String email = "gomenyukgleb@gmail.com";
        Long clientId = clientDTO.getId();
        deskService.addClientForDesk(deskId, clientId, email);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {
        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }
}
