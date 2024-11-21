/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transactions;

import core.models.Account;

/**
 *
 * @author tilan
 */
public class Deposit implements TransactionType {

    @Override
    public void execute(Account source, Account destination, double amount) {
        if (destination != null) {
            destination.deposit(amount);
        }
    }

    @Override
    public String name() {
        return "Deposit";
    }
    
    
}

