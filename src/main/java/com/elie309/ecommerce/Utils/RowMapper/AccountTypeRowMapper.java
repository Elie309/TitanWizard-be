package com.elie309.ecommerce.Utils.RowMapper;

import com.elie309.ecommerce.Models.AccountsModels.AccountType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountTypeRowMapper implements RowMapper<AccountType> {

    @Override
    public AccountType mapRow(ResultSet rs, int rowNum) {
        AccountType accountType = new AccountType();
        try {
            accountType.setAccountTypeId(rs.getLong("account_type_id"));
            accountType.setAccountType(rs.getString("account_type"));
            accountType.setAccountTypeCreatedAt(rs.getTimestamp("account_type_created_at"));
            accountType.setAccountTypeUpdatedAt(rs.getTimestamp("account_type_updated_at"));
        } catch (SQLException e) {
            //TODO: Handle SQLException appropriately, e.g., logging or throwing a custom exception
        }
        return accountType;
    }
}
