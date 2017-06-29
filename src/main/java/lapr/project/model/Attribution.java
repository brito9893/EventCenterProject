package lapr.project.model;


import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Created by Mário Vaz
 */
@XmlRootElement
public class Attribution {

    /**
     * FAE of the Attribution.
     */
    @XmlElement
    private FAE fae;

    /**
     * Application of the Attribution.
     */
    @XmlElement
    private Application application;

    /**
     * Constructor of Attribution class.
     *
     * @param fae         FAE of the Attribution.
     * @param application Application of the Attribution.
     */
    public Attribution(FAE fae, Application application) {
        this.fae = fae;
        this.application = application;
    }

    /**
     * Blank class constructor
     */
    public Attribution() {
        //xml requirement
    }

    /**
     * Obtain Application's fae.
     *
     * @return Application's fae.
     */
    public FAE getFae() {
        return fae;
    }

    /**
     * Obtain Application's application.
     *
     * @return Application's application.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Returns the hashcode
     *
     * @return : Hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.fae);
        hash = 53 * hash + Objects.hashCode(this.application);
        return hash;
    }

    /**
     * Compares two attribution objects
     *
     * @param obj : Other attribution object
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
        final Attribution other = (Attribution) obj;
        return Objects.equals(this.fae, other.fae) && Objects.equals(this.application, other.application);
    }
}
