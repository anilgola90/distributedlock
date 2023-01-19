package org.anil.distributedlock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monolith")
@RequiredArgsConstructor
public class MonolithAccountController {
    private final AccountService accountService;

    @PostMapping("/updateAccount/{id}")
    public void updateAccount(@PathVariable("id") Long id) throws InterruptedException {
        accountService.updateAccount(id);
    }

    @PostMapping("/updateAccountViaDistributedLocks/{id}")
    public void updateAccountViaDistributedLocks(@PathVariable("id") Long id) throws InterruptedException {
        accountService.updateAccountViaDistributedLocks(id);
    }
}
