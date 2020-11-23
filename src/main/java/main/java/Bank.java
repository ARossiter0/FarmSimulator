package main.java;

public class Bank implements Component {
    private int balance;
    private Mediator m;
    
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

    @Override
    public void setMediator(Mediator mediator) {
        this.m = mediator;
    }

    @Override
    public String getName() {
        return "Bank";
    }
}
