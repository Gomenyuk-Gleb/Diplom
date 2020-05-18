package com.gmail.glebgomenyuk.dao.repository;

import com.gmail.glebgomenyuk.dao.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

}
