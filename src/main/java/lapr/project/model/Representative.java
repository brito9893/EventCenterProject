package lapr.project.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vitor and luis on 08/06/2017.
 */
@XmlRootElement
public class Representative {

    /**
     * User
     */
    @XmlElement
    private User user;

    /**
     * Representative class constructor that receives information from an User object
     *
     * @param user : Holds the information for the representative object
     */
    public Representative(User user) {
        this.user = user;
    }

    /**
     * Empty Class constructor
     */
    public Representative() {
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
     * Method compares to representatives
     *
     * @param o
     * @return : True if the two objects are the same
     * False if the two objects are different
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (!(o instanceof Representative)) {
            return false;
        }

        Representative other = (Representative) o;

        return Objects.equals(this.getUser(), other.getUser());
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = 11;
        result = 31 * result + user.hashCode();
        return result;
    }
}
