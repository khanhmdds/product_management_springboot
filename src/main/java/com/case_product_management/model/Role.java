package com.case_product_management.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private long role_id;

    private String role;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> user;


    public Role() {
    }

    public Role(long role_id, String role, Set<User> user) {
        this.role_id = role_id;
        this.role = role;
//        this.user = user;
    }

    public long getId() {
        return role_id;
    }

    public void setId(long role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public Set<User> getUser() {
//        return user;
//    }
//
//    public void setUser(Set<User> user) {
//        this.user = user;
//    }
}
