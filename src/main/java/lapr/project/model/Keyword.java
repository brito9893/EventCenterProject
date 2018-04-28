package lapr.project.model;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Represents a keyword.
 *
 * @author by Nuno Bettencourt [nmb@isep.ipp.pt] on 29/05/16.
 */
@XmlRootElement
@Embeddable
public class Keyword {

    /**
     * Keyword representation.
     */
    @XmlElement
    @Id
    private String value = "";

    /**
     * Constructor for Keyword Class.
     *
     * @param keyword Keyword being used.
     */
    public Keyword(String keyword) {
        this.value = keyword;
    }

    /**
     * Empty Keyword Class constructor
     */
    public Keyword() {
    }

    /**
     * Obtain keyword value.
     *
     * @return Keyword Value
     */
    private String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the value of the keyword object
     *
     * @return : keyword in string
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    /**
     * Compares to keywords and returns if they are equal in true of false form.
     *
     * @param o : Other object
     * @return : Result of comparision in true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Keyword)) {
            return false;
        }

        Keyword that = (Keyword) o;

        return getValue().equals(that.getValue());
    }
}
