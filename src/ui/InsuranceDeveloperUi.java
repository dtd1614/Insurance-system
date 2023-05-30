package ui;

import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import domain.calculationFormula.HomeFormula;
import domain.calculationFormula.WorkplaceFormula;
import enumeration.calculationFormula.OutwallType;
import enumeration.calculationFormula.PillarType;
import enumeration.calculationFormula.RiskLevel;
import enumeration.calculationFormula.RoofType;
import enumeration.calculationFormula.homeFormula.HomeCompensation;
import enumeration.calculationFormula.homeFormula.HomeSquareMeter;
import enumeration.calculationFormula.homeFormula.HouseType;
import enumeration.calculationFormula.homeFormula.ResidenceType;
import enumeration.calculationFormula.workplaceFormula.Floor;
import enumeration.calculationFormula.workplaceFormula.WorkplaceCompensation;
import enumeration.calculationFormula.workplaceFormula.WorkplaceSquareMeter;
import enumeration.calculationFormula.workplaceFormula.WorkplaceUsage;
import enumeration.insurance.InsuranceStatus;
import enumeration.insurance.InsuranceType;
import service.ServiceContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class InsuranceDeveloperUi {
	
	private final String employeeId;
	private final ServiceContainer serviceContainer;
	private final BufferedReader userInput;
	
	public InsuranceDeveloperUi(String employeeId, ServiceContainer serviceContainer, BufferedReader userInput) {
		this.employeeId = employeeId;
		this.serviceContainer = serviceContainer;
		this.userInput = userInput;
	}

	public void printMenu() throws IOException {
		while(true) {
			System.out.println("******************** 상품개발자 메인 메뉴 *********************");
			System.out.println("1. 보험료&보상금 계산식 수립하기");
			System.out.println("2. 상품 개발하기");
			System.out.println("3. 인가심사결과 조회하기");
			System.out.println("0. 로그아웃");
			System.out.println("x. 종료");
			switch(userInput.readLine().trim()) {
				case "1" : printFormulaMenu(); break;
				case "2" : printDevelopeMenu();; break;
				case "3" : printExamineResult(); break;
				case "0" : return;
				case "x" : System.exit(0);
				default : System.err.println("잘못된 입력입니다.");
			}
		}
	}

	private void printFormulaMenu() throws IOException {
		while(true) {
			System.out.println("******************** 계산식 수립 메뉴 *********************");
			System.out.println("보험 종류를 선택하세요.");
			System.out.println("1. 주택화재보험");
			System.out.println("2. 사업장화재보험");
			System.out.println("0. 뒤로가기");
			switch(userInput.readLine().trim()) {
				case "1" : printHomeFormulaForm(); break;
				case "2" : printWorkplaceFormulaForm(); break;
				case "0" : return;
				default : System.err.println("잘못된 입력입니다.");
			}
		}
	}

	private void printHomeFormulaForm() throws IOException {
		
		String caculationFormulaName;
		HashMap<ResidenceType, RiskLevel> riskLevelAccordingToResidenceType = new HashMap<>();
		HashMap<OutwallType, RiskLevel> riskLevelAccordingToOutwallType = new HashMap<>();
		HashMap<PillarType, RiskLevel> riskLevelAccordingToPillarType = new HashMap<>();
		HashMap<RoofType, RiskLevel> riskLevelAccordingToRoofType = new HashMap<>();
		HashMap<HouseType, RiskLevel> riskLevelAccordingToHouseType = new HashMap<>();
		HashMap<HomeSquareMeter, RiskLevel> riskLevelAccordingToSquareMeter = new HashMap<>();
		HashMap<HomeCompensation, RiskLevel> riskLevelAccordingToCompensation = new HashMap<>();
		int numToMultiplyForMinCompenstion;
		int numToMultiplyForMaxCompenstion;	
		int numToMultiplyForPayment;
		
		System.out.println("******************** 주택화재보험 계산식 수립 *********************");
		
		System.out.println("계산식의 이름을 입력하세요.");
		caculationFormulaName = userInput.readLine().trim();
		if(caculationFormulaName.contains(" ")||caculationFormulaName.isEmpty()) {
			System.err.println("잘못된 입력입니다."); return;
		}
		System.out.println("거주 유형에 따른 위험도(1~10)를 입력하세요.");
		for (ResidenceType residence : ResidenceType.values()) {
			System.out.print(residence.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToResidenceType.put(residence, riskLevel);
		}		
		System.out.println("주택 유형에 따른 위험도(1~10)를 입력하세요.");
		for (HouseType house : HouseType.values()) {
			System.out.print(house.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToHouseType.put(house, riskLevel);
		}
		System.out.println("거주 평수에 따른 위험도(1~10)를 입력하세요.");
		for (HomeSquareMeter square : HomeSquareMeter.values()) {
			System.out.print(square.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToSquareMeter.put(square, riskLevel);
		}
		System.out.println("기둥 유형에 따른 위험도(1~10)를 입력하세요.");
		for (PillarType pillar : PillarType.values()) {
			System.out.print(pillar.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToPillarType.put(pillar, riskLevel);
		}
		System.out.println("지붕 유형에 따른 위험도(1~10)를 입력하세요.");
		for (RoofType roof : RoofType.values()) {
			System.out.print(roof.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToRoofType.put(roof, riskLevel);
		}
		System.out.println("외벽 유형에 따른 위험도(1~10)를 입력하세요.");
		for (OutwallType outwall : OutwallType.values()) {
			System.out.print(outwall.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToOutwallType.put(outwall, riskLevel);
		}
		System.out.println("보상금에 따른 위험도(1~10)를 입력하세요.");
		for (HomeCompensation hCompensation : HomeCompensation.values()) {
			System.out.print(hCompensation.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToCompensation.put(hCompensation, riskLevel);
		}
		System.out.println("보험료 계산식을 수립하세요.");
		System.out.println("보험료는 [고객의 총 위험도]에 [보험료 산출 상수]를 곱한 값입니다.");
		System.out.println("[보험료 산출 상수]를 입력하세요.");
	    try {
			numToMultiplyForPayment = Integer.parseInt(userInput.readLine());
	    } catch (NumberFormatException e) {    
	        System.err.println("잘못된 입력입니다."); return;
	    }
		System.out.println("최소 보상금 계산식을 수립하세요.");
		System.out.println("최소 보상금은 [고객 주택 면적(㎡)]에 [최소 보상금 산출 상수]를 곱한 값입니다.");
		System.out.println("[최소 보상금 산출 상수]를 입력하세요.");
	    try {
	    	numToMultiplyForMinCompenstion = Integer.parseInt(userInput.readLine());
	    } catch (NumberFormatException e) {    
	        System.err.println("잘못된 입력입니다."); return;
	    }
		System.out.println("최대 보상금 계산식을 수립하세요.");
		System.out.println("최대 보상금은 [고객 주택 면적(㎡)]에 [최대 보상금 산출 상수]를 곱한 값입니다.");
		System.out.println("[최대 보상금 산출 상수]를 입력하세요. [최대 보상금 산출 상수]는 [최소 보상금 산출 상수]보다 커야 합니다." );
	    try {
	    	numToMultiplyForMaxCompenstion = Integer.parseInt(userInput.readLine());
	    } catch (NumberFormatException e) {    
	        System.err.println("잘못된 입력입니다.");  return;
	    }
		if(numToMultiplyForMaxCompenstion <= numToMultiplyForMinCompenstion) {
	        System.out.println("잘못된 입력입니다.");
			System.err.println("[최대 보상금 산출 상수]는 [최소 보상금 산출 상수]보다 커야 합니다.");
			return;
		}						 	
		//CaculationFormula caculationFormula = new CaculationFormula(caculationFormulaId, riskLevelAccordingToPillarType, riskLevelAccordingToRoofType, riskLevelAccordingToOutwallType, numToMultiplyForMinCompenstion, numToMultiplyForMaxCompenstion, numToMultiplyForPayment);HomeFormula homeFormula = new HomeFormula(caculationFormulaId, riskLevelAccordingToPillarType, riskLevelAccordingToRoofType, riskLevelAccordingToOutwallType, numToMultiplyForMinCompenstion, numToMultiplyForMaxCompenstion, numToMultiplyForPayment, riskLevelAccordingToResidenceType, riskLevelAccordingToHouseType, riskLevelAccordingToSquareFeet, riskLevelAccordingToCompensation);
		HomeFormula homeFormula = new HomeFormula(caculationFormulaName, riskLevelAccordingToPillarType, 
				riskLevelAccordingToRoofType, riskLevelAccordingToOutwallType, 
				numToMultiplyForMinCompenstion, numToMultiplyForMaxCompenstion, 
				numToMultiplyForPayment, riskLevelAccordingToResidenceType, 
				riskLevelAccordingToHouseType, riskLevelAccordingToSquareMeter, 
				riskLevelAccordingToCompensation);
		int id = serviceContainer.getMakeFormulaService().makeFormula(homeFormula);
		if(id == 0) {System.err.println("게산식 수립에 실패하였습니다."); return;}
		System.out.println("계산식 수립이 완료되었습니다."); 
		System.out.println("계산식 아이디는 " + id + "입니다."); 
		return;
	}
	
	private void printWorkplaceFormulaForm() throws IOException {
		
		String caculationFormulaName;
		HashMap<PillarType, RiskLevel> riskLevelAccordingToPillarType = new HashMap<>();
		HashMap<RoofType, RiskLevel> riskLevelAccordingToRoofType = new HashMap<>();
		HashMap<OutwallType, RiskLevel> riskLevelAccordingToOutwallType = new HashMap<>();		
		HashMap<WorkplaceUsage, RiskLevel> riskLevelAccordingToUsage = new HashMap<>();
		HashMap<Floor, RiskLevel> riskLevelAccordingToNumOfFloors = new HashMap<>();
		HashMap<WorkplaceSquareMeter, RiskLevel> riskLevelAccordingToSquareFeet = new HashMap<>();
		HashMap<WorkplaceCompensation, RiskLevel> riskLevelAccordingToCompensation = new HashMap<>();		
		int numToMultiplyForMinCompenstion;
		int numToMultiplyForMaxCompenstion;	
		int numToMultiplyForPayment;	
		
		System.out.println("******************** 사업장화재보험 계산식 수립 *********************");
		
		System.out.println("계산식의 이름을 입력하세요.");
		caculationFormulaName = userInput.readLine().trim();
		if(caculationFormulaName.contains(" ")||caculationFormulaName.isEmpty()) {
			System.err.println("잘못된 입력입니다."); return;
		}
		System.out.println("건물용도에 따른 위험도(1~10)를 입력하세요.");
		for (WorkplaceUsage usage : WorkplaceUsage.values()) {
			System.out.print(usage.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToUsage.put(usage, riskLevel);
		}
		System.out.println("평수에 따른 위험도(1~10)를 입력하세요.");
		for (WorkplaceSquareMeter square : WorkplaceSquareMeter.values()) {
			System.out.print(square.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToSquareFeet.put(square, riskLevel);
		}		
		System.out.println("층수에 따른 위험도(1~10)를 입력하세요.");
		for (Floor floor : Floor.values()) {
			System.out.print(floor.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToNumOfFloors.put(floor, riskLevel);
		}		
		System.out.println("기둥 유형에 따른 위험도(1~10)를 입력하세요.");
		for (PillarType pillar : PillarType.values()) {
			System.out.print(pillar.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToPillarType.put(pillar, riskLevel);
		}		
		System.out.println("지붕 유형에 따른 위험도(1~10)를 입력하세요.");
		for (RoofType roof : RoofType.values()) {
			System.out.print(roof.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToRoofType.put(roof, riskLevel);
		}		
		System.out.println("외벽 유형에 따른 위험도(1~10)를 입력하세요.");
		for (OutwallType outwall : OutwallType.values()) {
			System.out.print(outwall.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToOutwallType.put(outwall, riskLevel);
		}		
		System.out.println("보상금에 따른 위험도(1~10)를 입력하세요");
		for (WorkplaceCompensation wCompensation : WorkplaceCompensation.values()) {
			System.out.print(wCompensation.getName() + " : ");
			RiskLevel riskLevel = choiceRiskLevel();
			if(riskLevel==null) {System.err.println("잘못된 입력입니다."); return;}
			riskLevelAccordingToCompensation.put(wCompensation, riskLevel);
		}
		System.out.println("보험료 계산식을 수립하세요.");
		System.out.println("보험료는 [고객의 총 위험도]에 [보험료 산출 상수]를 곱한 값입니다.");
		System.out.println("[보험료 산출 상수]를 입력하세요.");
		try {
			numToMultiplyForPayment = Integer.parseInt(userInput.readLine());
		} catch (NumberFormatException e) {
			System.err.println("잘못된 입력입니다."); return;
		}
		System.out.println("최소 보상금 계산식을 수립하세요.");
		System.out.println("최소 보상금은 [고객 주택 면적(㎡)]에 [최소 보상금 산출 상수]를 곱한 값입니다.");
		System.out.println("[최소 보상금 산출 상수]를 입력하세요.");
		try {
			numToMultiplyForMinCompenstion = Integer.parseInt(userInput.readLine());
		} catch (NumberFormatException e) {
			System.err.println("잘못된 입력입니다."); return;
		}
		System.out.println("최대 보상금 계산식을 수립하세요.");
		System.out.println("최대 보상금은 [고객 주택 면적(㎡)]에 [최대 보상금 산출 상수]를 곱한 값입니다.");
		System.out.println("[최대 보상금 산출 상수]를 입력하세요. [최대 보상금 산출 상수]는 [최소 보상금 산출 상수]보다 커야 합니다." );
		try {
			numToMultiplyForMaxCompenstion = Integer.parseInt(userInput.readLine());
		} catch (NumberFormatException e) {
			System.err.println("잘못된 입력입니다.");  return;
		}
		if(numToMultiplyForMaxCompenstion <= numToMultiplyForMinCompenstion) {
			System.out.println("잘못된 입력입니다.");
			System.err.println("[최대 보상금 산출 상수]는 [최소 보상금 산출 상수]보다 커야 합니다.");
			return;
		}
		WorkplaceFormula workFormula = new WorkplaceFormula(caculationFormulaName, riskLevelAccordingToPillarType,
				riskLevelAccordingToRoofType, riskLevelAccordingToOutwallType, 
				numToMultiplyForMinCompenstion, numToMultiplyForMaxCompenstion, 
				numToMultiplyForPayment, riskLevelAccordingToUsage, 
				riskLevelAccordingToNumOfFloors, riskLevelAccordingToSquareFeet, 
				riskLevelAccordingToCompensation);
		int id = serviceContainer.getMakeFormulaService().makeFormula(workFormula);
		if(id == 0) {System.err.println("게산식 수립에 실패하였습니다."); return;}
		System.out.println("계산식 수립이 완료되었습니다."); 
		System.out.println("계산식 아이디는 " + id + "입니다."); 
		return;
	}
	
	private RiskLevel choiceRiskLevel() throws IOException {
		switch(userInput.readLine().trim()) {
		case "1" : return RiskLevel.One;
		case "2" : return RiskLevel.Two; 
		case "3" : return RiskLevel.Three; 
		case "4" : return RiskLevel.Four; 
		case "5" : return RiskLevel.Five; 
		case "6" : return RiskLevel.Six; 
		case "7" : return RiskLevel.Seven; 
		case "8" : return RiskLevel.Eight; 
		case "9" : return RiskLevel.Nine; 
		case "10" : return RiskLevel.Ten; 
		default : return null;
		}
	}
	
	private void printDevelopeMenu() throws IOException {
		while(true) {
			System.out.println("******************** 상품 개발 메뉴 *********************");
			System.out.println("보험 종류를 선택하세요.");
			System.out.println("1. 주택화재보험");
			System.out.println("2. 사업장화재보험");
			System.out.println("0. 뒤로가기");
			switch(userInput.readLine().trim()) {
				case "1" : printDevelopeForm(InsuranceType.HomeFire); break;
				case "2" : printDevelopeForm(InsuranceType.WorkplaceFire); break;
				case "0": return;
				default : System.err.println("잘못된 입력입니다.");
			}
		}
	}
	
	private void printDevelopeForm(InsuranceType insuranceType) throws IOException {
		
		ArrayList<CalculationFormula> calculationFormulaList = serviceContainer.getMakeInsuranceService().getCalculationFormulaList(insuranceType);
		if(calculationFormulaList.isEmpty()) {System.err.println("수립된 계산식이 없습니다. 계산식 수립을 먼저 해주세요."); return;}

		System.out.println("******************** 상품 개발 양식 *********************");
		System.out.println("상품 개발 양식을 채워주세요.");
		System.out.print("이름 : ");
		String name = userInput.readLine().trim();		
		if(name.contains(" ")||name.isEmpty()) {System.err.println("잘못된 입력입니다."); return;}
		System.out.print("가입대상자 : ");
		String target = userInput.readLine().trim();
		if(target.isEmpty()) {System.err.println("잘못된 입력입니다."); return;}				
		System.out.println("계산식 목록을 조회하고 계산식을 선택하세요.");
		System.out.println("아이디\t이름");
		for(CalculationFormula calculationFormula : calculationFormulaList) {
			System.out.println(calculationFormula.getId() +
					"\t" + calculationFormula.getName());
		}
		System.out.print("계산식 아이디 : ");
		int formulaId;
	    try {
			formulaId = Integer.parseInt(userInput.readLine().trim());
	    } catch (NumberFormatException e) {    
	        System.err.println("잘못된 입력입니다."); return;
	    }
	    boolean isExistId = false;
	    for(CalculationFormula calculationFormula : calculationFormulaList) {
	    	if(calculationFormula.getId()==formulaId)isExistId = true;
	    }
	    if(!isExistId){System.err.println("잘못된 입력입니다."); return;}
		System.out.print("보상 조건 : ");
		String compensateCondtion = userInput.readLine().trim();
		if(compensateCondtion.isEmpty()) {System.err.println("잘못된 입력입니다."); return;}
		System.out.print("비보상 조건 : ");
		String notCompensateCondition = userInput.readLine().trim();
		if(notCompensateCondition.isEmpty()) {System.err.println("잘못된 입력입니다."); return;}
		Insurance insurance = new Insurance(name,insuranceType,target,formulaId, compensateCondtion, notCompensateCondition, InsuranceStatus.UnderAuthorize);
		int id = serviceContainer.getMakeInsuranceService().makeInsurance(insurance);
		if(id==0) {System.err.println("개발 및 인가요청이 실패되었습니다."); return;}
		System.out.println("개발 및 인가요청이 완료되었습니다."); 
		System.out.println("상품 아이디는 " + id + "입니다."); 
		return;
	}

	private void printExamineResult() throws RemoteException {
		System.out.println("******************** 인가 요청 결과 *********************");
		ArrayList<Insurance> confirmInsuranceList = serviceContainer.getMakeInsuranceService().getInsuranceList(InsuranceStatus.Authorize);
		ArrayList<Insurance> refuseInsuranceList = serviceContainer.getMakeInsuranceService().getInsuranceList(InsuranceStatus.RefuseAuthorize);
		ArrayList<Insurance> underExamineInsuranceList = serviceContainer.getMakeInsuranceService().getInsuranceList(InsuranceStatus.UnderAuthorize);
		ArrayList<Insurance> insurances = new ArrayList<>();
		insurances.addAll(confirmInsuranceList);
		insurances.addAll(refuseInsuranceList);
		insurances.addAll(underExamineInsuranceList);
		System.out.println("아이디\t이름\t유형\t심사결과");
		for(Insurance insurance : insurances){
			System.out.println(insurance.getId()
					+ "\t" + insurance.getName()
					+ "\t" + insurance.getType().getName()
					+ "\t" + insurance.getStatus().getName());
		}
	}
}
