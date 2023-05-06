package model.database;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="sms_history", schema = "public")
public class SmsHistory {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "source_number")
    private String source_number;
    @Column(name = "destination_number")
    private String destination_number;
    @Column(name = "external_id")
    private List<String> external_id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "direction")
    private String direction;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "transaction_id")
    private Integer transaction_id;

    public int getId() {
        return id;
    }
    public String getSource_number() {
        return source_number;
    }
    public String getDestination_number() {
        return destination_number;
    }
    public List<String> getExternal_id() {
        return external_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public String getDirection() {
        return direction;
    }
    public String getUuid() {
        return uuid;
    }
    public Integer gettransaction_id() {
        return transaction_id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setSource_number(String source_number) {
        this.source_number = source_number;
    }
    public void setDestination_number(String destination_number) {
        this.destination_number = destination_number;
    }
    public void setExternal_id(List<String> external_id) {
        this.external_id = external_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public void settransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }
}
