package VO;

import java.util.Collection;

/**
 * Created by Дима on 21.05.2017.
 */
public class LibraryCardValueObject extends ValueObject{

    public long user;

    public Collection<Long> bookRequests;

    public LibraryCardValueObject(long id, long user, Collection<Long> bookRequests) {
        this.id = id;
        this.user = user;
        this.bookRequests = bookRequests;
    }

    public LibraryCardValueObject() {
    }
}
