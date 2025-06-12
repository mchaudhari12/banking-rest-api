package com.bank.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bank.api.dto.TransferRequest;
import com.bank.api.entity.Account;
import com.bank.api.exception.InsufficientFundsException;
import com.bank.api.exception.ResourceNotFoundException;
import com.bank.api.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account account(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
    }

    @Transactional
    public void transferFunds(TransferRequest request) {

       Account from = accountRepository.findById(request.getFromAccountId())
                          .orElseThrow(() -> new ResourceNotFoundException("Sender not found"));
        Account to = accountRepository.findById(request.getToAccountId())
                          .orElseThrow(() -> new ResourceNotFoundException("Receiver not found"));

          if (from.getBalance() < request.getAmount()) {
                throw new InsufficientFundsException("Not enough balance");
             }

             from.setBalance(from.getBalance() - request.getAmount());
             to.setBalance(to.getBalance() + request.getAmount());
     
             accountRepository.save(from);
             accountRepository.save(to);
    }

    public void deleteAccount(Long id){
        if(!accountRepository.existsById(id)){
            throw new RuntimeException("Account Not Found");
        }
        accountRepository.deleteById(id);
    }

    public List<Account> getAccount(){
        return accountRepository.findAll();
    }
    
}
