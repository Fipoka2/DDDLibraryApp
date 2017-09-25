package VO;

/**
 * Created by Дима on 21.05.2017.
 */
public class BookValueObject extends ValueObject {

    public String name;

    public String author;

    public long publisherName;

    public BookValueObject( long id, String name, String author, long publisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisherName = publisher;
    }

    public BookValueObject() {
    }
}
