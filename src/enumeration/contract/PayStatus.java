package enumeration.contract;

public enum PayStatus {
    FullPayment("완납"),
    NonPayment("미납");

    private final String name;

    PayStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
