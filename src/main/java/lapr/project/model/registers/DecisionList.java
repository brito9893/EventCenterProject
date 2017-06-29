package lapr.project.model.registers;

import lapr.project.model.Decision;
import lapr.project.model.FAE;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lapr.project.model.Application;

/**
 * Created by Mário Vaz on 06-Jun-17.
 */
@XmlRootElement
public class DecisionList {

    /**
     * ArrayList that contains Application Decisions.
     */
    @XmlElementWrapper(name = "Decisions")
    @XmlElement(name = "Decision")
    private List<Decision> decisions;

    /**
     * Application parent
     */
    private Application application;

    /**
     * DecisionList Class Constructor
     */
    public DecisionList(Application application) {
        decisions = new ArrayList<>();
        this.application = application;
    }

    /**
     * Blank class constructor
     */
    public DecisionList() {
        //xml requirement
    }

    /**
     * Returns the number os elements in the list.
     *
     * @return Number of elements.
     */
    public int getSize() {
        return decisions.size();
    }

    /**
     * Get application parent
     *
     * @return application parent
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Creates a new decision
     *
     * @param rates             Rates List
     * @param decision          Decision logic value
     * @param justificationText Justification text
     * @param author            FAE
     * @return new Decision
     */
    public Decision newDecision(List<Integer> rates, boolean decision, String justificationText, FAE author) {
        return new Decision(rates, decision, justificationText, author);
    }

    /**
     * Add a new decision to list.
     *
     * @param decision Decision to be added.
     * @return Logic value referent to the success of the operation.
     */
    public boolean addDecision(Decision decision) {
        if (!decisions.contains(decision)) {
            return decisions.add(decision);
        }
        return false;
    }

    /**
     * Removes a Decision from the list.
     *
     * @param decision Decision to be removed.
     * @return Logic value referent to the success of the operation.
     */
    public boolean removeDecision(Decision decision) {
        return decisions.remove(decision);
    }


    /**
     * Obtains List of Decisions.
     *
     * @return Returns List of Decisions.
     */
    public List<Decision> getDecisionsList() {
        return decisions;
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
        if (!(o instanceof DecisionList)) {
            return false;
        }

        DecisionList other = (DecisionList) o;


        return decisions.containsAll(other.getDecisionsList());
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (Decision a : decisions) {
            hash = 31 * hash + +(a == null ? 0 : a.hashCode());
        }
        return hash;
    }
}
