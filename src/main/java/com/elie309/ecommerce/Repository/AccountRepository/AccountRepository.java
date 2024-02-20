package com.elie309.ecommerce.Repository.AccountRepository;

import com.elie309.ecommerce.Models.AccountsModels.Account;
import com.elie309.ecommerce.Repository.IRepository;
import com.elie309.ecommerce.Repository.RepositoryUtils;
import com.elie309.ecommerce.Utils.Response;
import com.elie309.ecommerce.Utils.RowMapper.AccountRowMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository implements IRepository<Account> {

    private final JdbcTemplate jdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseEntity<List<Account>> findAll() {
        String sql = "SELECT * FROM account";
        return new ResponseEntity<>(jdbcTemplate.query(sql, new AccountRowMapper()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> findById(Long accountId) {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        List<Account> accounts = jdbcTemplate.query(sql, new AccountRowMapper(), accountId);
        if(accounts.isEmpty()){
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accounts.get(0), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> save(Account account) {
        String sql = "INSERT INTO account (account_firstname, account_middlename, account_lastname, " +
                "account_type, account_email, account_password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(),
                account.getAccountLastname(), account.getAccountType(), account.getAccountEmail(),
                account.getAccountPassword());

        if(res != 0){
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Account> update(Account account) {
        String sql = "UPDATE account SET account_firstname = ?, account_middlename = ?, account_lastname = ?, " +
                "account_type = ?, account_email = ?, account_password = ? " +
                "WHERE account_id = ?";
        int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(),
                account.getAccountLastname(), account.getAccountType(), account.getAccountEmail(),
                account.getAccountPassword(), account.getAccountId());

        if(res != 0){
            return new ResponseEntity<>(account, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Response> delete(Long accountId) {
        String sql = "DELETE FROM account WHERE account_id = ?";
        return RepositoryUtils.getDeleteResponseEntity(accountId, sql, jdbcTemplate);

    }



}
