package com.gmail.glebgomenyuk.serviceImp;

import com.gmail.glebgomenyuk.dao.model.AdminTableEntity;
import com.gmail.glebgomenyuk.dao.repository.AdminTableRepository;
import com.gmail.glebgomenyuk.dto.AdminTableDTO;
import com.gmail.glebgomenyuk.service.AdminTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminTableServiceImp implements AdminTableService {

    @Autowired
    AdminTableRepository adminTableRepository;


    @Transactional
    @Override
    public void save(AdminTableDTO adminTableDTO) {
        adminTableRepository.save(AdminTableEntity.fromDTO(adminTableDTO));
    }

    @Transactional
    @Override
    public List<AdminTableDTO> getLocation(Pageable pagebabal) {
        List<AdminTableEntity> list = adminTableRepository.findAll(pagebabal).getContent();

        List<AdminTableDTO> res = new ArrayList<>();

        list.forEach(x -> {
            res.add(x.toDTO());
        });

        return res;
    }

}
