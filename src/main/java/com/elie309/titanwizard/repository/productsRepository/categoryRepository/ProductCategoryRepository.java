package com.elie309.titanwizard.repository.productsRepository.categoryRepository;

import com.elie309.titanwizard.exceptions.JdbcErrorHandler;
import com.elie309.titanwizard.models.productsModels.categoriesModels.ProductCategory;
import com.elie309.titanwizard.repository.IRepository;
import com.elie309.titanwizard.utils.rowMapper.ProductCategoryRowMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class ProductCategoryRepository implements IRepository<ProductCategory> {


    private final JdbcTemplate jdbcTemplate;

    public ProductCategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<ProductCategory> findAll() {
        String sql = "SELECT * FROM product_category";
        return jdbcTemplate.query(sql, new ProductCategoryRowMapper());
    }

    @Override
    public ProductCategory findById(Long id) {
        try {

            String sql = "SELECT * FROM product_category WHERE product_category_id = ?";
            return jdbcTemplate.queryForObject(sql, new ProductCategoryRowMapper(), id);

        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while finding the object");
    }

    public List<ProductCategory> findByTitle(String title) {
        try {

            String sql = "SELECT * FROM product_category WHERE product_category LIKE ?";
            String searchTerm = "%" + title + "%";
            return jdbcTemplate.query(sql, new ProductCategoryRowMapper(), searchTerm);

        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while finding the object");

    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {

        try {

            String sql = "INSERT INTO product_category (product_category) VALUES (?)";
            int res = jdbcTemplate.update(sql, productCategory.getProductCategory());

            if(res == 1){
                return productCategory;
            }
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not saved");

    }

    @Override
    public void update(ProductCategory productCategory) {

        try {
            String sql = "UPDATE product_category SET product_category = ? WHERE product_category_id = ?";
            int res = jdbcTemplate.update(sql, productCategory.getProductCategory(), productCategory.getProductCategoryId());

            if (res != 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not updated");
            }
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM product_category WHERE product_category_id = ?";
            int res = jdbcTemplate.update(sql, id);
            if (res != 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not deleted");
            }
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }
    }
}
