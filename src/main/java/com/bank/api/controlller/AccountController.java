package com.bank.api.controlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.api.dto.TransferRequest;
import com.bank.api.entity.Account;
import com.bank.api.repository.AccountRepository;
import com.bank.api.service.AccountService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

   
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id){
        return ResponseEntity.ok(accountService.account(id));
    }

    @PostMapping("/transfer")
   public ResponseEntity<String> transfer(@RequestBody TransferRequest request){
        accountService.transferFunds(request);
        return ResponseEntity.ok("Transfer Succesfull"); 
   }

   @PostMapping
   public ResponseEntity<Account> createdAccount(@RequestBody Account account){
        Account saved = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted");
   }

   @GetMapping("account/list")
   public ResponseEntity<List<Account>> allAccount(){
    return ResponseEntity.ok(accountService.getAccount());
   }
}
