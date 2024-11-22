/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author tilan
 */
public class AccountStorage {

    private static AccountStorage instance;
    private ArrayList<Account> accounts;

    private AccountStorage() {
        this.accounts = new ArrayList<>();
    }

    public static AccountStorage getInstance() {
        if (instance == null) {
            instance = new AccountStorage();
        }
        return instance;
    }

    public boolean addAccount(Account account) {
        for (Account a : this.accounts) {
            if (a.getId() == account.getId()) {
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }

    public Account getAccount(String id) {
        for (Account account : this.accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<Account> getAllAccounts() {
        accounts.sort((a1, a2) -> (a1.getId().compareTo(a2.getId())));
        return new ArrayList<>(this.accounts);
    }
}
