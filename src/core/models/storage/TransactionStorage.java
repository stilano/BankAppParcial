/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.transactions.Transaction;
import java.util.ArrayList;

/**
 *
 * @author tilan
 */
public class TransactionStorage {

    private static TransactionStorage instance;
    private ArrayList<Transaction> transactions;

    private TransactionStorage() {
        this.transactions = new ArrayList<>();
    }

    public static TransactionStorage getInstance() {
        if (instance == null) {
            instance = new TransactionStorage();
        }
        return instance;
    }

    public boolean addTransaction(Transaction transaction) {
        for (Transaction t : transactions) {
            if (t.equals(transaction)) { 
                return false;
            }
        }
        transactions.add(transaction);
        return true;
    }
}