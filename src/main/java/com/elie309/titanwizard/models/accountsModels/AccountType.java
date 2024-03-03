package com.elie309.titanwizard.models.accountsModels;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountType {
    @JsonIgnore
    private Long accountTypeId;

    @JsonAlias("accountTypeRole")
    @JsonProperty("role")
    private String accountTypeRole;


    public AccountType() {}

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeRole() {
        return accountTypeRole;
    }

    public void setAccountTypeRole(String accountTypeRole) {
        accountTypeRole = "ROLE_"+ accountTypeRole;
        this.accountTypeRole = accountTypeRole.toUpperCase();
    }


}
