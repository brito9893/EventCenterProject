package lapr.project.model.registers;

import java.util.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.model.*;

/**
 * Application Registry
 * <p>
 * Created by vitor on 06/06/2017.
 */
@XmlRootElement
public class ApplicationList {

    /**
     * ArrayList that contains Event Applications.
     */
    @XmlElementWrapper(name = "Applications")
    @XmlElement(name = "Application")
    private List<Application> listApplication;

    /**
     * Class constructor
     */
    public ApplicationList() {
        this.listApplication = new ArrayList<>();
    }

    /**
     * Add a new application to list.
     *
     * @param a Application to be added.
     * @return Logic value referent to the success of the operation.
     */
    public boolean addApplication(Application a) {
        if (listApplication.indexOf(a) == -1) {
            return listApplication.add(a);
        }
        return false;
    }

    /**
     * Create a new Application.
     *
     * @param companyRepresentative company's Representative.
     * @param company               company's name.
     * @param description           application's description.
     * @param invites               application's invites
     * @param area                  stand's area
     * @param keywordList           application's keyword list.
     * @return Application created.
     */
    public Application createApplication(Representative companyRepresentative, String company, String description, int invites, int area, KeywordList keywordList) {
        return new Application(companyRepresentative, company, description, invites, area, keywordList);
    }

    /**
     * Removes an application from the list.
     *
     * @param a Application to be removed.
     */
    public boolean removeApplication(Application a) {

        return listApplication.remove(a);

    }

    /**
     * Obtains list of applications.
     *
     * @return Returns list of applications.
     */
    public List<Application> getApplicationList() {
        return listApplication;
    }

    /**
     * Obtains list of applications' names.
     *
     * @return Returns list of applications' names.
     */
    public List<String> getApplicationNameList() {
        List<String> applicationNameList = new ArrayList<>();
        if (listApplication.isEmpty()) {
            applicationNameList.add("No applications to show");
        } else {
            for (Application a : listApplication) {
                applicationNameList.add(a.getCompany());
            }
        }
        return applicationNameList;
    }

    /**
     * Returns the number os elements in the list.
     *
     * @return Number of elements.
     */
    public int getSize() {
        return listApplication.size();
    }

    /**
     * Obtains Application by his name.
     *
     * @param applicationID application's name.
     * @return application with the given name.
     */
    public Application getApplication(String applicationID) {
        Application application = null;
        for (Application a : listApplication) {
            if (Objects.equals(a.getCompany(), applicationID)) {
                application = a;
            }
        }
        return application;
    }

    /**
     * return the number of accepted applications
     *
     * @return counter
     */
    public int getNumberOfAcceptedApplications() {
        int counter = 0;
        for (Application ap : listApplication) {
            if (ap.getDecision()) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Return application keywords
     *
     * @return application keywords
     */
    public Map<String, Double> getKeywords() {
        List<Keyword> keywords = new ArrayList<>();

        listApplication.forEach(e -> keywords.addAll(e.getKeywordList().getKeywordsList()));
        Map<String, Double> keyStringMap = new HashMap<>();

        keywords.forEach(k -> {
            String key = k.toString();
            if (!keyStringMap.containsKey(key)) {
                keyStringMap.put(key, 1.0);
            } else {
                double count = keyStringMap.get(key);
                keyStringMap.replace(key, ++count);
            }
        });
        keyStringMap.keySet().forEach(k -> keyStringMap.replace(k, keyStringMap.get(k) / (double) keywords.size()));

        return keyStringMap;
    }

    /**
     * Get FAE decisions List
     *
     * @param fae FAE
     * @return FAE decisions List
     */
    public List<Decision> getFaeDecisions(FAE fae) {
        List<Decision> decision = new ArrayList<>();
        for (Application a : listApplication) {
            for (Decision b : a.getDecisionList().getDecisionsList()) {
                if (b.getDecisionAuthor().equals(fae)) {
                    decision.add(b);
                }
            }
        }
        return decision;
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
        if (!(o instanceof ApplicationList)) {
            return false;
        }

        ApplicationList that = (ApplicationList) o;

        return this.listApplication.containsAll(that.getApplicationList());
    }

    /**
     * Return hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (Application a : listApplication) {
            hash = 31 * hash + +(a == null ? 0 : a.hashCode());
        }
        return hash;
    }

}
