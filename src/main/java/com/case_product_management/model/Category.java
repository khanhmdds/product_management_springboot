package com.case_product_management.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;

    @NotEmpty
    @Size(min = 2)
    private String name;

    public Category() {
    }

    public Category(long category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Category{" +
                "id=" + category_id +
                ", name='" + name + '\'' +
                '}';
    }
}