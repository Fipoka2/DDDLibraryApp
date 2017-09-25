package DO;

/**
 * Created by Дима on 21.05.2017.
 */
public class Publisher extends DomainObject {

    private String publisherName;

    private int year;

    public Publisher(long id, String publisherName, int year) {
        this.id = id;
        this.publisherName = publisherName;
        this.year = year;
    }

    public Publisher() {
    }


    public String getPublisher() {
        return publisherName;
    }

    public void setPublisher(String publisher) {
        this.publisherName = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
