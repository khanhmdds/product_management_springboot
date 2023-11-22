package com.case_product_management.model;

public class CartItem {
    private Long productId;
    private String name;
    private Long price;
    private int qty = 1;
    private String image;
    private Product product;
    public CartItem() {
    }
    public CartItem(Long productId, String name, Long price, int qty, String image, Product product){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.image = image;
        this.product = product;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductPrice() {
        return product != null ? product.getPrice() : (long) 0;
    }
}
