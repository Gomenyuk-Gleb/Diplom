package com.gmail.glebgomenyuk.dao.repository;

import com.gmail.glebgomenyuk.dao.model.ArchiveTasksEntity;
import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArchiveTasksRepository extends JpaRepository<ArchiveTasksEntity, Long> {

    ArchiveTasksEntity findByTaskName(String name);

    List<ArchiveTasksEntity> findAllByDeskEntity(DeskEntity deskEntity, Pageable pageable);

    @Query("select t from ArchiveTasksEntity t where t.id = :id")
    ArchiveTasksEntity findByIdTask(@Param("id") Long id);

}
