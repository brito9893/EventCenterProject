package lapr.project.model.registers;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.model.Attribution;
import lapr.project.model.FAE;

/**
 * Created by Mário Vaz on 06-Jun-17.
 */
@XmlRootElement
@Entity
public class AttributionList {

    /**
     * ArrayList that contains Event Attributions.
     */
    @XmlElementWrapper(name = "Attributions")
    @XmlElement(name = "Attribution")
    @OneToMany
    private final List<Attribution> attributionRegister;
    @Id
    private String id;

    /**
     * AttributionList Class Constructor
     */
    public AttributionList() {
        attributionRegister = new ArrayList<>();
    }

    /**
     * Returns the number os elements in the list.
     *
     * @return Number of elements.
     */
    public int getSize() {
        return attributionRegister.size();
    }

    /**
     * Add a new attribution to list.
     *
     * @param attribution Attribution to be added.
     * @return Logic value referent to the success of the operation.
     */
    public boolean addAttribution(Attribution attribution) {
        return attributionRegister.indexOf(attribution) == -1 && attributionRegister.add(attribution);
    }

    /**
     * Removes an Attribution from the list.
     *
     * @param attribution Attribution to be removed.
     * @return Logic value referent to the success of the operation.
     */
    public boolean removeAttribution(Attribution attribution) {
        return attributionRegister.remove(attribution);
    }

    /**
     * Obtains the List of Attributions.
     *
     * @return Returns List of Attributions.
     */
    public List<Attribution> getAttributionsList() {
        return attributionRegister;
    }

    /**
     * Check if FAE has attribution
     *
     * @param fae FAE
     * @return Logic value of success of operation
     */
    public boolean faeHasAttribution(FAE fae) {
        for (Attribution a : attributionRegister) {
            if (a.getFae().equals(fae)) {
                return true;
            }
        }
        return false;
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
        if (!(o instanceof AttributionList)) {
            return false;
        }

        AttributionList that = (AttributionList) o;

        return attributionRegister.containsAll(that.attributionRegister);
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (Attribution a : attributionRegister) {
            hash = 31 * hash + +(a == null ? 0 : a.hashCode());
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
