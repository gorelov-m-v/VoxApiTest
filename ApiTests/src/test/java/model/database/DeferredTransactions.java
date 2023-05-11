package model.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="deferred_transactions", schema = "billing")
public class DeferredTransactions {
    @Id
    @Column(name = "transaction_id")
    private Integer transaction_id;
    @Column(name = "account_id")
    private int account_id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "description")
    private String description;
    @Column(name = "committed")
    private Boolean committed;

    public Integer getTransaction_id() {
        return transaction_id;
    }
    public int getAccount_id() {
        return account_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public String getDescription() {
        return description;
    }
    public Boolean getCommitted() {
        return committed;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCommitted(Boolean committed) {
        this.committed = committed;
    }
}
