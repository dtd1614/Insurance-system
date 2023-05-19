package domain;

import enumeration.accident.AccidentStatus;

import java.io.Serializable;
import java.sql.Timestamp;

public class Accident implements Serializable {
    private int id;
    private int contractId;
    private Timestamp date;
    private String location;
    private String cause;
    private String content;
    private int damage;    //피해액
    private int accountNumber;
    private AccidentStatus status;

    public Accident(int contractId, Timestamp date, String location, String cause, String content, int damage, int accountNumber, AccidentStatus status) {
        this.contractId = contractId;
        this.date = date;
        this.location = location;
        this.cause = cause;
        this.content = content;
        this.damage = damage;
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccidentStatus getStatus() {
        return status;
    }

    public void setStatus(AccidentStatus status) {
        this.status = status;
    }
}
