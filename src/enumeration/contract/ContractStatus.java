package enumeration.contract;

public enum ContractStatus {
    Apply("신청"),
    RefuseAuthorize("인수거절"),
    Authorize("인수완료"),
    Conclude("체결완료");

    private final String name;

    ContractStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
