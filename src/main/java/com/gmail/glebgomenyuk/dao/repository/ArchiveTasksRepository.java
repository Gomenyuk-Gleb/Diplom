package com.gmail.glebgomenyuk.dao.repository;

import com.gmail.glebgomenyuk.dao.model.ArchiveTasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveTasksRepository extends JpaRepository<ArchiveTasksEntity, Long> {

    ArchiveTasksEntity findByTaskName(String name);
}
