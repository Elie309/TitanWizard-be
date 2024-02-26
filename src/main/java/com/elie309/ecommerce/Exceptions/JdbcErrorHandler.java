package com.elie309.ecommerce.Exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
public class JdbcErrorHandler {

    private final JdbcTemplate jdbcTemplate;

    public JdbcErrorHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) {
        try {

            return jdbcTemplate.queryForObject(sql,
                    rowMapper,
                    args);


        } catch (EmptyResultDataAccessException ex) {
            return null;
        } catch (IncorrectResultSizeDataAccessException ex) {

            handleIncorrectResultSize(ex);

        } catch (DataAccessException ex) {
            handleDataAccessError(ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
        return null;
    }


    public int update(String sql, Object... args) {
        try {
            return jdbcTemplate.update(sql, args);
        } catch (DataAccessException ex) {
            handleDataAccessError(ex);
            return 0;
        }
    }


    private void handleIncorrectResultSize(IncorrectResultSizeDataAccessException ex) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Many record were provided");
        //TODO: LOGGER
    }

    private void handleDataAccessError(DataAccessException ex) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during sql excuting");
        //TODO: DataAccessError
    }

}
