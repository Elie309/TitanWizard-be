package com.elie309.ecommerce.repository.accountRepository;

import com.elie309.ecommerce.models.accountsModels.AccountType;
import com.elie309.ecommerce.repository.IRepository;
import com.elie309.ecommerce.utils.rowMapper.AccountTypeRowMapper;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Repository
public class AccountTypeRepository implements IRepository<AccountType> {

    private final JdbcTemplate jdbcTemplate;

    public AccountTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AccountType> findAll() {
        String sql = "SELECT * FROM account_type";
        return jdbcTemplate.query(sql, new AccountTypeRowMapper());
    }

    @Override
    public AccountType findById(Long id) {
        String sql = "SELECT * FROM account_type WHERE account_type_id = ?";
        return jdbcTemplate.queryForObject(sql, new AccountTypeRowMapper(), id);
    }

    public AccountType findByRole(String role){
        String sql = "SELECT * FROM account_type WHERE account_type_role = ?";
        return jdbcTemplate.queryForObject(sql, new AccountTypeRowMapper(), role);
    }

    //WE CANNOT ALLOW THE BELOW BEHAVIORS
    @Override
    public AccountType save(AccountType accountType) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @Override
    public void update(AccountType accountType) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @Override
    public void delete(Long id) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
