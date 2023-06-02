package service;

import dao.*;

import java.rmi.RemoteException;

public class ServiceContainer {
    private final AccidentService accidentService;
    private final CalculationFormulaService calculationFormulaService;
    private final CompensateService compensateService;
    private final ContractService contractService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final CustomerInfoService customerInfoService;
    private final InsuranceService insuranceService;
    private final PayService payService;
    private final SaleService saleService;


    public ServiceContainer() throws RemoteException {
        this.accidentService = new AccidentService(new AccidentDao());
        this.compensateService = new CompensateService(new CompensationDao());
        this.customerService = new CustomerService(new CustomerDao());
        this.customerInfoService = new CustomerInfoService(new CustomerInfoDao());
        this.contractService = new ContractService(new ContractDao());
        this.payService = new PayService(new PayDao());
        this.calculationFormulaService = new CalculationFormulaService(new CalculationFormulaDao());
        this.insuranceService = new InsuranceService(new InsuranceDao());
        this.employeeService = new EmployeeService(new EmployeeDao());
        this.saleService = new SaleService(new SaleDao());

        this.compensateService.setAccidentService(this.accidentService);

        this.customerInfoService.setCustomerService(this.customerService);
        this.contractService.setCustomerInfoService(this.customerInfoService);
        this.payService.setContractService(this.contractService);
    }

    public AccidentService getAccidentService() {
        return accidentService;
    }

    public CalculationFormulaService getCalculationFormulaService() {
        return calculationFormulaService;
    }

    public CompensateService getCompensateService() {
        return compensateService;
    }

    public ContractService getContractService() {
        return contractService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public CustomerInfoService getInfoService() {
        return customerInfoService;
    }

    public InsuranceService getInsuranceService() {
        return insuranceService;
    }

    public PayService getPayService() {
        return payService;
    }
    
    public SaleService getSaleService() {
        return saleService;
    }
}
