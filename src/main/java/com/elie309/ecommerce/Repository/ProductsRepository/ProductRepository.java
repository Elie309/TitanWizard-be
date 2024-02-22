package com.elie309.ecommerce.Repository.ProductsRepository;

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
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public Product findById(Long productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
         List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper(), productId);

         if(products.isEmpty()){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found");
         }
         return products.get(0);

    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO product (product_title, product_description, product_sku, " +
                "product_category_id, product_subcategory_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        int res = jdbcTemplate.update(sql, product.getProductTitle(), product.getProductDescription(),
                product.getProductSku(), product.getProductCategoryId(),
                product.getProductSubcategoryId());

        if(res == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not saved");
        }

        return product;

    }

    @Override
    public Product update(Product product) {
        String sql = "UPDATE product SET product_title = ?, product_description = ?, product_sku = ?, " +
                "product_category_id = ?, product_subcategory_id = ? " +
                "WHERE product_id = ?";
        int res = jdbcTemplate.update(sql, product.getProductTitle(), product.getProductDescription(),
                product.getProductSku(), product.getProductCategoryId(), product.getProductSubcategoryId(),
                product.getProductId());

        if(res == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not updated");
        }

        return product;


    }

    @Override
    public void delete(Long productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        int res = jdbcTemplate.update(sql, productId);

        if(res == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found");
        }
    }

}
