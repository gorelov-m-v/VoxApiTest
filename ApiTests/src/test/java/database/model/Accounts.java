package database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="accounts", schema = "billing")
public class Accounts {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    private BigDecimal value;
    @Column(name = "credit_limit")
    private BigDecimal credit_limit;

    public int getId() {
        return id;
    }
    public BigDecimal getValue() {
        return value;
    }
    public BigDecimal getCredit_limit() {
        return credit_limit;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public void setCredit_limit(BigDecimal credit_limit) {
        this.credit_limit = credit_limit;
    }
}
