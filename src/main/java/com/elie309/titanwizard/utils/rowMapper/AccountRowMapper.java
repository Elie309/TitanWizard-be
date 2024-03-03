package com.elie309.titanwizard.utils.rowMapper;

import com.elie309.titanwizard.models.accountsModels.Account;
import com.elie309.titanwizard.models.accountsModels.AccountType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) {
        Account account = new Account();
        try {
            account.setAccountId(rs.getLong("account_id"));
            account.setAccountFirstname(rs.getString("account_firstname"));
            account.setAccountMiddlename(rs.getString("account_middlename"));
            account.setAccountLastname(rs.getString("account_lastname"));
            account.setAccountEmail(rs.getString("account_email"));
            account.setAccountPassword(rs.getString("account_password"));
            account.setAccountCreatedAt(rs.getTimestamp("account_created_at"));
            account.setAccountUpdatedAt(rs.getTimestamp("account_updated_at"));

            AccountTypeRowMapper accountTypeRowMapper = new AccountTypeRowMapper();
            AccountType accountType = accountTypeRowMapper.mapRow(rs, rowNum);
            account.setAccountType(accountType);


        } catch (SQLException e) {
            //TODO: Handle SQLException appropriately, e.g., logging or throwing a custom exception
        }
        return account;
    }
}
