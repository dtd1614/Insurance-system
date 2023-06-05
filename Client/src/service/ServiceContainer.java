package service;

import service.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServiceContainer {
    private AccidentServiceIF accidentService;
    private CalculationFormulaServiceIF calculationFormulaService;
    private CompensateServiceIF compensateService;
    private ContractServiceIF contractService;
    private CustomerServiceIF customerService;
    private EmployeeServiceIF employeeService;
    private InsuranceServiceIF insuranceService;
    private PayServiceIF payService;
    private SaleServiceIF saleService;

    public ServiceContainer()  {
        connect();
    }

    public void connect(){
        try {this.accidentService = (AccidentServiceIF) Naming.lookup("accidentService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}
        try {this.compensateService = (CompensateServiceIF) Naming.lookup("compensateService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}
        try {this.customerService = (CustomerServiceIF) Naming.lookup("customerService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}
        try {this.contractService = (ContractServiceIF) Naming.lookup("contractService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}
        try {this.payService = (PayServiceIF) Naming.lookup("payService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}
        try {this.calculationFormulaService = (CalculationFormulaServiceIF) Naming.lookup("calculationFormulaService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}
        try {this.insuranceService = (InsuranceServiceIF) Naming.lookup("insuranceService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}
        try {this.employeeService = (EmployeeServiceIF) Naming.lookup("employeeService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}
        try {this.saleService = (SaleServiceIF) Naming.lookup("saleService");}
        catch (NotBoundException | MalformedURLException | RemoteException e) {e.printStackTrace();}

        try {this.compensateService.setAccidentService(this.accidentService);}
        catch (RemoteException e) {e.printStackTrace();}
        try {this.contractService.setCustomerService(this.customerService);}
        catch (RemoteException e) {e.printStackTrace();}
        try {this.payService.setContractService(this.contractService);}
        catch (RemoteException e) {e.printStackTrace();}
    }

    public AccidentServiceIF getAccidentService() {return accidentService;}

    public CalculationFormulaServiceIF getCalculationFormulaService() {return calculationFormulaService;}

    public CompensateServiceIF getCompensateService() {return compensateService;}

    public ContractServiceIF getContractService() {
        return contractService;
    }

    public CustomerServiceIF getCustomerService() {
        return customerService;
    }

    public EmployeeServiceIF getEmployeeService() {
        return employeeService;
    }

    public InsuranceServiceIF getInsuranceService() {
        return insuranceService;
    }

    public PayServiceIF getPayService() {
        return payService;
    }
    
    public SaleServiceIF getSaleService() {
        return saleService;
    }

}
