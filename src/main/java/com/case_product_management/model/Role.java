package com.case_product_management.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private long role_id;

    private String roleName;

    @ManyToMany(mappedBy = "role")
    private Set<User> user;

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Role() {
    }


    public long getId() {
        return role_id;
    }

    public void setId(long role_id) {
        this.role_id = role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }

//    public Set<User> getUser() {
//        return user;
//    }
//
//    public void setUser(Set<User> user) {
//        this.user = user;
//    }
    public Role(String roleName) {
        this.roleName = roleName;
    }
}
