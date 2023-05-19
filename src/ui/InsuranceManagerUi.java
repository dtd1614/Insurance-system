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
import service.ServiceContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

public class InsuranceManagerUi {
	
    private final String employeeId;
    private final ServiceContainer serviceContainer;
    private final BufferedReader userInput;
    
    public InsuranceManagerUi(String employeeId, ServiceContainer serviceContainer, BufferedReader userInput) {
        this.employeeId = employeeId;
        this.serviceContainer = serviceContainer;
        this.userInput = userInput;
    }
    
	public void printMenu() throws IOException{
		while(true) {
			System.out.println("******************** 상품관리자 메인 메뉴 *********************");
			System.out.println("1. 인가하기");
			System.out.println("2. 상품관리하기");
			System.out.println("0. 로그아웃");
			System.out.println("x. 종료");
			switch(userInput.readLine().trim()) {
				case "1" : printExamineMenu();  break;
				case "2" : printManageMenu(); break;
				case "0" : return;
				case "x" : System.exit(0);
				default : System.err.println("잘못된 입력입니다.");
			}
		}
	}

	private void printExamineMenu() throws IOException{
	    while(true) {
			ArrayList<Insurance> insuranceList = serviceContainer.getAuthorizeService().getInsuranceList(InsuranceStatus.UnderExamine);
			if(insuranceList.isEmpty()) {System.err.println("인가요청중인 상품이 없습니다."); return;}
			System.out.println("******************** 상품 인가 메뉴 *********************");
	        System.out.println("인가할 상품의 아이디를 입력하세요. 뒤로가려면 0을 입력하세요.");
			System.out.println("아이디\t이름\t유형");
			for (Insurance insurance : insuranceList) {
				System.out.println(insurance.getId() 
						+ "\t" + insurance.getName() 
						+ "\t" + insurance.getType().getName());
			}
			System.out.print("상품 아이디 : ");
			int id;
		    try {
		    	id = Integer.parseInt(userInput.readLine().trim());
		    }catch (NumberFormatException e) {
		    	System.err.println("잘못된 입력입니다."); continue;
		    }
		    if(id==0) return;
		    Insurance selectedInsurance = null;
		    for(Insurance insurance:insuranceList) {
		    	if(insurance.getId()==id)selectedInsurance = insurance;
		    }
		    if(selectedInsurance==null){System.err.println("잘못된 입력입니다."); continue;}
		    printInsuranceDetail(selectedInsurance);
	    }
    }
	
	private void printInsuranceDetail(Insurance selectedInsurance) throws IOException {
	    while (true) {
	        System.out.println("******************** 상품 상세내용 *********************");
	        System.out.println("상품 상세내역을 확인하고 버튼을 선택하세요.");
	        System.out.println("[상품 상세내역]");
            System.out.println("아이디 : " + selectedInsurance.getId()
            		+ "\n이름 : " + selectedInsurance.getName() 
            		+ "\n유형 : " + selectedInsurance.getType().getName() 
            		+ "\n가입대상자 : " + selectedInsurance.getTarget()
            		+ "\n계산식 아이디 : " + selectedInsurance.getCalculationFormulaId());
            //System.out.println("보험설명: " + insurance.getDescription()+ "보상손해: " + insurance.getCompensation()+"비손해:"+insurance.getNonCompensation()+"계산식아이디: " + insurance.getCalculationId());
	        System.out.println("\n[버튼]");
            System.out.println("1. 인가");
            System.out.println("2. 거절");
            System.out.println("3. 계산식 조회");
            System.out.println("0. 뒤로가기");
            String choice = userInput.readLine().trim();
            if (choice.equals("0")) {
            	return;
            } else if(choice.equals("1")){
            	boolean isSuccess = serviceContainer.getAuthorizeService().examineAuthorization(selectedInsurance.getId(), InsuranceStatus.Confirm);
            	if(isSuccess==true) System.out.println("상품이 인가되었습니다.");
            	else System.out.println("인가가 실패하었습니다.");
                return;
            } else if (choice.equals("2")){
            	boolean isSuccess =  serviceContainer.getAuthorizeService().examineAuthorization(selectedInsurance.getId(), InsuranceStatus.Refuse);
//                System.out.println("인가 거절 사유를 입력해주세요.");
//                userInput.readLine().trim();
            	//거절 사유 어떻게 구현할지 넣을지 회의 필요
            	if(isSuccess==true) System.out.println("인가가 거절되었습니다.");
            	else System.out.println("인가거절이 실패하었습니다.");
                return;            
            } else if (choice.equals("3")) {
            	printFormulaDetail(selectedInsurance.getCalculationFormulaId());
            } else {
            	System.err.println("잘못된 입력입니다."); continue;
            }
        }
    }
	
