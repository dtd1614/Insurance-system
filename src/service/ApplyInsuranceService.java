package service;

import domain.Contract;
import domain.Info.HomeInfo;
import domain.Info.Info;
import domain.Info.WorkplaceInfo;
import domain.Insurance;
import domain.calculationFormula.CalculationFormula;
import domain.calculationFormula.HomeFormula;
import domain.calculationFormula.WorkplaceFormula;
import enumeration.calculationFormula.homeFormula.HomeCompensation;
import enumeration.calculationFormula.homeFormula.HomeSquareMeter;
import enumeration.calculationFormula.workplaceFormula.Floor;
import enumeration.calculationFormula.workplaceFormula.WorkplaceCompensation;
import enumeration.calculationFormula.workplaceFormula.WorkplaceSquareMeter;
import enumeration.insurance.InsuranceStatus;
import exception.EmptyListException;
import exception.NoDataException;
import repository.calculation.CalculationFormulaListImpl;
import repository.contract.ContractListImpl;
import repository.info.InfoListImpl;
import repository.insurance.InsuranceListImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ApplyInsuranceService extends UnicastRemoteObject implements ApplyInsuranceServiceIF {
    private final InsuranceListImpl insuranceList;
    private final CalculationFormulaListImpl calculationFormulaList;
    private final InfoListImpl infoList;
    private final ContractListImpl contractList;

    public ApplyInsuranceService(InsuranceListImpl insuranceList, CalculationFormulaListImpl calculationFormulaList, InfoListImpl infoList, ContractListImpl contractList) throws RemoteException {
        this.insuranceList = insuranceList;
        this.calculationFormulaList = calculationFormulaList;
        this.infoList = infoList;
        this.contractList = contractList;
    }

    @Override
    public ArrayList<Insurance> getInsuranceProductList() throws RemoteException, EmptyListException {
        ArrayList<Insurance> insuranceList = this.insuranceList.findByStatus(InsuranceStatus.Authorize);
        if(insuranceList.isEmpty()){throw new EmptyListException("현재 판매 중인 보험이 없습니다.");}
        return insuranceList;
    }

    @Override
    public int calculateMaxCompensation(int squareMeter, int calculationFormulaId) throws RemoteException, NoDataException {
        CalculationFormula calculationFormula = calculationFormulaList.findById(calculationFormulaId);
        if(calculationFormula==null){throw new NoDataException("존재하지 않는 계산식 아이디입니다.");}
        int maxCompensation = squareMeter * calculationFormula.getMultiplierForMaxCompensation();;
        if(calculationFormula instanceof HomeFormula){
            int homeMaxCompensation = HomeCompensation.values()[0].getMaxAmount();
            if(homeMaxCompensation < maxCompensation){maxCompensation = homeMaxCompensation;}
        }
        if(calculationFormula instanceof WorkplaceFormula){
            int workplaceMaxCompensation = WorkplaceCompensation.values()[0].getMaxAmount();
            if(workplaceMaxCompensation < maxCompensation){maxCompensation = workplaceMaxCompensation;}
        }
        return maxCompensation;
    }

    @Override
    public int calculateMinCompensation(int squareMeter, int calculationFormulaId) throws RemoteException, NoDataException {
        CalculationFormula calculationFormula = calculationFormulaList.findById(calculationFormulaId);
        if(calculationFormula==null){throw new NoDataException("존재하지 않는 계산식 아이디입니다.");}
        int minCompensation = squareMeter * calculationFormula.getMultiplierForMinCompensation();;
        if(calculationFormula instanceof HomeFormula){
            int homeMinCompensation = HomeCompensation.values()[HomeCompensation.values().length-1].getMinAmount();
            if(homeMinCompensation > minCompensation){minCompensation = homeMinCompensation;}
        }
        if(calculationFormula instanceof WorkplaceFormula){
            int workplaceMinCompensation = WorkplaceCompensation.values()[WorkplaceCompensation.values().length-1].getMinAmount();
            if(workplaceMinCompensation > minCompensation){minCompensation = workplaceMinCompensation;}
        }
        return minCompensation;
    }

    @Override
    public int calculatePayment(Info info, int compensation, int calculationFormulaId) throws RemoteException, NoDataException {
        CalculationFormula calculationFormula = calculationFormulaList.findById(calculationFormulaId);
        if(calculationFormula == null){throw new NoDataException("존재하지 않는 계산식 아이디입니다.");}
        int totalRisk = 0;
        totalRisk += calculationFormula
                .getRiskLevelAccordingToRoofType()
                .get(info.getRoofType())
                .getLevel();
        totalRisk += calculationFormula
                .getRiskLevelAccordingToOutwallType()
                .get(info.getOutwallType())
                .getLevel();
        totalRisk += calculationFormula
                .getRiskLevelAccordingToPillarType()
                .get(info.getPillarType())
                .getLevel();
        if(calculationFormula instanceof HomeFormula){
            totalRisk += ((HomeFormula) calculationFormula)
                    .getRiskLevelAccordingToResidenceType()
                    .get(((HomeInfo)info).getResidenceType())
                    .getLevel();
            totalRisk += ((HomeFormula) calculationFormula)
                    .getRiskLevelAccordingToHouseType()
                    .get(((HomeInfo)info).getHouseType())
                    .getLevel();
            totalRisk += ((HomeFormula) calculationFormula)
                    .getRiskLevelAccordingToSquareMeter()
                    .get(getHomeSquareMeter(info.getSquareMeter()))
                    .getLevel();
            totalRisk += ((HomeFormula) calculationFormula)
                    .getRiskLevelAccordingToCompensation()
                    .get(getHomeCompensation(compensation))
                    .getLevel();
        }
        if(calculationFormula instanceof WorkplaceFormula){
            totalRisk += ((WorkplaceFormula) calculationFormula)
                    .getRiskLevelAccordingToUsage()
                    .get(((WorkplaceInfo)info).getUsage())
                    .getLevel();
            totalRisk += ((WorkplaceFormula) calculationFormula)
                    .getRiskLevelAccordingToSquareFeet()
                    .get(getWorkplaceSquareMeter(info.getSquareMeter()))
                    .getLevel();
            totalRisk += ((WorkplaceFormula) calculationFormula)
                    .getRiskLevelAccordingToCompensation()
                    .get(getWorkplaceCompensation(compensation))
                    .getLevel();
            totalRisk += ((WorkplaceFormula) calculationFormula)
                    .getRiskLevelAccordingToFloor()
                    .get(getFloor(((WorkplaceInfo)info).getFloor()))
                    .getLevel();
        }
        return totalRisk * calculationFormula.getMultiplierForPayment();
    }
    @Override
    public int applyInsurance(Contract contract) throws RemoteException {
        return contractList.add(contract);
    }
    public HomeSquareMeter getHomeSquareMeter(int squareMeter){
        for(int i = 0; i < HomeSquareMeter.values().length; i++){
            if(squareMeter < HomeSquareMeter.values()[i].getMaxSquareFeet()) continue;
            else return HomeSquareMeter.values()[i-1];
        }
        return null;
    }
    public HomeCompensation getHomeCompensation(int compensation){
        for(int i = 0; i < HomeCompensation.values().length; i++){
            if(compensation < HomeCompensation.values()[i].getMaxAmount()) continue;
            else return HomeCompensation.values()[i-1];
        }
        return null;
    }
    public WorkplaceSquareMeter getWorkplaceSquareMeter(int squareMeter){
        for(int i = 0; i < WorkplaceSquareMeter.values().length; i++){
            if(squareMeter < WorkplaceSquareMeter.values()[i].getMaxSquareFeet()) continue;
            else return WorkplaceSquareMeter.values()[i-1];
        }
        return null;
    }
    public WorkplaceCompensation getWorkplaceCompensation(int compensation){
        for(int i = 0; i < WorkplaceCompensation.values().length; i++){
            if(compensation < WorkplaceCompensation.values()[i].getMaxAmount()) continue;
            else return WorkplaceCompensation.values()[i-1];
        }
        return null;
    }
    public Floor getFloor(int floor){
        for(int i = 0; i < Floor.values().length; i++){
            if(floor < Floor.values()[i].getMaxFloor()) continue;
            else return Floor.values()[i-1];
        }
        return null;
    }
}
