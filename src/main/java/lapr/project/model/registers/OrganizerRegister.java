package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.model.Organizer;

/**
 * Organizer Regist
 * <p>
 * Created by Vitor on 03/06/2017.
 */
@XmlRootElement
@Entity
public class OrganizerRegister {

    /**
     * ArrayList that contains Organizers.
     */
    @XmlElementWrapper(name = "OrganizerList")
    @XmlElement(name = "Organizer")
    @OneToMany
    private final List<Organizer> organizerList;
    @Id
    private String id;

    /**
     * OrganizerRegister Class Constructor
     */
    public OrganizerRegister() {
        organizerList = new ArrayList<>();
    }

    /**
     * returns the organizerList
     *
     * @return organizerList
     */
    public List<Organizer> getOrganizersList() {
        return organizerList;
    }

    /**
     * Method that allows us to get the size of the organizerList
     *
     * @return Integer corresponding to the size of the organizerList
     */
    public int getSize() {
        return organizerList.size();
    }

    /**
     * Method that allows us to add a user type object to the organizerList
     *
     * @param o object to be added
     * @return true (if the object is added) or false
     */
    public boolean addOrganizer(Organizer o) {
        if (organizerList.indexOf(o) == -1) {
            return organizerList.add(o);
        }
        return false;
    }

    /**
     * Determines if organizer is on the list.
     *
     * @param o organizer to determine.
     * @return Logic value of operation.
     */
    public boolean hasOrganizer(Organizer o) {
        return organizerList.indexOf(o) != -1;
    }

    /**
     * Method that allows us to remove a o type object from the organizerList
     *
     * @param o object to be removed
     */
    public void removeOrganizer(Organizer o) {
        organizerList.remove(o);
    }

    /**
     * This method compares the equality of the current object with the object
     * of same type.
     *
     * @param o Object to be compared.
     * @return Logic value of the comparison.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizerRegister)) {
            return false;
        }

        OrganizerRegister that = (OrganizerRegister) o;

        return organizerList.containsAll(that.organizerList);
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (Organizer a : organizerList) {
            hash = 31 * hash + (a == null ? 0 : a.hashCode());
        }
        return hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
