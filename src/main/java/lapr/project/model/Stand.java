package lapr.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Mário Vaz
 */
@XmlRootElement
@Entity
public class Stand {
    /**
     * Stand identification number
     */
    @XmlElement
    private int standID;

    /**
     * Stand's area in m^2.
     */
    @XmlElement
    private int area;

    /**
     * Stand's standDescription.
     */
    @XmlElement
    private String standDescription;
    @Id
    private String id;

    /**
     * Stand Class Constructor.
     *
     * @param area             stand's area.
     * @param standDescription stand's standDescription.
     */
    public Stand(int area, String standDescription, int idStand) {
        this.area = area;
        this.standDescription = standDescription;
        this.standID = idStand;
    }

    /**
     * Empty Class constructor
     */
    public Stand() {
    }

    /**
     * Returns the stand ID
     *
     * @return stand ID
     */
    public int getStandID() {
        return standID;
    }

    /**
     * Returns area.
     *
     * @return stand's area.
     */
    public int getArea() {
        return area;
    }

    /**
     * Returns standDescription.
     *
     * @return stand's standDescription.
     */
    public String getDescription() {
        return standDescription;
    }

    /**
     * Sets the standDescription of the stand
     *
     * @param standDescription : New standDescription to be set
     */
    public void setDescription(String standDescription) {
        this.standDescription = standDescription;
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
        if (o == null) {
            return false;
        }
        if (!(o instanceof Stand)) {
            return false;
        }

        Stand stand = (Stand) o;

        if (area != stand.area) {
            return false;
        }
        return standDescription.equals(stand.standDescription);
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = area;
        result = 31 * result + standDescription.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
