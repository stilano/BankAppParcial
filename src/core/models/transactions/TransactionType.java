/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transactions;

import core.models.Account;

/**
 *
 * @author edangulo
 */
public interface TransactionType {
    public abstract void execute(Account source, Account destination, double amount);
}

