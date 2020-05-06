package BDExchange.Domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class User {

    @Id @GeneratedValue
    private int id;

    @Email(message = "Email must be valid")
    @Column(unique=true)
    @Length(max = 100)
    private String emailaddress;

    private String password;

    public User() { }
    public User(String emailaddress, String password) {
        this.emailaddress = emailaddress;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username = " + emailaddress +
                ", password =" + password;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
