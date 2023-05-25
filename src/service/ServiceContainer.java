package service;

import repository.calculation.CalculationFormulaListImpl;
import repository.contract.ContractListImpl;
import repository.customer.CustomerListImpl;
import repository.employee.EmployeeListImpl;
import repository.insurance.InsuranceListImpl;
import repository.sale.SaleListImpl;

import java.rmi.RemoteException;

public class ServiceContainer {
    private final LoginServiceIF loginService;
    private final MakeInsuranceServiceIF makeInsuranceService;
    private final MakeFormulaServiceIF makeFormulaService;
    private final AuthorizeServiceIF AuthorizeService;
    private final OfferServiceIF offerService;
    private final ConcludeServiceIF concludeService;

    public ServiceContainer() throws RemoteException {
        CalculationFormulaListImpl calculationFormulaList = new CalculationFormulaListImpl();
        InsuranceListImpl insuranceList = new InsuranceListImpl();
        EmployeeListImpl employeeList = new EmployeeListImpl();
        CustomerListImpl customerList = new CustomerListImpl();
        SaleListImpl saleList = new SaleListImpl();
        ContractListImpl contractList = new ContractListImpl();

        loginService = new LoginService(customerList, employeeList);
        makeInsuranceService = new MakeInsuranceService(calculationFormulaList, insuranceList);
        makeFormulaService = new MakeFormulaService(calculationFormulaList);
        AuthorizeService = new AuthorizeService(insuranceList, calculationFormulaList);
        offerService = new OfferService(customerList, insuranceList, saleList, employeeList);
        concludeService = new ConcludeService(customerList, insuranceList, contractList);

//        RMI로 분리할 때 위에거 지우고 아래거 쓸 것.
//        loginService = (LoginServiceIF) Naming.lookup("loginService");
//        makeInsuranceService = (MakeInsuranceServiceIF) Naming.lookup("makeInsuranceService");
//        makeFormulaService = (MakeFormulaServiceIF) Naming.lookup("makeFormulaService");
//        examineInsuranceService = (ExamineInsuranceServiceIF) Naming.lookup("examineInsuranceService");
    }

    public LoginServiceIF getLoginService() {
        return loginService;
    }

    public MakeInsuranceServiceIF getMakeInsuranceService() {
        return makeInsuranceService;
    }

    public MakeFormulaServiceIF getMakeFormulaService() {
        return makeFormulaService;
    }

    public AuthorizeServiceIF getAuthorizeService() {
        return AuthorizeService;
    }

    public OfferServiceIF getofferService() {
        return offerService;
    }

    public ConcludeServiceIF getConcludeService() {return concludeService;}
}
