package com.elie309.titanwizard.models.accountsModels;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class Account {

    @JsonIgnore
    private Long accountId;

    @JsonAlias("accountFirstname")
    @JsonProperty(value = "firstname")
    private String accountFirstname;

    @JsonAlias("accountMiddlename")
    @JsonProperty(value = "middlename")
    private String accountMiddlename;

    @JsonAlias("accountLastname")
    @JsonProperty(value = "lastname")
    private String accountLastname;

    @JsonProperty(value = "accountType", access = JsonProperty.Access.READ_ONLY)
    private AccountType accountType;

    @JsonAlias("accountEmail")
    @JsonProperty(value = "email")
    private String accountEmail;

    @JsonAlias("accountPassword")
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String accountPassword;

    @JsonAlias("accountCreatedAt")
    @JsonProperty(value = "createdAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp accountCreatedAt;

    @JsonAlias("accountUpdatedAt")
    @JsonProperty(value = "updatedAt", access = JsonProperty.Access.READ_ONLY)
    private Timestamp accountUpdatedAt;

    public Account() {
    }

    //#region Setters and getters

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountFirstname() {
        return accountFirstname;
    }

    public String getAccountMiddlename() {
        return accountMiddlename;
    }

    public String getAccountLastname() {
        return accountLastname;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public Timestamp getAccountCreatedAt() {
        return accountCreatedAt;
    }

    public Timestamp getAccountUpdatedAt() {
        return accountUpdatedAt;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountFirstname(String accountFirstname) {
        this.accountFirstname = accountFirstname;
    }

    public void setAccountMiddlename(String accountMiddlename) {
        this.accountMiddlename = accountMiddlename;
    }

    public void setAccountLastname(String accountLastname) {
        this.accountLastname = accountLastname;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public void setAccountCreatedAt(Timestamp accountCreatedAt) {
        this.accountCreatedAt = accountCreatedAt;
    }

    public void setAccountUpdatedAt(Timestamp accountUpdatedAt) {
        this.accountUpdatedAt = accountUpdatedAt;
    }


    //#endregion

    //#region Methods

    /**
     * Return false if it is valid and true if invalid
     * @return boolean
     */
    public boolean isNotValid() {

        //WE ARE NOT CHECKING THE PASSWORD

        if (this.getAccountFirstname() == null || this.getAccountFirstname().isEmpty()) {
            return true;
        }
        if (this.getAccountLastname() == null || this.getAccountLastname().isEmpty()) {
            return true;
        }
        return this.getAccountEmail() == null || !this.isValidEmail();
    }

    public boolean isValidPassword(){
        //TODO: USE password.matches("REGULAR EXPRESSION");;
        return this.getAccountPassword().length() >= 8;

    }

    public boolean isValidEmail() {
        //TODO: USE email.matches("REGULAR EXPRESSION");
        return this.getAccountEmail().contains("@");
    }

    //#endregion

    @Override
    public String toString() {
        return "Account{" +
                "accountFirstname='" + accountFirstname + '\'' +
                ", accountMiddlename='" + accountMiddlename + '\'' +
                ", accountLastname='" + accountLastname + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                '}';
    }
}
