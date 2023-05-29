package domain;

import enumeration.pay.PayStatus;
import enumeration.pay.PaymentMethod;

import java.io.Serializable;

public class Pay implements Serializable {
    private int id;
    private int contractId;
    private PaymentMethod paymentMethod;
    private int accountNumber;
    private int cardNumber;
    private PayStatus status;

    public Pay(int contractId, PaymentMethod paymentMethod, int accountNumber, int cardNumber, PayStatus status) {
        this.contractId = contractId;
        this.paymentMethod = paymentMethod;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
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

    public PayStatus getStatus() {
        return status;
    }

    public void setStatus(PayStatus status) {
        this.status = status;
    }

	public boolean paied() {
		this.setStatus(PayStatus.FullPayment);
		return true;
		
	}
}
