package enumeration.pay;

public enum PaymentMethod {
    Card("카드"),
    Account("통장");

    private final String name;

    PaymentMethod(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
