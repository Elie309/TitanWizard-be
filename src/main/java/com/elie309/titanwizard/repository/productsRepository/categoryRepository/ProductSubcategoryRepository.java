package com.elie309.titanwizard.repository.productsRepository.categoryRepository;

import com.elie309.titanwizard.models.productsModels.categoriesModels.ProductSubcategory;
import com.elie309.titanwizard.repository.IRepository;
import com.elie309.titanwizard.utils.rowMapper.ProductSubcategoryRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductSubcategoryRepository implements IRepository<ProductSubcategory> {

    private final JdbcTemplate jdbcTemplate;

    public ProductSubcategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductSubcategory> findAll() {
        String sql = "SELECT * FROM product_subcategory";
        return jdbcTemplate.query(sql, new ProductSubcategoryRowMapper());
    }

    @Override
    public ProductSubcategory findById(Long id) {
        String sql = "SELECT * FROM product_subcategory WHERE product_subcategory_id = ?";
        return jdbcTemplate.queryForObject(sql,new ProductSubcategoryRowMapper(), id);
    }

    public List<ProductSubcategory> findByCategoryId(Long categoryId) {
        String sql = "SELECT * FROM product_subcategory WHERE product_category_id = ?";
        return jdbcTemplate.query(sql, new ProductSubcategoryRowMapper(), categoryId);
    }

    @Override
    public ProductSubcategory save(ProductSubcategory productSubcategory) {
        String sql = "INSERT INTO product_subcategory (product_category_id, product_subcategory) VALUES (?, ?)";
        jdbcTemplate.update(sql, productSubcategory.getProductCategoryId(), productSubcategory.getProductSubcategory());
        return productSubcategory;
    }

    @Override
    public void update(ProductSubcategory productSubcategory) {
        String sql = "UPDATE product_subcategory SET product_category_id = ?, product_subcategory = ? WHERE product_subcategory_id = ?";
        jdbcTemplate.update(sql, productSubcategory.getProductCategoryId(), productSubcategory.getProductSubcategory(), productSubcategory.getProductSubcategoryId());

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product_subcategory WHERE product_subcategory_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
