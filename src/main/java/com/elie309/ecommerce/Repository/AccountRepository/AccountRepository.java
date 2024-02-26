package com.elie309.ecommerce.Repository.AccountRepository;

import com.elie309.ecommerce.Exceptions.JdbcErrorHandler;
import com.elie309.ecommerce.Models.AccountsModels.Account;
import com.elie309.ecommerce.Models.AccountsModels.AccountType;
import com.elie309.ecommerce.Repository.IRepository;
import com.elie309.ecommerce.Utils.RowMapper.AccountRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class AccountRepository implements IRepository<Account> {

    private final JdbcTemplate jdbcTemplate;

    private final AccountTypeRepository accountTypeRepository;

    private final JdbcErrorHandler jdbcErrorHandler;

    public AccountRepository(JdbcTemplate jdbcTemplate, AccountTypeRepository accountTypeRepository, JdbcErrorHandler jdbcErrorHandler) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountTypeRepository = accountTypeRepository;
        this.jdbcErrorHandler = jdbcErrorHandler;
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM account LEFT JOIN ecommerce.account_type on account.account_type_id = account_type.account_type_id";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    @Override
    public Account findById(Long accountId) {

            String sql = "SELECT * FROM account LEFT JOIN ecommerce.account_type on account.account_type_id = account_type.account_type_id WHERE account_id = ?";
            return jdbcErrorHandler.queryForObject(sql, new AccountRowMapper(), accountId);
    }

    public Account findByEmail(String email){
        String sql = "SELECT * FROM account LEFT JOIN ecommerce.account_type on account.account_type_id = account_type.account_type_id WHERE account_email = ?";
        return jdbcErrorHandler.queryForObject(sql, new AccountRowMapper(), email);

    }


    @Override
    public Account save(Account account) {

        try {
            AccountType accountType = accountTypeRepository.findByRole("user");

            account.setAccountType(accountType);

            String sql = "INSERT INTO account (account_firstname, account_middlename, account_lastname, " +
                    "account_type_id, account_email, account_password) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";


            int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(),
                    account.getAccountLastname(), account.getAccountType().getAccountTypeId(),
                    account.getAccountEmail(), account.getAccountPassword());

            if (res == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not saved");
            }
        }catch(Exception e){
            if(e instanceof DataAccessException){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ((DataAccessException) e).getMostSpecificCause().toString());
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return account;


    }

    @Override
    public Account update(Account account) {
        String sql = "UPDATE account SET account_firstname = ?, account_middlename = ?, account_lastname = ?, " +
                "account_email = ? " +
                "WHERE account_id = ?";
        int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(),
                account.getAccountLastname(), account.getAccountEmail(), account.getAccountId());
        if(res == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid - Account not updated");
        }
        return account;
    }

    @Override
    public void delete(Long accountId) {
        String sql = "DELETE FROM account WHERE account_id = ?";
        int res = jdbcTemplate.update(sql, accountId);
        if(res == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Found");
        }
    }



}
