/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Luis Cunha on 16/06/2017
 */
@XmlRootElement
@Entity
public class EventManager {

    /**
     * User
     */
    @XmlElement
    @OneToOne
    private User user;

    /**
     * Class constructor
     *
     * @param user User
     */
    public EventManager(User user) {
        this.user = user;
    }

    /**
     * Blank class constructor
     */
    public EventManager() {
        //xml requirement
    }

    /**
     * Returns User
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * This method compares the equality of the current object with the object of same type.
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
        EventManager other = (EventManager) obj;
        return Objects.equals(this.getUser(), other.getUser());
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.user);
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
