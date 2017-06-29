package lapr.project.model.registers;

import lapr.project.model.FAE;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mário Vaz on 06-Jun-17.
 */
@XmlRootElement
public class FAEList {

    /**
     * ArrayList that contains Event FAEs.
     */
    //
    @XmlElementWrapper(name = "FaeList")
    @XmlElement(name = "FAE")
    private final List<FAE> faesList;

    /**
     * FAEList Class Constructor
     */
    public FAEList() {
        faesList = new ArrayList<>();
    }

    /**
     * Returns the number os elements in the list.
     *
     * @return Number of elements.
     */
    public int getSize() {
        return faesList.size();
    }

    /**
     * Add a new fae to list.
     *
     * @param fae FAE to be added.
     * @return Logic value referent to the success of the operation.
     */
    public boolean addFAE(FAE fae) {
        return faesList.indexOf(fae) == -1 && faesList.add(fae);
    }

    /**
     * Removes a FAE from the list.
     *
     * @param fae FAE to be removed.
     * @return Logic value referent to the success of the operation.
     */
    public boolean removeFAE(FAE fae) {
        return faesList.remove(fae);
    }

    /**
     * Obtains List of FAEs.
     *
     * @return Returns List of FAEs.
     */
    public List<FAE> getFAEsList() {
        return faesList;
    }

    /**
     * Obtains List of FAEs name
     *
     * @return List of FAEs name
     */
    public List<String> getFAEsNameList() {
        List<String> faeNameList = new ArrayList<>();
        if (faesList.isEmpty()) {
            faeNameList.add("No FAEs to show");
        } else {
            for (FAE f : faesList) {
                faeNameList.add(f.getUser().getName());
            }
        }
        return faeNameList;
    }

    /**
     * Check if FAE given is a real FAE
     *
     * @param fae FAE
     * @return logic value of the success of operation
     */
    public boolean isFae(FAE fae) {
        return faesList.contains(fae);
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
        if (!(o instanceof FAEList)) {
            return false;
        }

        FAEList faeList = (FAEList) o;

        return faesList.containsAll(faeList.faesList);
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (FAE a : faesList) {
            hash = 31 * hash + +(a == null ? 0 : a.hashCode());
        }
        return hash;
    }
}
