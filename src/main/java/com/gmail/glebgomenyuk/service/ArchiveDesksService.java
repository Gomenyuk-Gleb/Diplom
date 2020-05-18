package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dao.model.ArchiveDesksEntity;
import com.gmail.glebgomenyuk.dao.model.ArchiveTasksEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dto.ArchiveDesksDTO;
import com.gmail.glebgomenyuk.dto.DeskDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArchiveDesksService {

    public void returnDesks(Long deskId, String clientEmail);

    public void delDesks(Long deskId, String clientEmail);

   // public List<ArchiveDesksDTO> findAll();

    public Long countArchDesks(String clientEmail);

    List<ArchiveDesksDTO> findByClientsEmail(String clientEmail, Pageable pageable);

}
