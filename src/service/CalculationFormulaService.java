package service;

import domain.Info.HomeCustomerInfo;
import domain.Info.CustomerInfo;
import domain.Info.WorkplaceCustomerInfo;
import domain.calculationFormula.CalculationFormula;
import domain.calculationFormula.HomeFormula;
import domain.calculationFormula.WorkplaceFormula;
import enumeration.calculationFormula.homeFormula.HomeCompensation;
import enumeration.calculationFormula.homeFormula.HomeSquareMeter;
import enumeration.calculationFormula.workplaceFormula.Floor;
import enumeration.calculationFormula.workplaceFormula.WorkplaceCompensation;
import enumeration.calculationFormula.workplaceFormula.WorkplaceSquareMeter;
import enumeration.insurance.InsuranceType;
import exception.EmptyListException;
import exception.NoDataException;
import dao.CalculationFormulaDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CalculationFormulaService extends UnicastRemoteObject implements CalculationFormulaServiceIF{
    private final CalculationFormulaDao calculationFormulaDao;
    public CalculationFormulaService(CalculationFormulaDao calculationFormulaDao) throws RemoteException {
        this.calculationFormulaDao = calculationFormulaDao;
    }
    @Override
    public ArrayList<CalculationFormula> getCalculationFormulaList(InsuranceType insuranceType) throws RemoteException, EmptyListException {
        ArrayList<CalculationFormula> calculationFormulaList = this.calculationFormulaDao.findByType(insuranceType);
        if(calculationFormulaList.isEmpty()) throw new EmptyListException("계산식 목록이 존재하지 않습니다.");
        return calculationFormulaList;
    }
    @Override
    public CalculationFormula getCalculationFormula(int id) throws RemoteException, NoDataException {
        CalculationFormula calculationFormula = calculationFormulaDao.findById(id);
        if(calculationFormula == null) throw new NoDataException("존재하지 않는 계산식입니다.");
        return calculationFormula;
    }
    @Override
    public int makeFormula(CalculationFormula calculationFormula) throws RemoteException {
        return calculationFormulaDao.add(calculationFormula);
    }
    @Override
    public int calculateMaxCompensation(int squareMeter, int calculationFormulaId) throws RemoteException, NoDataException {
        CalculationFormula calculationFormula = calculationFormulaDao.findById(calculationFormulaId);
        if(calculationFormula==null){throw new NoDataException("존재하지 않는 계산식 아이디입니다.");}
        int maxCompensation = squareMeter * calculationFormula.getMultiplierForMaxCompensation();;
        if(calculationFormula instanceof HomeFormula){
            int homeMaxCompensation = HomeCompensation.values()[0].getMaxAmount();
            if(homeMaxCompensation < maxCompensation){maxCompensation = homeMaxCompensation;}
            int homeMinCompensation = HomeCompensation.values()[HomeCompensation.values().length-1].getMinAmount();
            if(homeMinCompensation > maxCompensation){maxCompensation = homeMinCompensation;}
        }
        if(calculationFormula instanceof WorkplaceFormula){
            int workplaceMaxCompensation = WorkplaceCompensation.values()[0].getMaxAmount();
            if(workplaceMaxCompensation < maxCompensation){maxCompensation = workplaceMaxCompensation;}
            int workplaceMinCompensation = WorkplaceCompensation.values()[WorkplaceCompensation.values().length-1].getMinAmount();
            if(workplaceMinCompensation > maxCompensation){maxCompensation = workplaceMinCompensation;}
        }
        return maxCompensation;
    }

    @Override
    public int calculateMinCompensation(int squareMeter, int calculationFormulaId) throws RemoteException, NoDataException {
        CalculationFormula calculationFormula = calculationFormulaDao.findById(calculationFormulaId);
        if(calculationFormula==null){throw new NoDataException("존재하지 않는 계산식 아이디입니다.");}
        int minCompensation = squareMeter * calculationFormula.getMultiplierForMinCompensation();;
        if(calculationFormula instanceof HomeFormula){
            int homeMinCompensation = HomeCompensation.values()[HomeCompensation.values().length-1].getMinAmount();
            if(homeMinCompensation > minCompensation){minCompensation = homeMinCompensation;}
            int homeMaxCompensation = HomeCompensation.values()[0].getMaxAmount();
            if(homeMaxCompensation < minCompensation){minCompensation = homeMaxCompensation;}

        }
        if(calculationFormula instanceof WorkplaceFormula){
            int workplaceMinCompensation = WorkplaceCompensation.values()[WorkplaceCompensation.values().length-1].getMinAmount();
            if(workplaceMinCompensation > minCompensation){minCompensation = workplaceMinCompensation;}
            int workplaceMaxCompensation = WorkplaceCompensation.values()[0].getMaxAmount();
            if(workplaceMaxCompensation < minCompensation){minCompensation = workplaceMaxCompensation;}
        }
        return minCompensation;
    }

    @Override
    public int calculatePayment(CustomerInfo customerInfo, int compensation, int calculationFormulaId) throws RemoteException, NoDataException {
        CalculationFormula calculationFormula = calculationFormulaDao.findById(calculationFormulaId);
        if(calculationFormula == null){throw new NoDataException("존재하지 않는 계산식 아이디입니다.");}
        int totalRisk = 0;
        totalRisk += calculationFormula
                .getRiskLevelAccordingToRoofType()
                .get(customerInfo.getRoofType())
                .getLevel();
        totalRisk += calculationFormula
                .getRiskLevelAccordingToOutwallType()
                .get(customerInfo.getOutwallType())
                .getLevel();
        totalRisk += calculationFormula
                .getRiskLevelAccordingToPillarType()
                .get(customerInfo.getPillarType())
                .getLevel();
        if(calculationFormula instanceof HomeFormula){
            totalRisk += ((HomeFormula) calculationFormula)
                    .getRiskLevelAccordingToResidenceType()
                    .get(((HomeCustomerInfo) customerInfo).getResidenceType())
                    .getLevel();
            totalRisk += ((HomeFormula) calculationFormula)
                    .getRiskLevelAccordingToHouseType()
                    .get(((HomeCustomerInfo) customerInfo).getHouseType())
                    .getLevel();
            totalRisk += ((HomeFormula) calculationFormula)
                    .getRiskLevelAccordingToSquareMeter()
                    .get(getHomeSquareMeter(customerInfo.getSquareMeter()))
                    .getLevel();
            totalRisk += ((HomeFormula) calculationFormula)
                    .getRiskLevelAccordingToCompensation()
                    .get(getHomeCompensation(compensation))
                    .getLevel();
        }
        if(calculationFormula instanceof WorkplaceFormula){
            totalRisk += ((WorkplaceFormula) calculationFormula)
                    .getRiskLevelAccordingToUsage()
                    .get(((WorkplaceCustomerInfo) customerInfo).getUsage())
                    .getLevel();
            totalRisk += ((WorkplaceFormula) calculationFormula)
                    .getRiskLevelAccordingToSquareFeet()
                    .get(getWorkplaceSquareMeter(customerInfo.getSquareMeter()))
                    .getLevel();
            totalRisk += ((WorkplaceFormula) calculationFormula)
                    .getRiskLevelAccordingToCompensation()
                    .get(getWorkplaceCompensation(compensation))
                    .getLevel();
            totalRisk += ((WorkplaceFormula) calculationFormula)
                    .getRiskLevelAccordingToFloor()
                    .get(getFloor(((WorkplaceCustomerInfo) customerInfo).getFloor()))
                    .getLevel();
        }
        return totalRisk * calculationFormula.getMultiplierForPayment();
    }
    public HomeSquareMeter getHomeSquareMeter(int squareMeter){
        for(int i = 0; i < HomeSquareMeter.values().length; i++){
            if(squareMeter < HomeSquareMeter.values()[i].getMaxSquareFeet()) continue;
            else return HomeSquareMeter.values()[i-1];
        }
        return HomeSquareMeter.values()[HomeSquareMeter.values().length-1];
    }
    public HomeCompensation getHomeCompensation(int compensation){
        for(int i = 0; i < HomeCompensation.values().length; i++){
            if(compensation < HomeCompensation.values()[i].getMaxAmount()) continue;
            else return HomeCompensation.values()[i-1];
        }
        return HomeCompensation.values()[HomeCompensation.values().length-1];
    }
    public WorkplaceSquareMeter getWorkplaceSquareMeter(int squareMeter){
        for(int i = 0; i < WorkplaceSquareMeter.values().length; i++){
            if(squareMeter < WorkplaceSquareMeter.values()[i].getMaxSquareFeet()) continue;
            else return WorkplaceSquareMeter.values()[i-1];
        }
        return WorkplaceSquareMeter.values()[WorkplaceSquareMeter.values().length-1];
    }
    public WorkplaceCompensation getWorkplaceCompensation(int compensation){
        for(int i = 0; i < WorkplaceCompensation.values().length; i++){
            if(compensation < WorkplaceCompensation.values()[i].getMaxAmount()) continue;
            else return WorkplaceCompensation.values()[i-1];
        }
        return WorkplaceCompensation.values()[WorkplaceCompensation.values().length-1];
    }
    public Floor getFloor(int floor){
        for(int i = 0; i < Floor.values().length; i++){
            if(floor < Floor.values()[i].getMaxFloor()) continue;
            else return Floor.values()[i-1];
        }
        return Floor.values()[Floor.values().length-1];
    }
}
