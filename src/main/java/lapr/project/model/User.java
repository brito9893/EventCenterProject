package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import lapr.project.utils.FileCipher;
import lapr.project.utils.PasswordEncoder;

/**
 * Represents a user.
 *
 * @author Luís Cunha on 01/06/2017
 */
@XmlRootElement
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -642375789393957775L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    /**
     * FileCipher for encryption.
     */
    @XmlElement(name = "key")
    @OneToOne
    private FileCipher encrypter;

    public String getId() {
        return id;
    }

    /**
     * User default language
     */
    private static Language defaultLanguage = Language.ENGLISH;
    /**
     * User's Username.
     */
    @XmlElement
    private String userName;
    /**
     * User's name.
     */
    @XmlElement
    private String name;
    /**
     * User's Email.
     */
    @XmlElement
    private String email;
    /**
     * User's password.
     */
    @XmlElement
    private String password;
    /**
     * User's languageString.
     */
    @XmlElement
    private Language language;

    public void setId(String id) {
        this.id = id;
    }


    /**
     * User Class Constructor with individual parameters.
     *
     * @param name     user's name
     * @param userName User's username
     * @param email    User's email
     * @param password User's password
     * @param language User's languageString
     */
    public User(String name, String userName, String email, String password, Language language) {
        PasswordEncoder encoder = new PasswordEncoder(password);
        this.password = encoder.toString();
        String keyword = UUID.randomUUID().toString().substring(1, 5);
        this.encrypter = new FileCipher(keyword);
        this.name = encrypter.cipher(name);
        this.userName = encrypter.cipher(userName);
        this.email = encrypter.cipher(email);

        this.language = language;
    }

    /**
     * User Class Constructor with individual parameters without language.
     *
     * @param name     user's name
     * @param userName User's username
     * @param email    User's email
     * @param password User's password
     */
    public User(String name, String userName, String email, String password) {
        PasswordEncoder encoder = new PasswordEncoder(password);
        String keyword = UUID.randomUUID().toString().substring(1, 5);

        this.encrypter = new FileCipher(keyword);
        this.name = encrypter.cipher(name);
        this.userName = encrypter.cipher(userName);
        this.email = encrypter.cipher(email);
        this.password = encoder.toString();
        this.language = defaultLanguage;
    }

    /**
     * User Empty Class Constructor
     */
    public User() {
    }

    /**
     * Returns Email.
     *
     * @return user's email
     */
    public String getEmail() {
        return encrypter.decipher(email).trim();
    }

    /**
     * Returns User's Name
     *
     * @return nome
     */
    public String getName() {
        return encrypter.decipher(name).trim();
    }

    /**
     * Returns user's username.
     *
     * @return userID
     */
    public String getUserName() {
        return encrypter.decipher(userName).trim();
    }

    /**
     * Changes user's username.
     *
     * @return Language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Check if password is correct
     *
     * @param pw Password
     * @return Logic value of success of operation
     */
    public boolean isPasswordCorrect(String pw) {
        PasswordEncoder pass = new PasswordEncoder(pw);
        return password.equals(pass.toString());
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
        final User other = (User) obj;
        if (!Objects.equals(this.getEmail(), other.getEmail())) {
            return false;
        }
        return Objects.equals(this.getUserName(), other.getUserName());
    }

    /**
     * Return hashCode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(getUserName());
        hash = 53 * hash + Objects.hashCode(getName());
        hash = 53 * hash + Objects.hashCode(getEmail());
        return hash;
    }

    /**
     * Language
     */
    @XmlEnum
    public enum Language {
        ENGLISH("English"), PORTUGUESE("Portuguese");

        private final String languageString;


        Language(String languageString) {
            this.languageString = languageString;
        }

        @Override
        public String toString() {
            return languageString;
        }

    }
}
