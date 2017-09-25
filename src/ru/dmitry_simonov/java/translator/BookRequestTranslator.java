package translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DO.BookRequest;
import VO.BookRequestValueObject;

public class BookRequestTranslator implements Translator<BookRequestValueObject, BookRequest> {

    protected Logger logger = LoggerFactory.getLogger(BookRequestTranslator.class);

    @Override
    public BookRequest convertToDomainObject(BookRequestValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }
        logger.info("Conversion was successful.");
        return new BookRequest(value.id, value.issueDate,
            null);
    }

    @Override
    public BookRequestValueObject convertToValueObject(BookRequest domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new BookRequestValueObject(domain.getId(), domain.getIssueDate(),
                domain.getBook().getId());
    }
}
