/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lapr.project.model.EventManager;
import lapr.project.model.User;

/**
 * @author U
 */
@XmlRootElement
public class EventManagerRegister {


    /**
     * ArrayList that stores user type objects
     */
    @XmlElementWrapper(name = "EventManagers")
    @XmlElement(name = "EventManager")
    private final List<EventManager> eventManagerList;


    /**
     * Build an instance of UserRegister
     */
    public EventManagerRegister() {
        eventManagerList = new ArrayList<>();

    }

    /**
     * Method that allows us to get the size of the USER_LIST
     *
     * @return Integer corresponding to the size of the USER_LIST
     */
    public int getSize() {
        return eventManagerList.size();
    }

    /**
     * Method that allows us to add a user type object to the eventManagerList
     *
     * @param user object to be added
     * @return true (if the object is added) or false
     */
    public boolean addUser(User user) {
        EventManager manager = new EventManager(user);
        if (!eventManagerList.contains(manager)) {
            return eventManagerList.add(manager);
        }
        return false;
    }

    /**
     * Method that allows us to remove a user type object from the eventManagerList
     *
     * @param user object to be removed
     * @return true (if the object is removed)
     */
    public boolean removeUser(EventManager user) {
        return eventManagerList.remove(user);
    }


    /**
     * returns the eventManagerList
     *
     * @return eventManagerList
     */
    public List<EventManager> getUsersList() {
        return eventManagerList;
    }

    /**
     * Obtains User by his name.
     *
     * @param name user's name.
     * @return user with the given name.
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
        final EventManagerRegister other = (EventManagerRegister) obj;
        if (other.getSize() != this.getSize()) {
            return false;
        }

        for (EventManager user : other.getUsersList()) {
            if (!eventManagerList.contains(user)) {
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
        for (EventManager a : eventManagerList) {
            hash = 23 * hash + (a == null ? 0 : a.hashCode());
        }
        return hash;
    }


}
