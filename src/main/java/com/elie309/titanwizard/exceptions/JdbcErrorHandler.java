package com.elie309.titanwizard.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;


public class JdbcErrorHandler {

    //TODO: Robust logging

    public static void errorHandler(Exception e) throws ResponseStatusException{
        switch (e) {
            case EmptyResultDataAccessException emptyResultDataAccessException ->
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");
            case IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException ->
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Many record were provided");
            case DataAccessException dataAccessException -> {

                if(dataAccessException.getRootCause() instanceof SQLException sqlException){
                    throw errorSqlExceptionHandler(sqlException);
                }
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
            case ResponseStatusException responseStatusException-> throw responseStatusException;
            case null, default ->
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    public static ResponseStatusException errorSqlExceptionHandler(SQLException sqle){
        return switch (sqle.getErrorCode()) {
            case 1062 ->// Duplicated key
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, sqle.getMessage());
            case 1452 -> // Foreign key constraint violation error code
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Foreign key constraint violation error occurred.");
            default ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An SQL error occured: " + sqle.getErrorCode());
        };
    }


}
