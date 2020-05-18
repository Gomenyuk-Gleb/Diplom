package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dao.model.ArchiveDesksEntity;
import com.gmail.glebgomenyuk.dao.model.ArchiveTasksEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dto.ArchiveDesksDTO;
import com.gmail.glebgomenyuk.dto.DeskDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArchiveDesksService {

    public void returnDesks(String name);

    public void delDesks(Long deskId, String email);

   // public List<ArchiveDesksDTO> findAll();

    public Long countArchDesks(String emailC);

    List<ArchiveDesksDTO> findByClientsEmail(String email, Pageable pageable);

}
