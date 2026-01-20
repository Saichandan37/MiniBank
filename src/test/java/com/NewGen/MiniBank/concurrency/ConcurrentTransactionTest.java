package com.NewGen.MiniBank.concurrency;


import com.NewGen.MiniBank.service.TransactionService;
import com.NewGen.MiniBank.serviceImpl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class ConcurrentTransactionTest {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Test
    void concurrentWithdrawTest() throws InterruptedException {

        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    transactionService.withdraw(11L, BigDecimal.valueOf(20));
                    System.out.println("Withdraw success");
                } catch (Exception e) {
                    System.out.println("Withdraw failed: " + e.getMessage());
                } finally {
                    latch.countDown(); // ✅ CORRECT PLACE
                }
            });
        }

        latch.await(); // wait for all threads
        executorService.shutdown();

        System.out.println("All transactions completed");
    }
}

