package enumeration.employee;

public enum Department {
	
	InsuranceDeveloper("상품개발자"), 
	InsuranceManager("상품관리자");
	
	private final String name;
	
	Department(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
