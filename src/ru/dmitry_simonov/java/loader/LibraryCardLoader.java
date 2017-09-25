package loader;

import java.util.ArrayList;
import java.util.Collection;

import DO.BookRequest;
import DO.LibraryCard;
import repository.LibraryCardRepository;
import translator.LibraryCardTranslator;
import VO.LibraryCardValueObject;

/**
 * Created by Дима on 21.05.2017.
 */
public class LibraryCardLoader extends Loader<LibraryCard> {
    
    private LibraryCardRepository libraryCardRepository;
    
    private LibraryCardTranslator libraryCardTranslator;
    
    private  UserLoader userLoader;
    
    private BookRequestLoader bookRequestLoader;

    public LibraryCardLoader(LibraryCardRepository libraryCardRepository, LibraryCardTranslator libraryCardTranslator,
                             UserLoader userLoader, BookRequestLoader bookRequestLoader){
        this.libraryCardRepository = libraryCardRepository;
        this.libraryCardTranslator = libraryCardTranslator;
        this.bookRequestLoader = bookRequestLoader;
        this.userLoader = userLoader;
    }

    @Override
    public LibraryCard getEntity(Long id){
        LibraryCardValueObject valueObject = libraryCardRepository.find(id);
        LibraryCard domainObject = null;
        if (valueObject != null){
            domainObject = libraryCardTranslator.convertToDomainObject(valueObject);
            domainObject.setUser(userLoader.getEntity(valueObject.user));
            Collection<BookRequest> bookRequests = new ArrayList<>();
            for(long bookRequestId : valueObject.bookRequests)
            {
                bookRequests.add(bookRequestLoader.getEntity(bookRequestId));
            }
            logger.info("Domain object with id = " + id + " was loaded.");
        } else {
            logger.warn("Domain object with id = " + id + " wasn't loaded.");
        }
        return domainObject;}

    public LibraryCardRepository getLibraryCardRepository()
    {
        return libraryCardRepository;
    }

    public void setLibraryCardRepository(LibraryCardRepository libraryCardRepository)
    {
        this.libraryCardRepository = libraryCardRepository;
    }

    public LibraryCardTranslator getLibraryCardTranslator()
    {
        return libraryCardTranslator;
    }

    public void setLibraryCardTranslator(LibraryCardTranslator libraryCardTranslator)
    {
        this.libraryCardTranslator = libraryCardTranslator;
    }

    public UserLoader getUserLoader()
    {
        return userLoader;
    }

    public void setUserLoader(UserLoader userLoader)
    {
        this.userLoader = userLoader;
    }

    public BookRequestLoader getBookRequestLoader()
    {
        return bookRequestLoader;
    }

    public void setBookRequestLoader(BookRequestLoader bookRequestLoader)
    {
        this.bookRequestLoader = bookRequestLoader;
    }
}