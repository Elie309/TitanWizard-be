package com.elie309.titanwizard.models.productsModels.categoriesModels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ProductCategory {

    @JsonAlias("productSubcategoryId")
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long productCategoryId;

    @JsonAlias("productCategory")
    @JsonProperty(value = "category")
    private String productCategory;

    @JsonProperty(value = "createdAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp productCategoryCreatedAt;

    @JsonProperty(value = "updatedAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp productCategoryUpdatedAt;

    public ProductCategory() {
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Timestamp getProductCategoryCreatedAt() {
        return productCategoryCreatedAt;
    }

    public void setProductCategoryCreatedAt(Timestamp productCategoryCreatedAt) {
        this.productCategoryCreatedAt = productCategoryCreatedAt;
    }

    public Timestamp getProductCategoryUpdatedAt() {
        return productCategoryUpdatedAt;
    }

    public void setProductCategoryUpdatedAt(Timestamp productCategoryUpdatedAt) {
        this.productCategoryUpdatedAt = productCategoryUpdatedAt;
    }
}
