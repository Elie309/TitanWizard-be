package com.elie309.titanwizard.controllers.accountsController;

import com.elie309.titanwizard.models.accountsModels.Account;
import com.elie309.titanwizard.repository.accountRepository.AccountRepository;
import com.elie309.titanwizard.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountRepository.findById(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> updateAccount(@PathVariable Long id, @RequestBody Account account) {

        account.setAccountId(id);

        if(account.isNotValid()){ //it doesn't check the password since it cannot be change this way
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account updates are invalid.");
        }

        accountRepository.update(account);
        return ResponseEntity.ok(new Response("Record updated", true));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> deleteAccount(@PathVariable Long id) {
         accountRepository.delete(id);
         return ResponseEntity.ok(new Response("Record Deleted", true));
    }
}
