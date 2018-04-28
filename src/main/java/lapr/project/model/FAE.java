package lapr.project.model;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an event FAE.
 *
 * @author Luis Cunha on 01/06/2017
 */
@XmlRootElement
@Entity
public class FAE implements Serializable {

    private static final long serialVersionUID = 4207992249285182060L;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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

    /**
     * Returns User
     *
     * @return user
     */
    @OneToOne
    public User getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
