package enumeration.insurance;

public enum InsuranceStatus {
	
	UnderAuthorize("인가심사중"),
	RefuseAuthorize("인가거절"),
	Authorize("인가완료"),
	StopSale("판매중지");
	
	private final String name;
	
	InsuranceStatus(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	

}
