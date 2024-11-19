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
public class Transfer implements TransactionType {

    @Override
    public void execute(Account source, Account destination, double amount) {
        if (source != null && destination != null && source.withdraw(amount)) {
            destination.deposit(amount); // Realiza el depósito en la cuenta destino
        } else {
            throw new IllegalArgumentException(", verifique las cuentas y el saldo");
        }
    }
}