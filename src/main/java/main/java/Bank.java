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
    
    public void withdraw(int amount) {
        balance -= amount;
    }
}
