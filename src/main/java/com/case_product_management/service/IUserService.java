package com.case_product_management.service;

import com.case_product_management.dto.AccountDTO;
import com.case_product_management.model.Role;
import com.case_product_management.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface IUserService {
    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    User saveUserForMember(User user);

    User findById(long id);

    User updateUser(User user);

    void changePass(User user, String newPass);

    Page<User> getUserByRole(Set<Role> role, int page);

    List<User> getUserByRole(Set<Role> role);

    User saveUserForAdmin(AccountDTO dto);

    void deleteById(long id);
}
