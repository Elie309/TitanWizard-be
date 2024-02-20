package com.elie309.ecommerce.Models.AccountsModels;

import java.sql.Timestamp;

public class Account {
    private Long accountId;
    private String accountFirstname;
    private String accountMiddlename;
    private String accountLastname;
    private Long accountType;
    private String accountEmail;
    private String accountPassword;
    private Timestamp accountCreatedAt;
    private Timestamp accountUpdatedAt;

    public Account() { }


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountFirstname() {
        return accountFirstname;
    }

    public void setAccountFirstname(String accountFirstname) {
        this.accountFirstname = accountFirstname;
    }

    public String getAccountMiddlename() {
        return accountMiddlename;
    }

    public void setAccountMiddlename(String accountMiddlename) {
        this.accountMiddlename = accountMiddlename;
    }

    public String getAccountLastname() {
        return accountLastname;
    }

    public void setAccountLastname(String accountLastname) {
        this.accountLastname = accountLastname;
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Timestamp getAccountCreatedAt() {
        return accountCreatedAt;
    }

    public void setAccountCreatedAt(Timestamp accountCreatedAt) {
        this.accountCreatedAt = accountCreatedAt;
    }

    public Timestamp getAccountUpdatedAt() {
        return accountUpdatedAt;
    }

    public void setAccountUpdatedAt(Timestamp accountUpdatedAt) {
        this.accountUpdatedAt = accountUpdatedAt;
    }
}
