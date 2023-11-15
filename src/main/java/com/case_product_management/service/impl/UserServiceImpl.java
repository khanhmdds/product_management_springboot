package com.case_product_management.service.impl;

import com.case_product_management.dto.AccountDTO;
import com.case_product_management.model.Role;
import com.case_product_management.model.User;
import com.case_product_management.repository.IRoleRepository;
import com.case_product_management.repository.IUserRepository;
import com.case_product_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByConfirmationToken(String confirmationToken) {
        return null;
    }

    @Override
    public User saveUserForMember(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> setRole = new HashSet<>();

        setRole.add(roleRepository.findByRoleName("ROLE_MEMBER"));
        user.setRole(setRole);
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void changePass(User user, String newPass) {
        user.setPassword(bCryptPasswordEncoder.encode(newPass));
        userRepository.save(user);
    }

    @Override
    public Page<User> getUserByRole(Set<Role> role, int page) {
        return userRepository.findByRole(role, PageRequest.of(page - 1, 6));
    }

    @Override
    public List<User> getUserByRole(Set<Role> role) {
        return userRepository.findByRole(role);
    }

    @Override
    public User saveUserForAdmin(AccountDTO dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setNumber(dto.getNumber());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        Set<Role> role  = new HashSet<>();
        role.add(roleRepository.findByRoleName(dto.getRoleName()));
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }


}
