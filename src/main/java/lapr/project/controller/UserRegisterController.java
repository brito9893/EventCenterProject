package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

/**
 * Controller class for Register User Use Case
 */
public class UserRegisterController {

    /**
     * User registry
     */
    private final UserRegister userRegister;
    /**
     * Unconfirmed user registry
     */
    private final UserNotConfirmedRegister userNotConfirmedRegister;

    /**
     * Constructor for the controller class
     *
     * @param center : Event Center object
     */
    public UserRegisterController(EventCenter center) {
        this.userRegister = center.getUserRegister();
        this.userNotConfirmedRegister = center.getUserNotConfirmedRegister();
    }

    /**
     * Verifies if the email provided exists
     *
     * @param email : email to be verified
     * @return flag indicating if the email exists
     */
    public boolean verifyEmail(String email) {
        for (User user : userRegister.getUsersList()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        for (User user : userNotConfirmedRegister.getUserNotConfirmed()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies if the username provided exists
     *
     * @param username : username to be verified
     * @return flag indicating if the username exists
     */
    public boolean verifyUserName(String username) {
        for (User user : userRegister.getUsersList()) {
            if (user.getUserName().equals(username)) {
                return false;
            }
        }
        for (User user : userNotConfirmedRegister.getUserNotConfirmed()) {
            if (user.getUserName().equals(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a user for details passed by parameter and adds it to the register
     *
     * @param name     : Name
     * @param userName : Username
     * @param email    : Email
     * @param password : Password
     * @param language : Language
     */
    public void createUser(String name, String userName, String email, String password, String language) {
        User user = userNotConfirmedRegister.newUser(name, userName, email, password, User.Language.valueOf(language.toUpperCase()));
        userRegister.addUser(user);
    }

}
