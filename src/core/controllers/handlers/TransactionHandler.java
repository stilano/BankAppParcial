/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.handlers;

import core.models.Account;
import core.models.transactions.TransactionType;

/**
 *
 * @author tilan
 */
public class TransactionHandler {
    
    public void processTransaction(TransactionType transactionType, Account source, Account destination, double amount) {
        if (transactionType != null) {
            transactionType.execute(source, destination, amount);
        } else {
            throw new IllegalArgumentException("Tipo de transacción no válido");
        }
    }
}

