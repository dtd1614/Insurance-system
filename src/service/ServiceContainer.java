package service;

import repository.*;

import java.rmi.RemoteException;

public class ServiceContainer {
    private final AccidentService accidentService;
    private final CalculationFormulaService calculationFormulaService;
    private final CompensateService compensateService;
    private final ContractService contractService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final InfoService infoService;
    private final InsuranceService insuranceService;
    private final PayService payService;
    private final PolicyService policyService;
    private final SaleService saleService;


    public ServiceContainer() throws RemoteException {
        this.accidentService = new AccidentService(new AccidentList());
        this.calculationFormulaService = new CalculationFormulaService(new CalculationFormulaList());
        this.compensateService = new CompensateService(new CompensationList());
        this.contractService = new ContractService(new ContractList());
        this.customerService = new CustomerService(new CustomerList());
        this.employeeService = new EmployeeService(new EmployeeList());
        this.infoService = new InfoService(new InfoList());
        this.insuranceService = new InsuranceService(new InsuranceList());
        this.payService = new PayService(new PayList());
        this.policyService = new PolicyService(new PolicyList());
        this.saleService = new SaleService(new SaleList());
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

    public InfoService getInfoService() {
        return infoService;
    }

    public InsuranceService getInsuranceService() {
        return insuranceService;
    }

    public PayService getPayService() {
        return payService;
    }

    public PolicyService getPolicyService() {
        return policyService;
    }

    public SaleService getSaleService() {
        return saleService;
    }
}
