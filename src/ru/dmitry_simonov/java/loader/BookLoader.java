package loader;

import DO.Book;
import repository.BookRepository;
import translator.BookTranslator;
import VO.BookValueObject;

/**
 * Created by Дима on 21.05.2017.
 */
public class BookLoader extends Loader<Book> {

    private BookRepository bookRepository;

    private BookTranslator bookTranslator;

    private PublisherLoader publisherLoader;


    public BookLoader(BookRepository bookRepository, BookTranslator bookTranslator,PublisherLoader loader){
        this.bookRepository = bookRepository;
        this.bookTranslator = bookTranslator;
        this.publisherLoader = loader;
    }

    public BookRepository getBookRepository()
    {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    public BookTranslator getBookTranslator()
    {
        return bookTranslator;
    }

    public void setBookTranslator(BookTranslator bookTranslator)
    {
        this.bookTranslator = bookTranslator;
    }

    @Override
    public Book getEntity(Long id) {
        BookValueObject bookValueObject = bookRepository.find(id);
        Book bookDomainObject = null;
        if (bookValueObject != null){
            bookDomainObject = bookTranslator.convertToDomainObject(bookValueObject);
            bookDomainObject.setPublisher(publisherLoader.getEntity(bookValueObject.id));
            logger.info("Domain object with id = " + id + " was loaded.");
        } else {
            logger.warn("Domain object with id = " + id + " wasn't loaded.");
        }
        return bookDomainObject;
    }

    public PublisherLoader getPublisherLoader()
    {
        return publisherLoader;
    }

    public void setPublisherLoader(PublisherLoader publisherLoader)
    {
        this.publisherLoader = publisherLoader;
    }
}
