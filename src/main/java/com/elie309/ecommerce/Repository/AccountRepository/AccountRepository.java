package com.elie309.ecommerce.Repository.AccountRepository;

import com.elie309.ecommerce.Exceptions.JdbcErrorHandler;
import com.elie309.ecommerce.Models.AccountsModels.Account;
import com.elie309.ecommerce.Models.AccountsModels.AccountType;
import com.elie309.ecommerce.Repository.IRepository;
import com.elie309.ecommerce.Utils.RowMapper.AccountRowMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class AccountRepository implements IRepository<Account> {

    private final JdbcTemplate jdbcTemplate;

    private final AccountTypeRepository accountTypeRepository;


    public AccountRepository(JdbcTemplate jdbcTemplate, AccountTypeRepository accountTypeRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM account LEFT JOIN ecommerce.account_type on account.account_type_id = account_type.account_type_id";
        try {
            return jdbcTemplate.query(sql, new AccountRowMapper());
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");
    }

    @Override
    public Account findById(Long accountId) {

        String sql = "SELECT * FROM account LEFT JOIN ecommerce.account_type on account.account_type_id = account_type.account_type_id WHERE account_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountId);
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");

    }

    public Account findByEmail(String email) {
        String sql = "SELECT * FROM account LEFT JOIN ecommerce.account_type on account.account_type_id = account_type.account_type_id WHERE account_email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), email);
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");

    }


    @Override
    public Account save(Account account) {

        try {
            AccountType accountType = accountTypeRepository.findByRole("user");

            account.setAccountType(accountType);

            String sql = "INSERT INTO account (account_firstname, account_middlename, account_lastname, account_type_id, account_email, account_password) VALUES (?, ?, ?, ?, ?, ?)";

            int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(), account.getAccountLastname(), account.getAccountType().getAccountTypeId(), account.getAccountEmail(), account.getAccountPassword());

            if (res != 0) {
                return account;
            }
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not saved");


    }

    @Override
    public Account update(Account account) {
        String sql = "UPDATE account SET account_firstname = ?, account_middlename = ?, account_lastname = ?, account_email = ? WHERE account_id = ?";
        try {

            int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(), account.getAccountLastname(), account.getAccountEmail(), account.getAccountId());
            if (res != 0) {
                return account;
            }
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not updated, Invalid data");


    }

    @Override
    public void delete(Long accountId) {
        String sql = "DELETE FROM account WHERE account_id = ?";
        try {

            int res = jdbcTemplate.update(sql, accountId);
            if (res == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found");
            }
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

    }


}
