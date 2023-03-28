package Database.model.publicschema;


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
}
