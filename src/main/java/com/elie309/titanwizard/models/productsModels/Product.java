package com.elie309.titanwizard.models.productsModels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Product {

    //#region Fields

    @JsonAlias("productId")
    @JsonProperty(value = "id")
    @JsonIgnore
    private Long productId;

    @JsonAlias("productTitle")
    @JsonProperty(value = "title")
    private String productTitle;

    @JsonAlias("productDescription")
    @JsonProperty(value = "description")
    private String productDescription;

    @JsonAlias("productSku")
    @JsonProperty(value = "sku")
    private String productSku;

    @JsonAlias("productCategoryId")
    @JsonProperty(value = "categoryId")
    private Long productCategoryId;

    @JsonAlias("productSubcategoryId")
    @JsonProperty(value = "subcategoryId")
    private Long productSubcategoryId;

    @JsonAlias("productCreatedAt")
    @JsonProperty(value ="createdAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp productCreatedAt;

    @JsonAlias("productUpdatedAt")
    @JsonProperty(value ="updatedAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp productUpdatedAt;

    //#endregion

    public Product() {}

    //#region Getters & Setters

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

    //#endregion


    //#region Methods

    /**
     * Return false if the product is valid and true if it invalid.
     * @return boolean
     */
    public boolean isNotValid() {
        // Check if any of the required fields is null
        return isEmptyOrNull(productTitle) ||
                isEmptyOrNull(productSku) ||
                productCategoryId == null ||
                productSubcategoryId == null;
    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    //#endregion


}
