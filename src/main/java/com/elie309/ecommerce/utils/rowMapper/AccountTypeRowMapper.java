package com.elie309.ecommerce.utils.rowMapper;

import com.elie309.ecommerce.models.accountsModels.AccountType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountTypeRowMapper implements RowMapper<AccountType> {

    @Override
    public AccountType mapRow(ResultSet rs, int rowNum) {
        AccountType accountType = new AccountType();
        try {
            accountType.setAccountTypeId(rs.getLong("account_type_id"));
            accountType.setAccountTypeRole(rs.getString("account_type_role"));
        } catch (SQLException e) {
            //TODO: Handle SQLException appropriately, e.g., logging or throwing a custom exception
        }
        return accountType;
    }
}
