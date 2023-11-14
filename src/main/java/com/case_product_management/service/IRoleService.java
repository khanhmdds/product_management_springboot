package com.case_product_management.service;

import com.case_product_management.model.Role;

import java.util.List;

public interface IRoleService {

    Role findByRole(String role);
    List<Role> findAllRole();
}
