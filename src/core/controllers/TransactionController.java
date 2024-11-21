/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utilities.Response;
import core.controllers.utilities.Status;
import core.models.Account;
import core.controllers.handlers.TransactionHandler;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;
import core.models.transactions.Transaction;
import java.util.ArrayList;

/**
 *
 * @author tilan
 */
public class TransactionController {

    public static Response registerTransaction(String transactionType, String sourceAccountId, String destinationAccountId, String amount) {
        try {
            double amountDouble;

            try {
                amountDouble = Double.parseDouble(amount);
                if (amountDouble <= 0) {
                    return new Response("Amount must be greater than 0", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }

            // Obtener las cuentas
            AccountStorage accountStorage = AccountStorage.getInstance();
            Account sourceAccount = accountStorage.getAccount(sourceAccountId);
            Account destinationAccount = accountStorage.getAccount(destinationAccountId);

            TransactionHandler transactionHandler = new TransactionHandler();
            return transactionHandler.processTransaction(transactionType, sourceAccount, destinationAccount, amountDouble);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getTransactions() {
        try {
            TransactionStorage storage = TransactionStorage.getInstance();
            ArrayList<Transaction> transactions = storage.getAllTransactions();

            if (transactions.isEmpty()) {
                return new Response("No transactions found", Status.NOT_FOUND);
            }

            return new Response("Got all transactions", Status.OK, transactions);
        } catch (Exception ex) {
            return new Response("Unexpected error while fetching transactions", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
