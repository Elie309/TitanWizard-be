package com.elie309.titanwizard.utils.rowMapper;

import com.elie309.titanwizard.models.productsModels.categoriesModels.ProductCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryRowMapper implements RowMapper<ProductCategory> {

    @Override
    public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(rs.getLong("product_category_id"));
        productCategory.setProductCategory(rs.getString("product_category"));
        productCategory.setProductCategoryCreatedAt(rs.getTimestamp("product_category_created_at"));
        productCategory.setProductCategoryUpdatedAt(rs.getTimestamp("product_category_updated_at"));
        return productCategory;
    }
}