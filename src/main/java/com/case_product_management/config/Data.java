package com.case_product_management.config;

import com.case_product_management.model.Role;
import com.case_product_management.model.User;
import com.case_product_management.repository.IRoleRepository;
import com.case_product_management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class Data implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        if (roleRepository.findByRoleName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        if (roleRepository.findByRoleName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }
        //admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setFullName("Admin");
            admin.setNumber("0862442710");
            HashSet<Role> roles = new HashSet<>();

            roles.add(roleRepository.findByRoleName("ROLE_ADMIN"));
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            admin.setRole(roles);
            userRepository.save(admin);
        }

        //member account
        if (userRepository.findByEmail("member@gmail.com") == null) {
            User member = new User();
            member.setEmail("member@gmail.com");
            member.setPassword(passwordEncoder.encode("123456"));
            HashSet<Role> roles = new HashSet<>();

            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            member.setRole(roles);
            userRepository.save(member);
        }
    }
}
