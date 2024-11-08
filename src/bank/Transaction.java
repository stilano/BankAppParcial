/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

/**
 *
 * @author edangulo
 */
public class Transaction {
    
    private TransactionType type;
    private Account account1;
    private Account account2;
    private double amount;

    public Transaction(TransactionType type, Account account1, double amount) {
        this.type = type;
        this.account1 = account1;
        this.amount = amount;
    }

    public Transaction(TransactionType type, Account account1, Account account2, double amount) {
        this.type = type;
        this.account1 = account1;
        this.account2 = account2;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Account getAccount1() {
        return account1;
    }

    public Account getAccount2() {
        return account2;
    }

    public double getAmount() {
        return amount;
    }
    
}
