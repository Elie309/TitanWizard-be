package com.elie309.ecommerce.Repository;

import com.elie309.ecommerce.Utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

public class RepositoryUtils {

    public static ResponseEntity<Response> getDeleteResponseEntity(Long accountId, String sql, JdbcTemplate jdbcTemplate) {
        int res = jdbcTemplate.update(sql, accountId);

        if(res != 0){
            Response sucessfullyDeleted = new Response("Sucessfully deleted", true);
            return new ResponseEntity<>(sucessfullyDeleted, HttpStatus.OK);
        }else{
            Response sucessfullyDeleted = new Response("Not deleted", false);
            return new ResponseEntity<>(sucessfullyDeleted, HttpStatus.BAD_REQUEST);
        }
    }
}
