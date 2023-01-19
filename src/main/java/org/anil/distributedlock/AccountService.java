package org.anil.distributedlock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private  ReentrantLock lock = new ReentrantLock();
    public void updateAccount(Long id) throws InterruptedException {
       boolean lockAquired =  lock.tryLock();
       if(lockAquired){
           try{
               log.info("lock taken");
               Account account = accountRepository.findById(id).get();
               account.setBalance(account.getBalance() + 100L);
               Thread.sleep(20_000);
               accountRepository.save(account);
           }
           finally {
               lock.unlock();
           }
       }
    }

    private final LockRegistry lockRegistry;

    private final JdbcTemplate jdbcTemplate;

    public void updateAccountViaDistributedLocks(Long id) throws InterruptedException {
        var lock = lockRegistry.obtain(String.valueOf(id));
        boolean lockAquired =  lock.tryLock();
        if(lockAquired){
            try{
                log.info("lock taken");
                Account account = accountRepository.findById(id).get();
                account.setBalance(account.getBalance() + 100L);
                Thread.sleep(20_000);
                accountRepository.save(account);
            }
            finally {
                lock.unlock();
            }
        }
    }




}
