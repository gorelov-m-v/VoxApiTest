package database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="currencies", schema = "billing")
public class Currency {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "code", columnDefinition = "bpchar(3)", length = 3)
    private String code;
    @Column(name = "symbol", columnDefinition = "bpchar(1)", length = 1)
    private String symbol;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
