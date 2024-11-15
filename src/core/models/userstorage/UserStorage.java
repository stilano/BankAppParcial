/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.userstorage;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author tilan
 */
public class UserStorage {
    // Instancia Singleton
    private static UserStorage instance;
    // Atributos del UserStorage
    private ArrayList<User> persons;
    
    private UserStorage (){
        this.persons = new ArrayList<>();
    }
    
    public static UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }
    
    public boolean addUser(User person) {
        for (User p : this.persons) {
            if (p.getId() == person.getId()) {
                return false;
            }
        }
        this.persons.add(person);
        return true;
    }
    
    public User getUser(int id) {
        for (User person : this.persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }
    
    public boolean delUser(int id) {
        for (User person : this.persons) {
            if (person.getId() == id) {
                this.persons.remove(person);
                return true;
            }
        }
        return false;
    }
}
