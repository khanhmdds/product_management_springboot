package com.case_product_management.service.impl;

import com.case_product_management.model.Role;
import com.case_product_management.repository.IRoleRepository;
import com.case_product_management.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }
}
