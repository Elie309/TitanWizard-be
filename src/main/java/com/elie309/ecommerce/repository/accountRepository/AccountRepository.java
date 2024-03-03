package com.elie309.ecommerce.repository.accountRepository;

import com.elie309.ecommerce.exceptions.JdbcErrorHandler;
import com.elie309.ecommerce.models.accountsModels.Account;
import com.elie309.ecommerce.models.accountsModels.AccountType;
import com.elie309.ecommerce.repository.IRepository;
import com.elie309.ecommerce.utils.rowMapper.AccountRowMapper;
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
        String sql = "SELECT * FROM account LEFT JOIN titanwizard.account_type on account.account_type_id = account_type.account_type_id";
        try {
            return jdbcTemplate.query(sql, new AccountRowMapper());
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");
    }

    @Override
    public Account findById(Long accountId) {

        String sql = "SELECT * FROM account LEFT JOIN titanwizard.account_type on account.account_type_id = account_type.account_type_id WHERE account_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountId);
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Records");

    }

    public Account findByEmail(String email) {
        String sql = "SELECT * FROM account LEFT JOIN titanwizard.account_type on account.account_type_id = account_type.account_type_id WHERE account_email = ?";

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

            if (res == 1) {
                return account;
            }
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not saved");

    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE account SET account_firstname = ?, account_middlename = ?, account_lastname = ?, account_email = ? WHERE account_id = ?";
        try {

            int res = jdbcTemplate.update(sql, account.getAccountFirstname(), account.getAccountMiddlename(), account.getAccountLastname(), account.getAccountEmail(), account.getAccountId());
            if (res != 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not updated");
            }

        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

    }

    @Override
    public void delete(Long accountId) {
        String sql = "DELETE FROM account WHERE account_id = ?";
        try {

            int res = jdbcTemplate.update(sql, accountId);
            if (res != 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not Deleted");
            }
        } catch (Exception e) {
            JdbcErrorHandler.errorHandler(e);
        }

    }


}
