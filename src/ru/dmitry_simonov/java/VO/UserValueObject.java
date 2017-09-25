package VO;

/**
 * Created by Дима on 21.05.2017.
 */
public class UserValueObject extends ValueObject {

    public String fio;

    public String phoneNumber;

    public String address;

    public UserValueObject( long id, String fio, String phoneNumber, String address) {
        this.id = id;
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public  UserValueObject(){}

}
