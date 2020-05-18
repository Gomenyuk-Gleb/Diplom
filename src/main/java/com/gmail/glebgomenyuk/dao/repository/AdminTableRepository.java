package com.gmail.glebgomenyuk.dao.repository;

import com.gmail.glebgomenyuk.dao.model.AdminTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminTableRepository extends JpaRepository <AdminTableEntity, Long> {
}
