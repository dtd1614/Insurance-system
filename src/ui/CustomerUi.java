package ui;

import domain.Contract;
import domain.Info.HomeInfo;
import domain.Info.Info;
import domain.Info.WorkplaceInfo;
import domain.Insurance;
import enumeration.calculationFormula.OutwallType;
import enumeration.calculationFormula.PillarType;
import enumeration.calculationFormula.RoofType;
import enumeration.calculationFormula.homeFormula.HomeSquareMeter;
import enumeration.calculationFormula.homeFormula.HouseType;
import enumeration.calculationFormula.homeFormula.ResidenceType;
import enumeration.calculationFormula.workplaceFormula.Floor;
import enumeration.calculationFormula.workplaceFormula.WorkplaceSquareMeter;
import enumeration.calculationFormula.workplaceFormula.WorkplaceUsage;
import enumeration.contract.ContractStatus;
import enumeration.contract.ContractTerm;
import enumeration.contract.PaymentCycle;
import enumeration.insurance.InsuranceStatus;
import enumeration.insurance.InsuranceType;
import exception.EmptyListException;
import exception.NoDataException;
import service.ServiceContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerUi {
	
	private final String customerId;
	private final ServiceContainer serviceContainer;
	private final BufferedReader userInput;
	
	public CustomerUi(String customerId, ServiceContainer serviceContainer, BufferedReader userInput) {
		this.customerId = customerId;
		this.serviceContainer = serviceContainer;
		this.userInput = userInput;
	}

	public void printMenu() throws IOException {
		while(true) {
			System.out.println("******************** 고객 메인 메뉴 *********************");
			System.out.println("1. 보험가입");
			System.out.println("2. 계약조회");
			System.out.println("3. 사고접수");
			System.out.println("4. 심사조회");
			System.out.println("5. 요금납부");
			System.out.println("0. 로그아웃");
			System.out.println("x. 종료");
			switch(userInput.readLine().trim()) {
				case "1" : printApplyInsuranceMenu(); break;
				case "2" : printSearchContractMenu(); break;
				case "3" : printReportAccidentMenu(); break;
				case "4" : printSearchExamineResultMenu(); break;
				case "5" : printPayMenu(); break;
				case "0" : return;
				case "x" : System.exit(0);
				default : System.err.println("잘못된 입력입니다.");
			}
		}
	}

	private void printApplyInsuranceMenu() throws IOException {
		while(true) {
			System.out.println("********************  보험가입 메뉴 *********************");
			System.out.println("자세히 보고싶은 보험의 아이디를 입력하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			ArrayList<Insurance> insuranceList;
			try {insuranceList = serviceContainer.getInsuranceService().getInsuranceList(InsuranceStatus.Authorize);}
			catch (EmptyListException e) {System.err.println(e.getMessage()); return;}
			System.out.println("아이디\t이름\t유형");
			for(Insurance insurance : insuranceList){
				System.out.println(insurance.getId()
						+ "\t" + insurance.getName()
						+ "\t" + insurance.getType().getName());
			}
			System.out.print("보험 아이디 : ");
			int insuranceId;
			try {insuranceId = Integer.parseInt(userInput.readLine().trim());}
			catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
			if (insuranceId == 0) return;
			Insurance selectedInsurance = null;
			for(Insurance insurance : insuranceList) {if(insurance.getId()==insuranceId) selectedInsurance = insurance;}
			if(selectedInsurance == null){System.err.println("잘못된 입력입니다."); continue;}
			printInsuranceDetail(selectedInsurance);
		}
	}

	private void printInsuranceDetail(Insurance insurance) throws IOException {
		while (true) {
			System.out.println("******************** 보험 상세정보 *********************");
			System.out.println("아이디 : " + insurance.getId()
					+ "\n이름 : " + insurance.getName()
					+ "\n유형 : " + insurance.getType().getName()
					+ "\n가입대상자 : " + insurance.getTarget()
					+ "\n보상조건 : " + insurance.getCompensateCondition()
					+ "\n비보상조건 : " + insurance.getNotCompensateCondition());
			System.out.println("\n[버튼]");
			System.out.println("1. 신청");
			System.out.println("0. 뒤로가기");
			String choice = userInput.readLine().trim();
			if (choice.equals("0")) {
				return;
			} else if(choice.equals("1")){
				if(insurance.getType()==InsuranceType.WorkplaceFire) printWorkplaceFireApplyForm(insurance);
				else printHomeFireApplyForm(insurance);
			}  else {
				System.err.println("잘못된 입력입니다.");
			}
		}
	}
	private void printWorkplaceFireApplyForm(Insurance insurance) throws IOException {
		while (true){
			System.out.println("******************** 사업장화재보험 가입양식 *********************");

			System.out.println("사업장 용도를 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < WorkplaceUsage.values().length; i++) {
				System.out.println((i + 1) + ". " + WorkplaceUsage.values()[i].getName());
			}
			WorkplaceUsage workplaceUsage;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  workplaceUsage = WorkplaceUsage.values()[0]; break;
				case "2" :  workplaceUsage = WorkplaceUsage.values()[1]; break;
				case "3" :  workplaceUsage = WorkplaceUsage.values()[2]; break;
				case "4" :  workplaceUsage = WorkplaceUsage.values()[3]; break;
				case "5" :  workplaceUsage = WorkplaceUsage.values()[4]; break;
				case "6" :  workplaceUsage = WorkplaceUsage.values()[5]; break;
				case "7" :  workplaceUsage = WorkplaceUsage.values()[6]; break;
				case "8" :  workplaceUsage = WorkplaceUsage.values()[7]; break;
				case "9" :  workplaceUsage = WorkplaceUsage.values()[8]; break;
				case "10" :  workplaceUsage = WorkplaceUsage.values()[9]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("사업장 면적(㎡)을 입력하세요.");
			int minWorkplaceSquareMeter = WorkplaceSquareMeter.values()[WorkplaceSquareMeter.values().length-1].getMinSquareFeet();
			int maxWorkplaceSquareMeter = WorkplaceSquareMeter.values()[0].getMaxSquareFeet();
			System.out.println("입력가능범위는 ["
					+ minWorkplaceSquareMeter
					+ " ~ "
					+ maxWorkplaceSquareMeter
					+ "] 입니다.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			int squareMeter;
			try {squareMeter = Integer.parseInt(userInput.readLine().trim());}
			catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
			if(squareMeter == 0) return;
			if(squareMeter < minWorkplaceSquareMeter || squareMeter > maxWorkplaceSquareMeter) {System.err.println("잘못된 입력입니다."); continue;}

			System.out.println("건물층수를 입력하세요.");
			int minFloor = Floor.values()[Floor.values().length-1].getMinFloor();
			int maxFloor = Floor.values()[0].getMaxFloor();
			System.out.println("입력가능범위는 ["
					+ minFloor
					+ " ~ "
					+ maxFloor
					+ "] 입니다.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			int floor;
			try {floor = Integer.parseInt(userInput.readLine().trim());}
			catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
			if(floor == 0) return;
			if(floor < minFloor || floor > maxFloor) {System.err.println("잘못된 입력입니다."); continue;}

			System.out.println("기둥형태를 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < PillarType.values().length; i++) {
				System.out.println((i + 1) + ". " + PillarType.values()[i].getName());
			}
			PillarType pillarType;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  pillarType = PillarType.values()[0]; break;
				case "2" :  pillarType = PillarType.values()[1]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("지붕형태를 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < RoofType.values().length; i++) {
				System.out.println((i + 1) + ". " + RoofType.values()[i].getName());
			}
			RoofType roofType;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  roofType = RoofType.values()[0]; break;
				case "2" :  roofType = RoofType.values()[1]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("외벽형태를 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < OutwallType.values().length; i++) {
				System.out.println((i + 1) + ". " + OutwallType.values()[i].getName());
			}
			OutwallType outwallType;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  outwallType = OutwallType.values()[0]; break;
				case "2" :  outwallType = OutwallType.values()[1]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("보상금을 입력하세요.");
			int minCompensation;
			int maxCompensation;
			try {
				minCompensation = serviceContainer.getCalculationFormulaService().calculateMinCompensation(squareMeter, insurance.getCalculationFormulaId());
				maxCompensation = serviceContainer.getCalculationFormulaService().calculateMaxCompensation(squareMeter, insurance.getCalculationFormulaId());
			} catch (NoDataException e) {
				System.err.println(e.getMessage()); return;
			}
			System.out.println("입력가능범위는 ["
					+ minCompensation
					+ " ~ "
					+ maxCompensation
					+ "] 입니다.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			int compensation;
			try {compensation = Integer.parseInt(userInput.readLine().trim());}
			catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
			if(compensation == 0) return;
			if(compensation < minCompensation || compensation > maxCompensation) {System.err.println("잘못된 입력입니다."); continue;}

			Info info = new WorkplaceInfo(customerId, squareMeter, pillarType, roofType, outwallType, workplaceUsage, floor);

			System.out.println("보험료 계산 결과를 알려드립니다.");
			int payment;
			try {
				payment = serviceContainer.getCalculationFormulaService().calculatePayment(info, compensation, insurance.getCalculationFormulaId());
			} catch (NoDataException e) {
				System.err.println(e.getMessage()); return;
			}
			System.out.println("월 보험료는 [" + payment + "]원 입니다.");

			System.out.println("보험 기간을 입력하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < ContractTerm.values().length; i++) {
				System.out.println((i + 1) + ". " + ContractTerm.values()[i].getYear() + "년");
			}
			ContractTerm contractTerm;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  contractTerm = ContractTerm.values()[0]; break;
				case "2" :  contractTerm = ContractTerm.values()[1]; break;
				case "3" :  contractTerm = ContractTerm.values()[2]; break;
				case "4" :  contractTerm = ContractTerm.values()[3]; break;
				case "5" :  contractTerm = ContractTerm.values()[4]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("납입 주기를 입력하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < PaymentCycle.values().length; i++) {
				System.out.println((i + 1) + ". " + PaymentCycle.values()[i].getMonth() + "월납");
			}
			PaymentCycle paymentCycle;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  paymentCycle = PaymentCycle.values()[0]; break;
				case "2" :  paymentCycle = PaymentCycle.values()[1]; break;
				case "3" :  paymentCycle = PaymentCycle.values()[2]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("해당 보험을 추천한 영업사원이 있다면 해당 영업사원의 아이디를 입력하세요.");
			System.out.println("없는 경우에는 -1를 입력하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			int salespersonId;
			try {salespersonId = Integer.parseInt(userInput.readLine().trim());}
			catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
			if(salespersonId==0) return;

			int infoId = serviceContainer.getInfoService().makeInfo(info);
			Contract contract = new Contract(infoId, insurance.getId(), salespersonId, customerId, contractTerm, payment, paymentCycle, compensation, ContractStatus.Apply);
			int contractId = serviceContainer.getContractService().applyInsurance(contract);
			if(contractId==0) {System.err.println("가입신청이 실패하였습니다."); continue;}
			System.out.println("가입신청이 완료되었습니다."); return;
		}
	}
	private void printHomeFireApplyForm(Insurance insurance) throws IOException {
		while (true){
			System.out.println("******************** 주택화재보험 가입양식 *********************");

			System.out.println("거주유형을 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < ResidenceType.values().length; i++) {
				System.out.println((i + 1) + ". " + ResidenceType.values()[i].getName());
			}
			ResidenceType residenceType;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  residenceType = ResidenceType.values()[0]; break;
				case "2" :  residenceType = ResidenceType.values()[1]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("주택유형을 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < HouseType.values().length; i++) {
				System.out.println((i + 1) + ". " + HouseType.values()[i].getName());
			}
			HouseType houseType;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  houseType = HouseType.values()[0]; break;
				case "2" :  houseType = HouseType.values()[1]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("주택 면적(㎡)을 입력하세요.");
			int minHomeSquareMeter = HomeSquareMeter.values()[HomeSquareMeter.values().length-1].getMinSquareFeet();
			int maxHomeSquareMeter = HomeSquareMeter.values()[0].getMaxSquareFeet();
			System.out.println("입력가능범위는 ["
					+ minHomeSquareMeter
					+ " ~ "
					+ maxHomeSquareMeter
					+ "] 입니다.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			int squareMeter;
			try {squareMeter = Integer.parseInt(userInput.readLine().trim());}
			catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
			if(squareMeter == 0) return;
			if(squareMeter < minHomeSquareMeter || squareMeter > maxHomeSquareMeter) {System.err.println("잘못된 입력입니다."); continue;}

			System.out.println("기둥형태를 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < PillarType.values().length; i++) {
				System.out.println((i + 1) + ". " + PillarType.values()[i].getName());
			}
			PillarType pillarType;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  pillarType = PillarType.values()[0]; break;
				case "2" :  pillarType = PillarType.values()[1]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("지붕형태를 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < RoofType.values().length; i++) {
				System.out.println((i + 1) + ". " + RoofType.values()[i].getName());
			}
			RoofType roofType;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  roofType = RoofType.values()[0]; break;
				case "2" :  roofType = RoofType.values()[1]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("외벽형태를 선택하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < OutwallType.values().length; i++) {
				System.out.println((i + 1) + ". " + OutwallType.values()[i].getName());
			}
			OutwallType outwallType;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  outwallType = OutwallType.values()[0]; break;
				case "2" :  outwallType = OutwallType.values()[1]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("보상금(원)을 입력하세요.");
			int minCompensation;
			int maxCompensation;
			try {
				minCompensation = serviceContainer.getCalculationFormulaService().calculateMinCompensation(squareMeter, insurance.getCalculationFormulaId());
				maxCompensation = serviceContainer.getCalculationFormulaService().calculateMaxCompensation(squareMeter, insurance.getCalculationFormulaId());
			} catch (NoDataException e) {
				System.err.println(e.getMessage()); return;
			}
			System.out.println("입력가능범위는 ["
					+ minCompensation
					+ " ~ "
					+ maxCompensation
					+ "] 입니다.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			int compensation;
			try {compensation = Integer.parseInt(userInput.readLine().trim());}
			catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
			if(compensation == 0) return;
			if(compensation < minCompensation || compensation > maxCompensation) {System.err.println("잘못된 입력입니다."); continue;}

			Info info = new HomeInfo(customerId, squareMeter, pillarType, roofType, outwallType, houseType, residenceType);

			System.out.println("보험료(원) 계산 결과를 알려드립니다.");
			int payment;
			try {
				payment = serviceContainer.getCalculationFormulaService().calculatePayment(info, compensation, insurance.getCalculationFormulaId());
			} catch (NoDataException e) {
				System.err.println(e.getMessage()); return;
			}
			System.out.println("월 보험료는 [" + payment + "]원 입니다.");

			System.out.println("보험 기간을 입력하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < ContractTerm.values().length; i++) {
				System.out.println((i + 1) + ". " + ContractTerm.values()[i].getYear() + "년");
			}
			ContractTerm contractTerm;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  contractTerm = ContractTerm.values()[0]; break;
				case "2" :  contractTerm = ContractTerm.values()[1]; break;
				case "3" :  contractTerm = ContractTerm.values()[2]; break;
				case "4" :  contractTerm = ContractTerm.values()[3]; break;
				case "5" :  contractTerm = ContractTerm.values()[4]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("납입 주기를 입력하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			for(int i = 0; i < PaymentCycle.values().length; i++) {
				System.out.println((i + 1) + ". " + PaymentCycle.values()[i].getMonth() + "월납");
			}
			PaymentCycle paymentCycle;
			switch(userInput.readLine().trim()) {
				case "0" :  return;
				case "1" :  paymentCycle = PaymentCycle.values()[0]; break;
				case "2" :  paymentCycle = PaymentCycle.values()[1]; break;
				case "3" :  paymentCycle = PaymentCycle.values()[2]; break;
				default : System.err.println("잘못된 입력입니다."); continue;
			}

			System.out.println("해당 보험을 추천한 영업사원이 있다면 해당 영업사원의 아이디를 입력하세요.");
			System.out.println("없는 경우에는 -1를 입력하세요.");
			System.out.println("뒤로가기를 원하시면 0을 입력하세요.");
			int salespersonId;
			try {salespersonId = Integer.parseInt(userInput.readLine().trim());}
			catch (NumberFormatException e) {System.err.println("잘못된 입력입니다."); continue;}
			if (salespersonId==0) return;

			int infoId = serviceContainer.getInfoService().makeInfo(info);
			Contract contract = new Contract(infoId, insurance.getId(), salespersonId, customerId, contractTerm, payment, paymentCycle, compensation, ContractStatus.Apply);
			int contractId = serviceContainer.getContractService().applyInsurance(contract);
			if(contractId==0) {System.err.println("가입신청이 실패하였습니다."); continue;}
			System.out.println("가입신청이 완료되었습니다.");
			System.out.println("신청아이디는 " + contractId + "입니다.");
			return;
		}
	}
	private void printSearchContractMenu() {
	}
	private void printSearchExamineResultMenu() throws IOException {
		while(true) {
			System.out.println("******************** 심사결과조회 메뉴 *********************");
			System.out.println("1. 보험가입신청결과조회");
			System.out.println("2. 사고심사결과조회");
			System.out.println("0. 뒤로가기");
			switch(userInput.readLine().trim()) {
				case "1" : printApplyInsuranceResult(); break;
				case "2" : printReportAccidentResult(); break;
				case "0" : return;
				default : System.err.println("잘못된 입력입니다.");
			}
		}
	}
	private void printApplyInsuranceResult() throws RemoteException {
		System.out.println("******************** 가입 요청 결과 *********************");
		ArrayList<Contract> applyInsuranceResultList = null;
		try {applyInsuranceResultList = serviceContainer.getContractService().getContractList(customerId);}
		catch (EmptyListException e) {System.err.println(e.getMessage()); return;}
		System.out.println("아이디\t보험아이디\t심사결과");
		for(Contract contract : applyInsuranceResultList){
			System.out.println(contract.getId()
					+ "\t" + contract.getInsuranceId()
					+ "\t" + contract.getStatus().getName());
		}
	}
	private void printReportAccidentResult() {
	}
	private void printReportAccidentMenu() {
	}
	private void printPayMenu() {
	}
}
