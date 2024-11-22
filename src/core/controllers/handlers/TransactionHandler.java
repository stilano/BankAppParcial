/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.handlers;

import core.controllers.utilities.Response;
import core.controllers.utilities.Status;
import core.models.Account;
import core.models.storage.TransactionStorage;
import core.models.transactions.Deposit;
import core.models.Transaction;
import core.models.transactions.TransactionType;
import core.models.transactions.Transfer;
import core.models.transactions.Withdraw;

/**
 *
 * @author tilan
 */
public class TransactionHandler {

    public Response processTransaction(String transactionType, Account source, Account destination, double amount) {
        try {
            
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

            if (amount <= 0) {
                return new Response("Amount must be greater than 0", Status.BAD_REQUEST);
            }

            if ((transaction instanceof Transfer || transaction instanceof Withdraw) && source == null) {
                return new Response("Source account does not exist", Status.BAD_REQUEST);
            }
            if ((transaction instanceof Transfer || transaction instanceof Deposit) && destination == null) {
                return new Response("Destination account does not exist", Status.BAD_REQUEST);
            }
            if ((transaction instanceof Withdraw && source.withdraw(amount) == false)
                    || (transaction instanceof Transfer && source.withdraw(amount) == false)) {
                return new Response("Insufficient balance", Status.BAD_REQUEST);
            }

            transaction.execute(source, destination, amount);

            TransactionStorage.getInstance().addTransaction(new Transaction(transaction, source, destination, amount));

            return new Response("Transaction processed successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error occurred", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
