package com.case_product_management.repository;

import com.case_product_management.model.Role;
import com.case_product_management.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Page<User> findByRole(Set<Role> vaiTro, Pageable of);

    List<User> findByRole(Set<Role> role);
}
