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
public class Withdraw implements TransactionType {

    @Override
    public void execute(Account source, Account destination, double amount) {
        if (source != null && source.withdraw(amount)) {
        } else {
            throw new IllegalArgumentException("Saldo insuficiente o cuenta no válida");
        }
    }
}