	private void printFormulaDetail(int id) throws IOException{
        System.out.println("******************** 계산식 상세내용 *********************");
		CalculationFormula formula = serviceContainer.getAuthorizeService().getCaculationFormula(id);
		if(formula==null) {System.out.println("존재하지 않는 계산식입니다."); return;}
		if(formula instanceof HomeFormula) {printHomeFormulaDetail((HomeFormula) formula);}
		else {printHomeFormulaDetail((WorkplaceFormula) formula);}
	}
	
	private void printHomeFormulaDetail(HomeFormula formula) {
		System.out.println("아이디 : " + formula.getId()
						+ "\n이름 : " + formula.getName());
		System.out.println("[거주 유형에 따른 위험도]");
        for (Entry<ResidenceType, RiskLevel> entrySet : formula.getRiskLevelAccordingToResidenceType().entrySet()) {
            System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
        }
		System.out.println("[주택 유형에 따른 위험도]");
        for (Entry<HouseType, RiskLevel> entrySet : formula.getRiskLevelAccordingToHouseType().entrySet()) {
            System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
        }
		System.out.println("[거주 평수에 따른 위험도]");
        for (Entry<HomeSquareMeter, RiskLevel> entrySet : formula.getRiskLevelAccordingToSquareFeet().entrySet()) {
            System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
        }
		System.out.println("[기둥 유형에 따른 위험도]");
        for (Entry<PillarType, RiskLevel> entrySet : formula.getRiskLevelAccordingToPillarType().entrySet()) {
            System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
        }
		System.out.println("[지붕 유형에 따른 위험도]");
        for (Entry<RoofType, RiskLevel> entrySet : formula.getRiskLevelAccordingToRoofType().entrySet()) {
            System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
        }
		System.out.println("[외벽 유형에 따른 위험도]");
        for (Entry<OutwallType, RiskLevel> entrySet : formula.getRiskLevelAccordingToOutwallType().entrySet()) {
            System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
        }
		System.out.println("[보상금에 따른 위험도]");
        for (Entry<HomeCompensation, RiskLevel> entrySet : formula.getRiskLevelAccordingToCompensation().entrySet()) {
            System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
        }
		System.out.println("보험료 산출 상수 : " + formula.getMultiplierForPayment());
		System.out.println("최소 보상금 산출 상수 : " + formula.getMultiplierForMinCompensation());
		System.out.println("최대 보상금 산출 상수 : " + formula.getMultiplierForMaxCompensation());
	}
	
	private void printHomeFormulaDetail(WorkplaceFormula formula) {
		System.out.println("아이디 : " + formula.getId()
						+ "\n이름 : " + formula.getName());
		System.out.println("[건물용도에 따른 위험도]");
		for (Entry<WorkplaceUsage, RiskLevel> entrySet : formula.getRiskLevelAccordingToUsage().entrySet()) {
		System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
		}
		System.out.println("[평수에 따른 위험도]");
		for (Entry<WorkplaceSquareMeter, RiskLevel> entrySet : formula.getRiskLevelAccordingToSquareFeet().entrySet()) {
		System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
		}
		System.out.println("[층수에 따른 위험도]");
		for (Entry<Floor, RiskLevel> entrySet : formula.getRiskLevelAccordingToNumOfFloors().entrySet()) {
		System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
		}
		System.out.println("[기둥 유형에 따른 위험도]");
		for (Entry<PillarType, RiskLevel> entrySet : formula.getRiskLevelAccordingToPillarType().entrySet()) {
		System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
		}
		System.out.println("[지붕 유형에 따른 위험도]");
		for (Entry<RoofType, RiskLevel> entrySet : formula.getRiskLevelAccordingToRoofType().entrySet()) {
		System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
		}
		System.out.println("[외벽 유형에 따른 위험도]");
		for (Entry<OutwallType, RiskLevel> entrySet : formula.getRiskLevelAccordingToOutwallType().entrySet()) {
		System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
		}
		System.out.println("[보상금에 따른 위험도]");
		for (Entry<WorkplaceCompensation, RiskLevel> entrySet : formula.getRiskLevelAccordingToCompensation().entrySet()) {
		System.out.println(entrySet.getKey().getName() + " : " + entrySet.getValue().getLevel());
		}
		System.out.println("보험료 산출 상수 : " + formula.getMultiplierForPayment());
		System.out.println("최소 보상금 산출 상수 : " + formula.getMultiplierForMinCompensation());
		System.out.println("최대 보상금 산출 상수 : " + formula.getMultiplierForMaxCompensation());
	}

	private void printManageMenu() {
		
	}
	
}