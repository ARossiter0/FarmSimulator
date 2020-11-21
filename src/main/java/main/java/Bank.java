package main.java;

public class Bank {
    private int balance;
    
    public Bank(int initialBalance) {
        balance = initialBalance;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public void deposit(int amount) {
        balance += amount;
    }
    
    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
