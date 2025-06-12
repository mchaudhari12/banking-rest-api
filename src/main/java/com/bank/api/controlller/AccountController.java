package com.bank.api.controlller;

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
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

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
}
