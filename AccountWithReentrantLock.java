// AccountWithReentrantLock.java
// use explicit locking

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class AccountWithReentrantLock {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Create and launch 100 threads
        for (int i = 0; i < 100; i++) {
            executor.execute(new AddAPennyTask());
        }
        executor.shutdown();
        // Wait until all tasks are finished
        while (!executor.isTerminated()) {}

        System.out.println("Balance: " + account.getBalance());
    }

     // inner class to house a thread for adding a penny to the account
    private static class AddAPennyTask implements Runnable {
        public void run() {
            account.deposit(1);
        }
    }

    // inner class to represent the account
    private static class Account {
        private static Lock lock = new ReentrantLock();
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            lock.lock(); // acquire the lock

            try {
                int newBalance = balance + amount;
                Thread.sleep(5);
                balance = newBalance;
            } catch (InterruptedException ex) {
            } finally {
                lock.unlock(); // release the lock
            }
        }
    }
}
