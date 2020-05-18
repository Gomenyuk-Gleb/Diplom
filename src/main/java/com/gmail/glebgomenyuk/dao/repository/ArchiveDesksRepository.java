package com.gmail.glebgomenyuk.dao.repository;

import com.gmail.glebgomenyuk.dao.model.ArchiveDesksEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dto.ArchiveDesksDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArchiveDesksRepository extends JpaRepository<ArchiveDesksEntity, Long> {

    ArchiveDesksEntity findByDeskName(String name);

    //List<ArchiveDesksEntity> findAllByC();

    List<ArchiveDesksEntity> findByClientsEmail(String email, Pageable pageable);

    @Query("select t from ArchiveDesksEntity t where t.id = :id")
    ArchiveDesksEntity findByIdDesk(@Param("id") Long id);

}
