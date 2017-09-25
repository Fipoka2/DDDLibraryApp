package DO;

/**
 * Created by Dmitry on 03.05.2017.
 */
public class Book extends DomainObject{

    private String name;
    private String author;
    private Publisher publisher;

    public Book(long id, String name, String author, Publisher publisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
