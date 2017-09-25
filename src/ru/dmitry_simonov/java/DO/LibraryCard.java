package DO;

import java.util.Collection;



public class LibraryCard extends DomainObject {

    private User user;

    private Collection<BookRequest> bookRequests;

    public LibraryCard(long id, User user, Collection<BookRequest> bookRequests) {
        this.id = id;
        this.user = user;
        this.bookRequests = bookRequests;
    }

    public LibraryCard() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<BookRequest> getBookRequests() {
        return bookRequests;
    }

    public void setBookRequests(Collection<BookRequest> bookRequests) {
        this.bookRequests = bookRequests;
    }
}
