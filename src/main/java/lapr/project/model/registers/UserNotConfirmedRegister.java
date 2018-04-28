/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.model.User;
import lapr.project.model.User.Language;

/**
 * User Not Confirmed Regist
 *
 * @author Luís Cunha on 02/06/2017
 */
@XmlRootElement
@Entity
public class UserNotConfirmedRegister {

    /**
     * ArrayList that contains all non confirmed Users.
     */
    @XmlElementWrapper(name = "UnConfirmedUsers")
    @XmlElement(name = "User")
    @OneToMany
    private final List<User> usersNotConfirmedList;
    @Id
    private String id;

    /**
     * UserNotConfirmedRegister Class Constructor
     */
    public UserNotConfirmedRegister() {
        usersNotConfirmedList = new ArrayList<>();
    }

    /**
     * Creates a new user and returns it.
     *
     * @param name     new User's Name
     * @param userName new User's userName
     * @param email    new User's email
     * @param password new User's password
     * @param language new User's language
     * @return New User
     */
    public User newUser(String name, String userName, String email, String password, Language language) {
        return new User(name, userName, email, password, language);
    }

    /**
     * Add a new user to list.
     *
     * @param user User to be added.
     * @return Logic value referent to the sucess of the operation.
     */
    public boolean addUser(User user) {
        if (!usersNotConfirmedList.contains(user)) {
            return usersNotConfirmedList.add(user);
        }
        return false;
    }

    /**
     * Removes a new user of the list.
     *
     * @param user User to be remove.
     * @return Logic value referent to the sucess of the operation.
     */
    public boolean removeUser(User user) {
        return usersNotConfirmedList.remove(user);
    }

    /**
     * Obtains List of unconfirmed Users.
     *
     * @return Returns List of unconfirmed users.
     */
    public List<User> getUserNotConfirmed() {
        return new ArrayList<>(usersNotConfirmedList);
    }

    /**
     * Returns the number os elements in the list.
     *
     * @return Number of elements.
     */
    public int getSize() {
        return usersNotConfirmedList.size();
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 5;
        for (User a : usersNotConfirmedList) {
            hash = 23 * hash + (a == null ? 0 : a.hashCode());
        }
        return hash;
    }

    /**
     * This method compares the equality of the current object with the object
     * of same type.
     *
     * @param obj Object to be compared.
     * @return Logic value of the comparison.
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserNotConfirmedRegister)) {
            return false;
        }
        final UserNotConfirmedRegister other = (UserNotConfirmedRegister) obj;

        if (other.getSize() != this.getSize()) {
            return false;
        }
        for (User user : other.getUserNotConfirmed()) {
            if (!usersNotConfirmedList.contains(user)) {
                return false;
            }
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
