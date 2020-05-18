package com.gmail.glebgomenyuk.dao.repository;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findByEmail(String email);

    ClientEntity deleteByEmail(String email);

    List<DeskEntity> findByName(String name);

    @Query("select t from ClientEntity t where t.id = :id")
    ClientEntity findByClientId(@Param("id") Long id);

    boolean existsByEmail(String email);
}
