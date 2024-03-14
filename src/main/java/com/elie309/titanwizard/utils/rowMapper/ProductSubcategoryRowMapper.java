package com.elie309.titanwizard.utils.rowMapper;

import com.elie309.titanwizard.models.productsModels.categoriesModels.ProductSubcategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSubcategoryRowMapper implements RowMapper<ProductSubcategory> {

    @Override
    public ProductSubcategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductSubcategory productSubcategory = new ProductSubcategory();
        productSubcategory.setProductSubcategoryId(rs.getLong("product_subcategory_id"));
        productSubcategory.setProductCategoryId(rs.getLong("product_category_id"));
        productSubcategory.setProductSubcategory(rs.getString("product_subcategory"));
        productSubcategory.setProductSubcategoryCreatedAt(rs.getTimestamp("product_subcategory_created_at"));
        productSubcategory.setProductSubcategoryUpdatedAt(rs.getTimestamp("product_subcategory_updated_at"));
        return productSubcategory;
    }
}