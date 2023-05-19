package domain.customer;

public class InsuranceMember extends Customer{
    public InsuranceMember(String id, String password, String name, String email, int phoneNumber, String address, boolean hasHome, boolean hasWorkplace, int age, boolean gender) {
        super(id, password, name, email, phoneNumber, address, hasHome, hasWorkplace);
        this.age = age;
        this.gender = gender;
    }

    private int age;
    private boolean gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }


}