package Database.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users", schema = "public")
public class Users {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "account_id")
    private int account_id;
    @Column(name = "active")
    private boolean active;
    @Column(name = "api_key")
    private String api_key;
    @Column(name = "currency_id")
    private String currency_id;

    public int getId() {
        return id;
    }
    public String username() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public int getAccount_id() {
        return account_id;
    }
    public boolean isActive() {
        return active;
    }
    public String getApi_key() {
        return api_key;
    }

}
