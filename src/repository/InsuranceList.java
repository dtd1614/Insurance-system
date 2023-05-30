package repository;
import java.util.ArrayList;

import domain.Insurance;
import enumeration.insurance.InsuranceStatus;
import enumeration.insurance.InsuranceType;

public class InsuranceList extends DBConnector{
	private final ArrayList<Insurance> insuranceList = new ArrayList<>();
	public int add(Insurance insurance){
		if(insuranceList.size()==0)insurance.setId(1);
		else {insurance.setId(insuranceList.get(insuranceList.size()-1).getId()+1);}
		if(insuranceList.add(insurance)) return insurance.getId();
		else {return 0;}
	}
	public ArrayList<Insurance> retrieve(){
		return insuranceList;
	}
	public ArrayList<Insurance> findByStatus(InsuranceStatus insuranceStatus) {
		ArrayList<Insurance> insuranceListByStatus = new ArrayList<>();
		for(Insurance insurance : insuranceList) {
			if(insurance.getStatus()==insuranceStatus) insuranceListByStatus.add(insurance);
		}
		return insuranceListByStatus;
	}
	public Insurance findById(int id) {
		for(Insurance insurance : insuranceList) {
			if(insurance.getId() == id) return insurance;
		}
		return null;
	}
	public boolean update(int id, InsuranceStatus sale) {
		for(Insurance insurance : insuranceList) {
			if(insurance.getId()==id) {insurance.setStatus(sale); return true;}
		}
		return false;
	}
	public ArrayList<Insurance> findByType(InsuranceType insuranceType) {
		ArrayList<Insurance> insuranceListByType = new ArrayList<>();
		for(Insurance insurance : insuranceList) {
			if(insurance.getType()==insuranceType) insuranceListByType.add(insurance);
		}
		return insuranceListByType;
	}
}