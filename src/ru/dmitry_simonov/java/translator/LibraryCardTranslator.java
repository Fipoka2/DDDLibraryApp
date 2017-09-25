package translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

import DO.BookRequest;
import DO.LibraryCard;
import VO.LibraryCardValueObject;

public class LibraryCardTranslator implements Translator<LibraryCardValueObject, LibraryCard> {

    protected Logger logger = LoggerFactory.getLogger(LibraryCardTranslator.class);

    @Override
    public LibraryCard convertToDomainObject(LibraryCardValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }
        logger.info("Conversion was successful.");
        return new LibraryCard(value.id,null, null);
    }

    @Override
    public LibraryCardValueObject convertToValueObject(LibraryCard domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        Collection<Long> longCollection = new ArrayList<Long>();
        for(BookRequest bookRequest:domain.getBookRequests())
        {
            longCollection.add(bookRequest.getId());
        }
        logger.info("Conversion was successful.");
        return new LibraryCardValueObject(domain.getId(), domain.getUser().getId(),
                longCollection);
    }
}
