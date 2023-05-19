package enumeration.policy;

public enum PolicyType {
    Compensation("보상");

    private final String name;

    PolicyType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
