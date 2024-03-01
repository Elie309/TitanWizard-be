package com.elie309.ecommerce.Controllers.AccountsController;

import com.elie309.ecommerce.Models.AccountsModels.Account;
import com.elie309.ecommerce.Repository.AccountRepository.AccountRepository;
import com.elie309.ecommerce.Utils.Response;
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

        if(account == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record not found");
        }

        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        account.setAccountId(id);
        Account newAccount = accountRepository.update(account);
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> deleteAccount(@PathVariable Long id) {
         accountRepository.delete(id);
         return ResponseEntity.ok(new Response("Record Deleted", true));
    }
}
