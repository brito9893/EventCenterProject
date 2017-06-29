package lapr.project.model.registers;

import lapr.project.model.Stand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mário Vaz on 05-Jun-17.
 */
@XmlRootElement
public class StandList {

    /**
     * ArrayList that contains Event Stands.
     */
    @XmlElementWrapper(name = "Stands")
    @XmlElement(name = "Stand")
    private final ArrayList<Stand> standsList;
    private int standsID = 0;

    /**
     * StandList Class Constructor
     */
    public StandList() {
        standsList = new ArrayList<>();
    }

    /**
     * Returns the number os elements in the list.
     *
     * @return Number of elements.
     */
    public int getSize() {
        return standsList.size();
    }

    /**
     * Add a new stand to list.
     *
     * @param stand Stand to be added.
     * @return Logic value referent to the success of the operation.
     */
    public boolean addStand(Stand stand) {
        if (!standsList.contains(stand)) {
            return standsList.add(stand);
        }
        return false;
    }

    /**
     * Create a new stand
     *
     * @param area        Stand's area
     * @param description Stand's description
     * @return new stand
     */
    public Stand newStand(int area, String description) {
        return new Stand(area, description, ++standsID);
    }

    /**
     * Removes a Stand from the list.
     *
     * @param stand Stand to be removed.
     * @return Logic value referent to the success of the operation.
     */
    public boolean removeStand(Stand stand) {
        return standsList.remove(stand);
    }

    /**
     * Obtains List of Stands.
     *
     * @return Returns List of Stands.
     */
    public List<Stand> getStandsList() {
        return standsList;
    }

    /**
     * Obtains List of stands' names
     *
     * @return List of stands' names
     */
    public List<String> getStandsNameList() {
        List<String> standNameList = new ArrayList<>();
        if (standsList.isEmpty()) {
            standNameList.add("No stands to show");
        } else {
            for (Stand s : standsList) {
                standNameList.add(Integer.toString(s.getStandID()));
            }
        }
        return standNameList;
    }

    /**
     * Obtains Stand by his ID.
     *
     * @param id stand's ID.
     * @return stand with the given ID.
     */
    public Stand getStand(int id) {
        Stand stand = null;
        for (Stand s : standsList) {
            if (Objects.equals(s.getStandID(), id)) {
                stand = s;
            }
        }
        return stand;
    }

    /**
     * Get stand areas List
     *
     * @return stand areas List
     */
    public List<Integer> getStandAreas() {
        List<Integer> areas = new ArrayList<>();
        standsList.forEach(st -> areas.add(st.getArea()));
        return areas;
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
        if (!(o instanceof StandList)) {
            return false;
        }

        StandList stands = (StandList) o;

        return standsList.containsAll(stands.standsList);
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (Stand a : standsList) {
            hash = 31 * hash + +(a == null ? 0 : a.hashCode());
        }
        return hash;
    }
}
