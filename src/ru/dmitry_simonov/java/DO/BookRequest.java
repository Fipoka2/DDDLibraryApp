package DO;

import java.util.Date;

/**
 * Created by Дима on 21.05.2017.
 */
public class BookRequest extends DomainObject{


    private Date issueDate ;

    private Book book;

    public BookRequest(long id, Date issueDate, Book book) {
        this.id = id;
        this.book = book;
    }


    public BookRequest() {
    }

    public Date getIssueDate()
    {
        return issueDate;
    }

    public void setIssueDate(Date issueDate)
    {
        this.issueDate = issueDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}