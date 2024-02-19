package com.elie309.ecommerce.Models;

import java.sql.Timestamp;

public class Product {
    private Long productId;
    private String productTitle;
    private String productDescription;
    private String productSku;
    private Long productCategoryId;
    private Long productSubcategoryId;
    private Timestamp productCreatedAt;
    private Timestamp productUpdatedAt;


    public Product() {}


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductSubcategoryId() {
        return productSubcategoryId;
    }

    public void setProductSubcategoryId(Long productSubcategoryId) {
        this.productSubcategoryId = productSubcategoryId;
    }

    public Timestamp getProductCreatedAt() {
        return productCreatedAt;
    }

    public void setProductCreatedAt(Timestamp productCreatedAt) {
        this.productCreatedAt = productCreatedAt;
    }

    public Timestamp getProductUpdatedAt() {
        return productUpdatedAt;
    }

    public void setProductUpdatedAt(Timestamp productUpdatedAt) {
        this.productUpdatedAt = productUpdatedAt;
    }
}
