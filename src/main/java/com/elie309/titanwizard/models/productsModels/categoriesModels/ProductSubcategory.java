package com.elie309.titanwizard.models.productsModels.categoriesModels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ProductSubcategory {

    @JsonAlias("productSubcategoryId")
    @JsonProperty(value = "id")
    private Long productSubcategoryId;

    @JsonAlias("productCategoryId")
    @JsonProperty(value = "categoryId")
    private Long productCategoryId;

    @JsonAlias("productSubcategory")
    @JsonProperty(value = "subcategory")
    private String productSubcategory;

    @JsonProperty(value = "createdAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp productSubcategoryCreatedAt;

    @JsonProperty(value = "updatedAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp productSubcategoryUpdatedAt;

    public ProductSubcategory() {
    }

    public Long getProductSubcategoryId() {
        return productSubcategoryId;
    }

    public void setProductSubcategoryId(Long productSubcategoryId) {
        this.productSubcategoryId = productSubcategoryId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductSubcategory() {
        return productSubcategory;
    }

    public void setProductSubcategory(String productSubcategory) {
        this.productSubcategory = productSubcategory;
    }

    public Timestamp getProductSubcategoryCreatedAt() {
        return productSubcategoryCreatedAt;
    }

    public void setProductSubcategoryCreatedAt(Timestamp productSubcategoryCreatedAt) {
        this.productSubcategoryCreatedAt = productSubcategoryCreatedAt;
    }

    public Timestamp getProductSubcategoryUpdatedAt() {
        return productSubcategoryUpdatedAt;
    }

    public void setProductSubcategoryUpdatedAt(Timestamp productSubcategoryUpdatedAt) {
        this.productSubcategoryUpdatedAt = productSubcategoryUpdatedAt;
    }
}
