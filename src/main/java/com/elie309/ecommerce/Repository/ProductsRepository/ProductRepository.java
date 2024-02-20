package com.elie309.ecommerce.Repository.ProductsRepository;

import com.elie309.ecommerce.Models.ProductsModels.Product;
import com.elie309.ecommerce.Repository.IRepository;
import com.elie309.ecommerce.Repository.RepositoryUtils;
import com.elie309.ecommerce.Utils.Response;
import com.elie309.ecommerce.Utils.RowMapper.ProductRowMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements IRepository<Product> {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseEntity<List<Product>> findAll() {
        String sql = "SELECT * FROM product";
        return new ResponseEntity<>(jdbcTemplate.query(sql, new ProductRowMapper()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> findById(Long productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
         List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper(), productId);

         if(products.isEmpty()){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
         return new ResponseEntity<>(products.get(0), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Product>  save(Product product) {
        String sql = "INSERT INTO product (product_title, product_description, product_sku, " +
                "product_category_id, product_subcategory_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getProductTitle(), product.getProductDescription(),
                product.getProductSku(), product.getProductCategoryId(),
                product.getProductSubcategoryId());

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Product> update(Product product) {
        String sql = "UPDATE product SET product_title = ?, product_description = ?, product_sku = ?, " +
                "product_category_id = ?, product_subcategory_id = ? " +
                "WHERE product_id = ?";
        int res = jdbcTemplate.update(sql, product.getProductTitle(), product.getProductDescription(),
                product.getProductSku(), product.getProductCategoryId(), product.getProductSubcategoryId(),
                product.getProductId());

        if(res == 1){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Response> delete(Long productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        return RepositoryUtils.getDeleteResponseEntity(productId, sql, jdbcTemplate);

    }

}
