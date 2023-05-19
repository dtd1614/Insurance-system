package repository.insurance;

import java.util.ArrayList;
import domain.Insurance;
public interface InsuranceList{

	public int add(Insurance insurance);

	public boolean delete();

	public ArrayList<Insurance> retrieve();

	public boolean update();

}