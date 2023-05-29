package repository.insurance;
import java.util.ArrayList;

import domain.Insurance;
import enumeration.insurance.InsuranceStatus;
import enumeration.insurance.InsuranceType;

public class InsuranceListImpl implements InsuranceList {
	private final ArrayList<Insurance> insuranceList = new ArrayList<>();


	public int add(Insurance insurance){
		if(insuranceList.size()==0)insurance.setId(1);
		else {insurance.setId(insuranceList.get(insuranceList.size()-1).getId()+1);}
		if(insuranceList.add(insurance)) return insurance.getId();
		else {return 0;}
	}

	public boolean delete(){
		return false;
	}

	public ArrayList<Insurance> retrieve(){
		return insuranceList;
	}

	public boolean update(){
		return false;
	}

	public ArrayList<Insurance> findByStatus(InsuranceStatus insuranceStatus) {
		ArrayList<Insurance> insuranceListByStatus = new ArrayList<>();
		for(Insurance insurance : insuranceList) {
			if(insurance.getStatus()==insuranceStatus) insuranceListByStatus.add(insurance);
		}
		return insuranceListByStatus;
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