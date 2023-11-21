package com.case_product_management.model;

public class CartItem {
    private Long productId;
    private String name;
    private double price;
    private int qty = 1;
    private String image;

    public CartItem() {
    }
    public CartItem(Long productId, String name, double price, int qty, String image){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.image = image;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
