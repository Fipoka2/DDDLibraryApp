package translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DO.Book;
import VO.BookValueObject;

public class BookTranslator implements Translator<BookValueObject, Book> {

    protected Logger logger = LoggerFactory.getLogger(BookTranslator.class);

    @Override
    public Book convertToDomainObject(BookValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }
        logger.info("Conversion was successful.");
        return new Book(value.id, value.name, value.author,null);
    }

    @Override
    public BookValueObject convertToValueObject(Book domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new BookValueObject(domain.getId(), domain.getName(),
                domain.getAuthor(), domain.getPublisher().getId());
    }
}