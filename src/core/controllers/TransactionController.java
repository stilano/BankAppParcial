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
import core.models.transactions.Deposit;
import core.models.transactions.Transaction;
import core.models.transactions.TransactionType;
import core.models.transactions.Transfer;
import core.models.transactions.Withdraw;

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

            if (sourceAccount == null) {
                return new Response("Source account does not exist", Status.BAD_REQUEST);
            }

            if (transactionType.equals("TRANSFER") || transactionType.equals("DEPOSIT")) {
                if (destinationAccount == null) {
                    return new Response("Destination account does not exist", Status.BAD_REQUEST);
                }
            }

            TransactionType transaction = null;
            if (transactionType.equals("Deposit")) {
                transaction = new Deposit();
            } else if (transactionType.equals("Withdraw")) {
                transaction = new Withdraw();
            } else if (transactionType.equals("Transfer")) {
                transaction = new Transfer();
            } else {
                return new Response("Invalid transaction type", Status.BAD_REQUEST);
            }

            try {
                TransactionHandler transactionHandler = new TransactionHandler();
                transactionHandler.processTransaction(transaction, sourceAccount, destinationAccount, amountDouble);
            } catch (IllegalArgumentException ex) {
                return new Response("Insuficient balance", Status.BAD_REQUEST);
            }

            return new Response("Transaction processed successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
