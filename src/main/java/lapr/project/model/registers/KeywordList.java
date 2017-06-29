package lapr.project.model.registers;

import lapr.project.model.Keyword;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mário Vaz on 06-Jun-17.
 */
@XmlRootElement
public class KeywordList {

    /**
     * ArrayList that contains Application Keywords.
     */
    @XmlElementWrapper(name = "Keywords")
    @XmlElement(name = "key")
    private final List<Keyword> keywords;

    /**
     * KeywordList Class Constructor
     */
    public KeywordList() {
        keywords = new ArrayList<>();
    }

    /**
     * KeywordList Class Constructor with given keywords
     *
     * @param keys Keywords
     */
    public KeywordList(List<Keyword> keys) {
        keywords = new ArrayList<>(keys);
    }

    /**
     * Returns the number os elements in the list.
     *
     * @return Number of elements.
     */
    public int getSize() {
        return keywords.size();
    }

    /**
     * Add a new keyword to list.
     *
     * @param keyword Keyword to be added.
     * @return Logic value referent to the success of the operation.
     */
    public boolean addKeyword(Keyword keyword) {
        if (!keywords.contains(keyword)) {
            return keywords.add(keyword);
        }
        return false;
    }

    /**
     * Removes a Keyword from the list.
     *
     * @param keyword Keyword to be removed.
     * @return Logic value referent to the success of the operation.
     */
    public boolean removeKeyword(Keyword keyword) {
        return keywords.remove(keyword);
    }

    /**
     * Obtains List of Keywords.
     *
     * @return Returns List of Keywords.
     */
    public List<Keyword> getKeywordsList() {
        return new ArrayList<>(keywords);
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
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof KeywordList))
            return false;

        KeywordList that = (KeywordList) o;

        return keywords.containsAll(that.keywords);
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    public int hashCode() {
        int hash = 7;
        for (Keyword a : keywords) {
            hash = 31 * hash + (a == null ? 0 : a.hashCode());
        }
        return hash;
    }
}
