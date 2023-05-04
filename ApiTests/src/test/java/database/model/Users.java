package database.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="users", schema = "public")
public class Users {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "account_id")
    private int account_id;
    @Column(name = "activated")
    private LocalDate activated;
    @Column(name = "active")
    private boolean active;
    @Column(name = "api_key")
    private String api_key;
    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    @Column(name = "is_credit")
    private boolean isCredit;

    public int getId() {
        return id;
    }
    public String username() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public int getAccount_id() {
        return account_id;
    }
    public LocalDate getActivated() {
        return activated;
    }
    public boolean isActive() {
        return active;
    }
    public String getApi_key() {
        return api_key;
    }
    public String getUsername() {
        return username;
    }
    public Currency getCurrency() {
        return currency;
    }
    public boolean isCredit() {
        return isCredit;
    }
}
