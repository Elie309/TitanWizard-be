package com.elie309.ecommerce.Utils.RowMapper;


import com.elie309.ecommerce.Models.Product;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getLong("product_id"));
        product.setProductTitle(rs.getString("product_title"));
        product.setProductDescription(rs.getString("product_description"));
        product.setProductSku(rs.getString("product_sku"));
        product.setProductCategoryId(rs.getLong("product_category_id"));
        product.setProductSubcategoryId(rs.getLong("product_subcategory_id"));
        product.setProductCreatedAt(rs.getTimestamp("product_created_at"));
        product.setProductUpdatedAt(rs.getTimestamp("product_updated_at"));
        return product;
    }
}