package lapr.project.model;


import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an event FAE.
 *
 * @author Luis Cunha on 01/06/2017
 */
@XmlRootElement
public class FAE {

    /**
     * FAE user
     */
    @XmlElement
    private User user;

    /**
     * FAE empty constructor
     */
    public FAE() {
    }

    /**
     * FAE constructor
     *
     * @param user User
     */
    public FAE(User user) {
        this.user = user;
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
        FAE other = (FAE) obj;
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
}
