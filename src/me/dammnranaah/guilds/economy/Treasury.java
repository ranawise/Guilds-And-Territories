package me.dammnranaah.guilds.economy;

public class Treasury {
    private double balance;

    public Treasury(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
