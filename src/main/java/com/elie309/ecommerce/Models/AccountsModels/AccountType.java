package com.elie309.ecommerce.Models.AccountsModels;

import java.sql.Timestamp;

public class AccountType {
    private Long accountTypeId;
    private String accountType;
    private Timestamp accountTypeCreatedAt;
    private Timestamp accountTypeUpdatedAt;

    public AccountType() {}

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Timestamp getAccountTypeCreatedAt() {
        return accountTypeCreatedAt;
    }

    public void setAccountTypeCreatedAt(Timestamp accountTypeCreatedAt) {
        this.accountTypeCreatedAt = accountTypeCreatedAt;
    }

    public Timestamp getAccountTypeUpdatedAt() {
        return accountTypeUpdatedAt;
    }

    public void setAccountTypeUpdatedAt(Timestamp accountTypeUpdatedAt) {
        this.accountTypeUpdatedAt = accountTypeUpdatedAt;
    }
}
