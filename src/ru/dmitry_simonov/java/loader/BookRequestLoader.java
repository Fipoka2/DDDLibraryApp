package loader;

import DO.BookRequest;
import repository.BookRequestRepository;
import translator.BookRequestTranslator;
import VO.BookRequestValueObject;

/**
 * Created by Дима on 21.05.2017.
 */
public class BookRequestLoader extends Loader<BookRequest> {

     private BookRequestRepository bookRequestRepository;

     private BookRequestTranslator bookRequestTranslator;

     private BookLoader bookLoader;


    public BookRequestLoader(BookRequestRepository bookRequestRepository, BookRequestTranslator bookRequestTranslator
    ,BookLoader bookLoader){
        this.bookRequestRepository = bookRequestRepository;
        this.bookRequestTranslator = bookRequestTranslator;
        this.bookLoader = bookLoader;
    }

    @Override
    public BookRequest getEntity(Long id){
        BookRequestValueObject valueObject = bookRequestRepository.find(id);
        BookRequest domainObject = null;
        if (valueObject != null){
            domainObject = bookRequestTranslator.convertToDomainObject(valueObject);
            logger.info("Domain object with id = " + id + " was loaded.");
            domainObject.setBook(bookLoader.getEntity(valueObject.book));
        } else {
            logger.warn("Domain object with id = " + id + " wasn't loaded.");
        }
        return domainObject;}
}
