package lapr.project.model;


import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Represents an event organizer
 *
 * @author Luís Cunha on 01/06/2017
 */
@XmlRootElement
public class Organizer {

    /**
     * Organizer's user
     */
    @XmlElement
    private User user;

    /**
     * Organizer Constructor with user.
     *
     * @param user variavel do tipo User
     */
    public Organizer(User user) {
        this.user = user;
    }

    /**
     * Blank class constructor
     */
    public Organizer() {
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
        final Organizer other = (Organizer) obj;
        return Objects.equals(this.getUser(), other.getUser());
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.user);
        return hash;
    }
}
