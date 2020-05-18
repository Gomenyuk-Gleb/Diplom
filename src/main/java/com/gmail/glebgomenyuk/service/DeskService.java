package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import com.gmail.glebgomenyuk.dto.ClientDTO;
import com.gmail.glebgomenyuk.dto.DeskDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeskService {

    public List<DeskDTO> findAllDesksByClients(String email);

    public void addDesk(DeskDTO deskDTO, String email);

    public void delDesk(Long id, String email);

    public void addClientForDesk(Long deskId, Long clientId, String email);

    public Long countDesksByClient(String email);

    public List<DeskDTO> findAllDesksByClientsAndPage(String email, Pageable pageable);

}
