package BDExchange.Domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
public class User {

    @Id @GeneratedValue
    private int id;

    @Email(message = "Email must be valid")
    @Column(unique=true)
    @Length(max = 100)
    private String emailaddress;

    private String password;
    private String livingAddress;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "User_delivery_options", joinColumns = @JoinColumn(name = "userid"))
    @Column(name = "deliveryoptions")
    List<DeliveryOptions> deliveryOptions = new ArrayList<>();

    public User() { }
    public User(String emailaddress, String password, String livingAddress, List<DeliveryOptions> deliveryOptionsList) {
        this.emailaddress = emailaddress;
        this.password = password;
        this.livingAddress = livingAddress;
        deliveryOptions = deliveryOptionsList;
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
