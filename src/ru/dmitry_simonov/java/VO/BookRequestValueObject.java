package VO;

import java.util.Date;

/**
 * Created by Дима on 21.05.2017.
 */
public class BookRequestValueObject extends ValueObject{

    public Date issueDate;

    public long book;

    public BookRequestValueObject (long id, Date issueDate, long book) {
        this.id = id;
        this.issueDate = issueDate;
        this.book = book;
    }

    public BookRequestValueObject() {
    }
}