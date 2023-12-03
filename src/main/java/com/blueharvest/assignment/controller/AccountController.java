package com.blueharvest.assignment.controller;

import com.blueharvest.assignment.dto.AccountDto;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@RestController
@RequestMapping(AccountController.REQUEST_PATH)
public class AccountController {
    public static final String REQUEST_PATH = "/api/v1/account";

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    @GetMapping("/accounts")
    private ResponseEntity<List<Account>> getAllAccounts() {
        logger.info("Get all accounts.");
        return new ResponseEntity<>(accountService.findAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Account> getAccountById(@Valid @PathVariable @Min(value = 1) Long id) {
        logger.info("Get account by Id {}", id);
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/customers/{id}/accounts")
    private ResponseEntity<Account> createAccount(@RequestBody @Valid AccountDto account, @PathVariable Long id) {
        logger.info("Create a new account for customer{}", id);
        return new ResponseEntity<>(accountService.createAccount(modelMapper.map(account, Account.class), id),
                HttpStatus.CREATED);
    }


    @DeleteMapping("/{accountId}")
    private ResponseEntity deleteAccount(@PathVariable("accountId") @Min(1) Long accountId) {
        logger.info("delete account {} ", accountId);
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customers/{id}/accounts")
    public ResponseEntity<List<Account>> getAllAccountsByCustomerId(@Valid @PathVariable @Min(value = 1) Long id) {
        logger.info("Get all accounts of customer:{}", id);
        return new ResponseEntity<>(accountService.findAllAccountsByCustomerId(id), HttpStatus.OK);
    }

}
