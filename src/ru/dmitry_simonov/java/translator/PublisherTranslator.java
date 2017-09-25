package translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DO.Publisher;
import VO.PublisherValueObject;

public class PublisherTranslator implements Translator<PublisherValueObject, Publisher> {

    protected Logger logger = LoggerFactory.getLogger(PublisherTranslator.class);

    @Override
    public Publisher convertToDomainObject(PublisherValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new Publisher(value.id, value.publisherName,value.year);
    }

    @Override
    public PublisherValueObject convertToValueObject(Publisher domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new PublisherValueObject(domain.getId(), domain.getPublisher(),domain.getYear());
    }
}
