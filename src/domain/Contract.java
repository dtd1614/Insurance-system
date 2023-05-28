package domain;

import enumeration.contract.ContractStatus;
import enumeration.contract.ContractTerm;
import enumeration.contract.PaymentCycle;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Contract implements Serializable {
    private int id;
    private String infoId;
    private int customerId;
    private int insuranceId;
    private int saleEmployeeId;
    private ContractTerm term;
    private Timestamp startDate;
    private Timestamp expirationDate;
    private int paymentFee;
    private PaymentCycle paymentCycle;
    private Timestamp paymentDeadline;
    private int compensation;
    private ContractStatus status;

    public Contract(String infoId, int insuranceId, int saleEmployeeId,int customerId, ContractTerm term, int paymentFee, PaymentCycle paymentCycle, int compensation, ContractStatus status) {
        this.infoId = infoId;
        this.insuranceId = insuranceId;
        this.saleEmployeeId = saleEmployeeId;
        this.customerId=customerId;
        this.term = term;
        this.paymentFee = paymentFee;
        this.paymentCycle = paymentCycle;
        this.compensation = compensation;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getSaleEmployeeId() {
        return saleEmployeeId;
    }

    public void setSaleEmployeeId(int saleEmployeeId) {
        this.saleEmployeeId = saleEmployeeId;
    }

    public ContractTerm getTerm() {
        return term;
    }

    public void setTerm(ContractTerm term) {
        this.term = term;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(int paymentFee) {
        this.paymentFee = paymentFee;
    }

    public PaymentCycle getPayCycle() {
        return paymentCycle;
    }

    public void setPayCycle(PaymentCycle paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public Timestamp getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Timestamp paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public int getCompensation() {
        return compensation;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public boolean paied() {
		int cycle=PaymentCycle.getCycle(this.getPayCycle());
		Timestamp deadline= this.getPaymentDeadline();
		LocalDateTime newDeadline = deadline.toLocalDateTime();
		newDeadline=newDeadline.plus(cycle,ChronoUnit.MONTHS);
		this.setPaymentDeadline(Timestamp.valueOf(newDeadline));
		return true;
	}
}
