package domain;

import java.io.Serializable;

public class Pay implements Serializable {
    private int id;
    private int contractId;
    private int accountNumber;
    private int cardNumber;

    public Pay(int contractId, int accountNumber, int cardNumber) {
        this.contractId = contractId;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
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

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

//	public boolean paied() {
//		this.setStatus(PayStatus.FullPayment);
//		return true;
//	}
}
