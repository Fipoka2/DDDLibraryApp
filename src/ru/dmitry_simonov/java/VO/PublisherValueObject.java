package VO;



/**
 * Created by Дима on 21.05.2017.
 */
public class PublisherValueObject extends ValueObject
{

    public String publisherName;

    public int year;

    public PublisherValueObject(long id, String publisherName, int year) {
        this.id = id;
        this.publisherName = publisherName;
        this.year = year;
    }

    public PublisherValueObject() {
    }
}
