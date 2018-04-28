/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lapr.project.model.registers.EventManagerRegister;
import lapr.project.model.registers.EventRegister;
import lapr.project.model.registers.UserNotConfirmedRegister;
import lapr.project.model.registers.UserRegister;

/**
 * @author U
 */
@XmlRootElement
@Entity
public class EventCenter {

    /**
     * Event Register.
     */
    @XmlElement
    @OneToOne
    private EventRegister registerEvent;

    /**
     * Unconfirmed Register.
     */
    @XmlElement
    @OneToOne
    private UserNotConfirmedRegister userNotConfirmed;

    /**
     * User Not Confirmed Register.
     */
    @XmlElement
    @OneToOne
    private UserRegister users;

    /**
     * Event Manager Register
     */
    @XmlElement
    @OneToOne
    private EventManagerRegister managers;

    /**
     * Event Center Constructor.
     */
    public EventCenter() {

        this.userNotConfirmed = new UserNotConfirmedRegister();
        this.users = new UserRegister();
        this.managers = new EventManagerRegister();
        this.registerEvent = new EventRegister();

        User firstManager = new User("DARKFORCE", "darkforce", "darkforce@isep.ipp.pt", "123", User.Language.ENGLISH);
        users.addUser(firstManager);
        managers.addUser(firstManager);
    }

    /**
     * Check if given user is Event Manager
     *
     * @param user User
     * @return Logic value of success of operation
     */
    public boolean isEventManager(User user) {
        for (EventManager em : managers.getUsersList()) {
            if (em.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if given user is FAE
     *
     * @param user User
     * @return Logic value of success of operation
     */
    public boolean isFAE(User user) {
        for (Event event : registerEvent.getEventList()) {
            for (FAE fae : event.getFaeList().getFAEsList()) {
                if (fae.getUser().equals(user)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if given user is Organizer
     *
     * @param user User
     * @return Logic value of success of operation
     */
    public boolean isOrganizer(User user) {
        for (Event event : registerEvent.getEventList()) {
            for (Organizer organizer : event.getOrganizerRegister().getOrganizersList()) {
                if (organizer.getUser().equals(user)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns register of users.
     *
     * @return Register of users.
     */
    @XmlTransient
    public UserRegister getUserRegister() {
        return users;
    }

    /**
     * Set user register
     *
     * @param users User register
     */
    public void setUserRegister(UserRegister users) {
        this.users = users;
    }

    /**
     * Returns register of Events.
     *
     * @return Register of Events.
     */
    @XmlTransient
    public EventRegister getEventRegister() {
        return registerEvent;
    }

    /**
     * Set event register
     *
     * @param registerEvent event register
     */
    public void setEventRegister(EventRegister registerEvent) {
        this.registerEvent = registerEvent;
    }

    /**
     * Returns Unconfirmed Register.
     *
     * @return Unconfirmed Register.
     */
    @XmlTransient
    public UserNotConfirmedRegister getUserNotConfirmedRegister() {
        return userNotConfirmed;
    }

    /**
     * Set user not confirmed register
     *
     * @param userNotConfirmed User not confirmed register
     */
    public void setUserNotConfirmedRegister(UserNotConfirmedRegister userNotConfirmed) {
        this.userNotConfirmed = userNotConfirmed;
    }

    /**
     * Get event managers List
     *
     * @return Event managers List
     */
    @XmlTransient
    public EventManagerRegister getEventManagers() {
        return managers;
    }

    /**
     * Set event managers List
     *
     * @param managers event managers List
     */
    public void setEventManagers(EventManagerRegister managers) {
        this.managers = managers;
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
        final EventCenter other = (EventCenter) obj;
        if (!this.registerEvent.equals(other.registerEvent)) {
            return false;
        }

        if (!this.userNotConfirmed.equals(other.userNotConfirmed)) {
            return false;
        }
        return this.users.equals(other.users);
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + registerEvent.hashCode();
        result = 31 * result + userNotConfirmed.hashCode();
        result = 31 * result + users.hashCode();
        result = 31 * result + managers.hashCode();
        return result;
    }

    @Id
    @GeneratedValue
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
