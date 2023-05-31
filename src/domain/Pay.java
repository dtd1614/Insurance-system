package domain;

import enumeration.contract.PayStatus;
import enumeration.pay.PaymentMethod;

import java.io.Serializable;

public class Pay implements Serializable {
    private int id;
    private int contractId;
    private PaymentMethod paymentMethod;
    private int accountNumber;
    private int cardNumber;

    public Pay(int contractId, PaymentMethod paymentMethod, int accountNumber, int cardNumber) {
        this.contractId = contractId;
        this.paymentMethod = paymentMethod;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
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
