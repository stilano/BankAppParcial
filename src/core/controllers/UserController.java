/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.models.User;
import core.models.userstorage.UserStorage;

/**
 *
 * @author tilan
 */
public class UserController {
    public static String createUser(String id, String firstname, String lastname, String age, String gender) {
        try {
            int idInt, ageInt = 0;
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return "Id must be positive";
                }
            } catch (NumberFormatException ex) {
                return "Id must be numeric";
            }
            
            if (firstname.equals("")) {
               return "First Name must not be empty";
            }
            
            if (lastname.equals("")) {
                System.out.println("Last Name must not be empty");
            }
            
            try {
                ageInt = Integer.parseInt(age);
                if (ageInt < 0) {
                    System.out.println("Age must be positive");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Age must be numeric");
            }
            
            UserStorage storage = UserStorage.getInstance();            
            if (!storage.addUser(new User(idInt, firstname, lastname, ageInt))) {
                System.out.println("A person with that id already exists");
            }
            return "User created successfully";
        } catch (Exception ex) {
            return "Unexpected error";
        }
    }
}
