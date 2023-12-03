package com.blueharvest.assignment.service.impl;

import com.blueharvest.assignment.exceptions.DuplicateAccountException;
import com.blueharvest.assignment.exceptions.NotFoundException;
import com.blueharvest.assignment.model.Account;
import com.blueharvest.assignment.model.Customer;
import com.blueharvest.assignment.model.Transaction;
import com.blueharvest.assignment.model.enums.TransactionType;
import com.blueharvest.assignment.repository.AccountModelRepository;
import com.blueharvest.assignment.service.AccountService;
import com.blueharvest.assignment.service.CustomerService;
import com.blueharvest.assignment.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountModelRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;
    private static final Long MINIMUM_CREDIT = 100L;
    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    public Account createAccount(Account account, Long customerId) {
        Customer customer = customerService.findById(customerId);
        logger.info("customer is exist:{}", customer);
        if (account.getBalance() >= MINIMUM_CREDIT) {
            logger.info("account and customer are valid.");

            account.setCustomer(customer);
            account = accountRepository.save(account);
            if (account.getBalance() > 0) {
                Transaction transaction = new Transaction();
                transaction.setAccount(account);
                transaction.setAmount(account.getBalance());
                transaction.setTransactionType(TransactionType.INITIALIZE);
                transactionService.commitTransaction(transaction, account.getAccountId());
                logger.info("Transaction commit:{}", transaction);
            }
            logger.debug("Account Response:{}", account);
            return account;
        } else {
            logger.error("Account:{} Already exists for given customer:{}", account.getAccountType(), customerId);
            throw new DuplicateAccountException("Account already exists for customer:" + customerId);
        }

    }

    @Override
    public Account findById(Long accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isPresent()) {
            logger.info("Account is Exist:)");
            return account.get();
        } else {
            logger.error("AccountId is not found {}", accountId);
            throw new NotFoundException("Account Id is not Found:" + accountId);
        }
    }


    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }


    @Override
    public List<Account> findAllAccountsByCustomerId(Long customerId) {

        Customer customer = customerService.findById(customerId);
        logger.info("customer:{}", customer);
        List<Account> accounts = customer.getAccounts();
        logger.info("Accounts:{}", accounts);
        return accounts;
    }


    @Override
    public void deleteAccount(Long accountId) {

    }
}
