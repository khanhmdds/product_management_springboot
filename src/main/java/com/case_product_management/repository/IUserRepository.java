package com.case_product_management.repository;


import com.case_product_management.model.Admin;
import com.case_product_management.model.User;
import com.case_product_management.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository  extends JpaRepository<User, Long> {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.case_product_management.model.dto.UserDTO (" +
            "u.id, " +
            "u.username" +
            ") " +
            "FROM User u " +
            "WHERE u.username = ?1"
    )
    Optional<UserDTO> findUserDTOByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT " +
            "u.id, " +
            "u.username, " +
            "u.password, " +
            "u.role " +
            "FROM User AS u " +
            "WHERE u.username = :username " +
            "AND u.id <> :id"
    )
    Optional<Admin> findByUsernameAndIdIsNot(@Param("username") String username, @Param("id") Long id);
}
