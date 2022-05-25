// AccountWithSync.java
// use synchronized

import java.util.concurrent.*;

public class AccountWithSync {
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
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public synchronized void deposit(int amount) {
            int newBalance = balance + amount;

            try {
                Thread.sleep(5);
            }
            catch (InterruptedException ex) {}

            balance = newBalance;
        }
    }
}
