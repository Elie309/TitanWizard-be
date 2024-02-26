package com.elie309.ecommerce.Exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class JdbcErrorHandler {

    public static void errorHandler(Exception e) throws ResponseStatusException{

        switch (e) {
            case EmptyResultDataAccessException emptyResultDataAccessException ->
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");
            case IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException ->
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Many record were provided");
            case DataAccessException dataAccessException ->
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            case null, default ->
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }



}
