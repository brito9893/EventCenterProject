package lapr.project.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.model.registers.DecisionList;
import lapr.project.model.registers.KeywordList;

/**
 * Application class.
 *
 * @author by Luis Cunha 08/06/2017
 */
@XmlRootElement
public class Application {

    /**
     * Application's company
     */
    @XmlElement
    private String company;

    /**
     * Keyword list
     */
    @XmlElement
    private KeywordList Listkeyword;

    /**
     * Application's applicationDescription
     */
    @XmlElement
    private String applicationDescription;

    /**
     * Representative for the company
     */
    @XmlElement
    private Representative companyRepresentative;

    /**
     * List of decisions on the application
     */
    @XmlElement
    private DecisionList listDecision;

    /**
     * Stand assigned
     */
    @XmlElement
    private Stand appStand;

    /**
     * Stand invites
     */
    @XmlElement
    private int numberInvites;

    /**
     * Application stand's area
     */
    @XmlElement
    private int appStandArea;

    /**
     * Application decision logic value
     */
    @XmlElement
    private boolean applicationDecision;

    /**
     * Constructor for Application
     *
     * @param companyRepresentative  Company Representative.
     * @param company                Company represented.
     * @param applicationDescription ApplicationDescription
     * @param Listkeyword            Keyword List
     */
    public Application(Representative companyRepresentative, String company, String applicationDescription, int invites, int appStandArea, KeywordList Listkeyword) {
        this.companyRepresentative = companyRepresentative;
        this.company = company;
        this.applicationDescription = applicationDescription;
        this.Listkeyword = Listkeyword;
        this.numberInvites = invites;
        this.appStandArea = appStandArea;
        this.listDecision = new DecisionList(this);
        this.appStand = null;
        this.applicationDecision = false;
    }

    /**
     * Class blank constructor
     */
    public Application() {

    }

    /**
     * Obtain Application's applicationDescription.
     *
     * @return Application applicationDescription
     */
    public String getDescription() {
        return applicationDescription;
    }

    /**
     * Sets the applicationDescription of the application
     *
     * @param applicationDescription : Input applicationDescription
     */
    public void setDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    /**
     * Returns the last decision in the list of decisions
     *
     * @return decision
     */
    public boolean getDecision() {
        updateDecision();
        return applicationDecision;
    }

    /**
     * Get stand's invites
     *
     * @return stand's invites
     */
    public int getInvites() {
        return numberInvites;
    }

    /**
     * Set stand's invites
     *
     * @param invites stand's invites
     */
    public void setInvites(int invites) {
        this.numberInvites = invites;
    }

    /**
     * Get stand's area
     *
     * @return stand's area
     */
    public int getStandArea() {
        return appStandArea;
    }

    /**
     * Set stand's area
     *
     * @param appStandArea stand's area
     */
    public void setStandArea(int appStandArea) {
        this.appStandArea = appStandArea;
    }

    /**
     * Add a keyword to Application.
     *
     * @param keyword Keyword to be added.
     */
    public void addKeyword(Keyword keyword) {
        getKeywordList().addKeyword(keyword);
    }

    /**
     * Obtain the list of existing keywords.
     *
     * @return A list of existing keywords.
     */
    public KeywordList getKeywordList() {
        return Listkeyword;
    }

    /**
     * Defines the list of keywords
     *
     * @param Listkeyword : Input list of keywords
     */
    public void setKeywordList(KeywordList Listkeyword) {
        this.Listkeyword = Listkeyword;
    }

    /**
     * Obtains application's company
     *
     * @return Company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Obtains the application representative
     *
     * @return Representative
     */
    public Representative getRepresentative() {
        return companyRepresentative;
    }

    /**
     * Obtains the appStand assigned to the application
     *
     * @return : Stand
     */
    public Stand getStand() {
        return appStand;
    }

    /**
     * Defines the appStand for the input
     *
     * @param appStand : New Stand
     */
    public void setStand(Stand appStand) {
        this.appStand = appStand;
    }

    /**
     * Obtains the application's decision list
     *
     * @return Decision List
     */
    public DecisionList getDecisionList() {
        return listDecision;
    }

    /**
     * Defines the company for the input
     *
     * @param companyInput : New company
     */
    public void setCompanyName(String companyInput) {
        this.company = companyInput;
    }

    /**
     * Returns the hashcode
     *
     * @return : Hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.company);
        hash = 13 * hash + Objects.hashCode(this.Listkeyword);
        hash = 13 * hash + Objects.hashCode(this.applicationDescription);
        hash = 13 * hash + Objects.hashCode(this.companyRepresentative);
        hash = 13 * hash + Objects.hashCode(this.listDecision);
        return hash;
    }

    /**
     * Compares two application objects
     *
     * @param obj : Other application object
     * @return : Result -> True or False
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
        final Application other = (Application) obj;

        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.applicationDescription, other.applicationDescription)) {
            return false;
        }
        if (!Objects.equals(this.Listkeyword, other.Listkeyword)) {
            return false;
        }
        if (!Objects.equals(this.companyRepresentative, other.companyRepresentative)) {
            return false;
        }
        if (!Objects.equals(this.applicationDecision, other.applicationDecision)) {
            return false;
        }
        return Objects.equals(this.listDecision, other.listDecision);
    }

    /**
     * Update decision
     */
    private void updateDecision() {
        if (listDecision.getSize() > 0) {
            applicationDecision = listDecision.getDecisionsList().get(listDecision.getSize() - 1).getActualDecision();
        } else {
            applicationDecision = false;
        }
    }

}
