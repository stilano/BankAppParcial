/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utilities.Response;
import core.controllers.utilities.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountStorage;
import core.models.storage.UserStorage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author tilan
 */
public class AccountController {

    public static Response createAccount(String userIdText, String initialBalanceText) {
        try {
            int userId = Integer.parseInt(userIdText);
            double initialBalance = Double.parseDouble(initialBalanceText);

            if (initialBalance < 0) {
                return new Response("Initial balance must be non-negative", Status.BAD_REQUEST);
            }

            User selectedUser = UserStorage.getInstance().getUser(userId);

            if (selectedUser == null) {
                return new Response("User not found", Status.BAD_REQUEST);
            }
            
            Random random = new Random();
            int first = random.nextInt(1000);
            int second = random.nextInt(1000000);
            int third = random.nextInt(100);

            String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
            System.out.println(accountId);

            Account account = new Account(accountId, selectedUser, initialBalance);

            AccountStorage.getInstance().addAccount(account);

            return new Response("Account created successfully", Status.CREATED);

        } catch (NumberFormatException ex) {
            return new Response("Invalid input format for user ID or balance", Status.BAD_REQUEST);
        } catch (Exception ex) {
            return new Response("Unexpected error occurred", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getAccounts() {
        try {
            AccountStorage storage = AccountStorage.getInstance();
            ArrayList<Account> accounts = storage.getAllAccounts();

            if (accounts.isEmpty()) {
                return new Response("No accounts found", Status.NOT_FOUND);
            }
            
            return new Response("Got all accounts", Status.OK, accounts);
        } catch (Exception ex) {
            return new Response("Unexpected error while fetching accounts", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
