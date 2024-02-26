package com.elie309.ecommerce.Repository.ProductsRepository;

import com.elie309.ecommerce.Exceptions.JdbcErrorHandler;
import com.elie309.ecommerce.Models.ProductsModels.Product;
import com.elie309.ecommerce.Repository.IRepository;
import com.elie309.ecommerce.Utils.RowMapper.ProductRowMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class ProductRepository implements IRepository<Product> {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        try {

            return jdbcTemplate.query(sql, new ProductRowMapper());

        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");

    }

    @Override
    public Product findById(Long productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        try {

            return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), productId);

        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO product (product_title, product_description, product_sku, " + "product_category_id, product_subcategory_id) " + "VALUES (?, ?, ?, ?, ?)";

        try {

            int res = jdbcTemplate.update(sql, product.getProductTitle(), product.getProductDescription(), product.getProductSku(), product.getProductCategoryId(), product.getProductSubcategoryId());

            if (res != 0) {
                return product;
            }

        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not saved");
    }

    @Override
    public Product update(Product product) {

        String sql = "UPDATE product SET product_title = ?, product_description = ?, product_sku = ?, " + "product_category_id = ?, product_subcategory_id = ? " + "WHERE product_id = ?";
        try {

            int res = jdbcTemplate.update(sql, product.getProductTitle(), product.getProductDescription(), product.getProductSku(), product.getProductCategoryId(), product.getProductSubcategoryId(), product.getProductId());

            if (res != 0) {
                return product;
            }

        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not updated");

    }

    @Override
    public void delete(Long productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";

        try {

            int res = jdbcTemplate.update(sql, productId);

            if (res == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found");
            }

        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }
    }

}
