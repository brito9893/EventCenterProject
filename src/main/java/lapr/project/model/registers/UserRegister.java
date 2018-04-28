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

/**
 * Represents the user regist through a list where they are stored
 *
 * @author lilyl
 */
@XmlRootElement
@Entity
public class UserRegister {

    /**
     * ArrayList that stores user type objects
     */
    @XmlElementWrapper(name = "ConfirmedUsers")
    @XmlElement(name = "User")
    @OneToMany
    private final List<User> usersList;

    /**
     * Build an instance of UserRegister
     */
    public UserRegister() {
        usersList = new ArrayList<>();
    }

    /**
     * Method that allows us to get the size of the USER_LIST
     *
     * @return Integer corresponding to the size of the USER_LIST
     */
    public int getSize() {
        return usersList.size();
    }

    /**
     * Method that allows us to add a user type object to the usersList
     *
     * @param user object to be added
     * @return true (if the object is added) or false
     */
    public boolean addUser(User user) {
        if (!usersList.contains(user)) {
            return usersList.add(user);
        }
        return false;
    }

    /**
     * Method that allows us to remove a user type object from the usersList
     *
     * @param user object to be removed
     * @return true (if the object is removed)
     */
    public boolean removeUser(User user) {
        return usersList.remove(user);
    }

    /**
     * Returns users List
     *
     * @return Users List
     */
    public List<User> getUsersList() {
        return usersList;
    }

    /**
     * Returns user's name List
     *
     * @return User's name List
     */
    public List<String> getUsersNameList() {
        List<String> userNameList = new ArrayList<>();
        if (usersList.isEmpty()) {
            userNameList.add("No users to show");
        } else {
            for (User u : usersList) {
                userNameList.add(u.getName());
            }
        }
        return userNameList;
    }

    /**
     * Obtains User by his name.
     *
     * @param name user's name.
     * @return user with the given name.
     */
    public User getUserByName(String name) {
        User user = null;
        for (User u : usersList) {
            if (u.getName().equals(name)) {
                user = u;
            }
        }
        return user;
    }

    /**
     * Get given username user
     *
     * @param userID Username
     * @return Username user
     */
    public User getUserByUserName(String userID) {
        User user = null;
        for (User u : usersList) {

            if (u.getUserName().equals(userID)) {
                user = u;
            }
        }
        return user;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRegister other = (UserRegister) obj;
        if (other.getSize() != this.getSize()) {
            return false;
        }

        for (User user : other.getUsersList()) {
            if (!usersList.contains(user)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (User a : usersList) {
            hash = 23 * hash + +(a == null ? 0 : a.hashCode());
        }
        return hash;
    }


    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
