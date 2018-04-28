/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author U
 */
@XmlRootElement
@Entity
public class Decision {

    /**
     * Logic value of actualDecision.
     */
    @XmlElement
    private boolean actualDecision;

    /**
     * Supportive text of the actualDecision.
     */
    @XmlElement
    private String justification;

    /**
     * List of ratings on a decision
     */
    @XmlElementWrapper(name = "ratings")
    @XmlElement(name = "rate")
    @OneToMany
    private List<Integer> rates;

    /**
     * FAE making the decision
     */
    @XmlElement
    @OneToOne
    private FAE fae;
    @Id
    private String id;

    /**
     * Constructor of Decision class.
     *
     * @param actualDecision Logic value of actualDecision.
     * @param justification  Supportive text of the actualDecision.
     */
    public Decision(List<Integer> rates, boolean actualDecision, String justification, FAE fae) {
        if (rates.size() == 4) {
            this.rates = rates;
        } else {
            this.rates = new ArrayList<>();
        }

        this.actualDecision = actualDecision;
        this.justification = justification;
        this.fae = fae;
    }

    /**
     * Blank Class constructor
     */
    public Decision() {
    }

    /**
     * Obtain Decision's logic value.
     *
     * @return Decision's logic value.
     */
    public boolean getActualDecision() {
        return actualDecision;
    }

    /**
     * Obtain Decision's supportive text.
     *
     * @return Decision's supportive text.
     */
    public String getJustification() {
        return justification;
    }

    /**
     * @return
     */
    public FAE getDecisionAuthor() {
        return fae;
    }

    /**
     * Get decision's rates
     *
     * @return Decision's rates
     */
    public List<Integer> getRates() {
        return rates;
    }

    /**
     * This method compares the equality of the current object with the object
     * of same type.
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
        final Decision other = (Decision) obj;
        if (this.actualDecision != other.actualDecision) {
            return false;
        }
        if (!Objects.equals(this.justification, other.justification)) {
            return false;
        }
        if (!rates.containsAll(other.rates)) {
            return false;
        }
        return Objects.equals(this.fae, other.fae);
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.actualDecision ? 1 : 0);
        hash = 83 * hash + Objects.hashCode(this.justification);
        for (int a : this.rates) {
            hash = 83 * hash + a;
        }
        hash = 83 * hash + Objects.hashCode(this.fae);
        return hash;
    }

    /**
     * Calculate average evaluation
     *
     * @return Average evaluation
     */
    public double calcAverageEvaluation() { // soma de todos os param do array Rates e retorna a média de todos eles
        Integer sum = 0;
        Integer total = 0;
        for (Integer rate : this.getRates()) { // pra cada rate em gate rates soma todos e dps divide
            sum += rate;
            total++;
        }
        return ((double) sum / ((total == 0) ? (double) 1 : (double) total));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
