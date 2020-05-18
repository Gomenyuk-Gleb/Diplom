package com.gmail.glebgomenyuk.dao.repository;

import com.gmail.glebgomenyuk.dao.model.DeskEntity;
import com.gmail.glebgomenyuk.dao.model.TasksEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksRepository extends JpaRepository<TasksEntity, Long> {

    TasksEntity findByTaskName(String name);

    TasksEntity deleteByTaskName(String name);

    List<TasksEntity> findAllByDeskEntity(DeskEntity deskEntity, Pageable pageable);

    @Query("select t from TasksEntity t where t.id = :id")
    TasksEntity findByIdTask(@Param("id") Long id);
}
