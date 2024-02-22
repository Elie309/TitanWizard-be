package com.elie309.ecommerce.Repository.AccountRepository;

import com.elie309.ecommerce.Models.AccountsModels.Account;
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

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM account";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    @Override
    public Account findById(Long accountId) {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper(), accountId);

        if(accounts.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID Not found");
        }

        return accounts.get(0);
    }

    @Override
    public Account save(Account account) {
        String sql = "INSERT INTO account (account_firstname, account_middlename, account_lastname, " +
                "account_type, account_email, account_password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(),
                account.getAccountLastname(), account.getAccountType(), account.getAccountEmail(),
                account.getAccountPassword());

        if(res == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not saved");
        }
        return account;


    }

    @Override
    public Account update(Account account) {
        String sql = "UPDATE account SET account_firstname = ?, account_middlename = ?, account_lastname = ?, " +
                "account_type = ?, account_email = ?, account_password = ? " +
                "WHERE account_id = ?";
        int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(),
                account.getAccountLastname(), account.getAccountType(), account.getAccountEmail(),
                account.getAccountPassword(), account.getAccountId());

        if(res == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not updated");
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

    public Account findByEmail(String email){
        String sql = "SELECT * FROM account WHERE account_email = ?";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper(), email);

        if(accounts.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Not found");
        }
        return accounts.get(0);
    }

}
