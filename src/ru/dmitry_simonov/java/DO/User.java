package DO;

/**
 * Created by Дима on 21.05.2017.
 */
public class User extends DomainObject {

    private String fio;

    private String phoneNumber;

    private String address;

    public User(long id, String fio, String phoneNumber, String address) {
        this.id = id;
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
