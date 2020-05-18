package com.gmail.glebgomenyuk.dao.repository;

import com.gmail.glebgomenyuk.dao.model.ClientEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dto.DeskDTO;
import org.hibernate.sql.Select;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.From;
import java.util.List;

public interface DeskRepository extends JpaRepository<DeskEntity, Long> {

    DeskEntity findByDeskName(String name);

    DeskEntity deleteByDeskName(String name);

    @Query("select t from DeskEntity t where t.id = :id")
    DeskEntity findByIdDesk(@Param("id") Long id);


    List<DeskEntity> findByClientsEmail(String email, Pageable pageable);

}
