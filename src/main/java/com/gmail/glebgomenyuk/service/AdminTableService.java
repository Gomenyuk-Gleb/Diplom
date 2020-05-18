package com.gmail.glebgomenyuk.service;

import com.gmail.glebgomenyuk.dto.AdminTableDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminTableService {

    void save(AdminTableDTO adminTableDTO);

    List<AdminTableDTO> getLocation(Pageable pagebabal);

}
