package service;

import dao.*;

public class ServiceContainer {
    private final AccidentServiceIF accidentService;
    private final CalculationFormulaServiceIF calculationFormulaService;
    private final CompensateServiceIF compensateService;
    private final ContractServiceIF contractService;
    private final CustomerServiceIF customerService;
    private final EmployeeServiceIF employeeService;
    private final InsuranceServiceIF insuranceService;
    private final PayServiceIF payService;
    private final SaleServiceIF saleService;


    public ServiceContainer() throws Exception {
        this.accidentService = new AccidentService(new AccidentDao());
        this.compensateService = new CompensateService(new CompensationDao());
        this.customerService = new CustomerService(new CustomerDao());
        this.contractService = new ContractService(new ContractDao());
        this.payService = new PayService(new PayDao());
        this.calculationFormulaService = new CalculationFormulaService(new CalculationFormulaDao());
        this.insuranceService = new InsuranceService(new InsuranceDao());
        this.employeeService = new EmployeeService(new EmployeeDao());
        this.saleService = new SaleService(new SaleDao());

        this.compensateService.setAccidentService(this.accidentService);

        this.contractService.setCustomerService(this.customerService);
        this.payService.setContractService(this.contractService);
    }

    public AccidentServiceIF getAccidentService() {
        return accidentService;
    }

    public CalculationFormulaServiceIF getCalculationFormulaService() {
        return calculationFormulaService;
    }

    public CompensateServiceIF getCompensateService() {
        return compensateService;
    }

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
