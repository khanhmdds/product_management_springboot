package com.case_product_management.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long product_id;

    @NotEmpty
    private String productName;

    @NotEmpty
    private long price;

    @NotEmpty
    private String description;

//    @JsonIgnore
    @NotEmpty
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "orderDetail_id")
    private List<OrderDetails> orderDetail;

    public List<OrderDetails> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetails> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Product() {
    }

    public Product(long product_id, String productName, long price, String description, String image, Category category) {
        this.product_id = product_id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category = category;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
