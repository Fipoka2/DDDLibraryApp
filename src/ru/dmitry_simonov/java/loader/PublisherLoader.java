package loader;

import DO.Publisher;
import repository.PublisherRepository;
import translator.PublisherTranslator;
import VO.PublisherValueObject;

/**
 * Created by Дима on 21.05.2017.
 */
public class PublisherLoader extends  Loader<Publisher> {
    
    private PublisherRepository publisherRepository;
    
    private  PublisherTranslator publisherTranslator;
    

    public PublisherLoader(PublisherRepository publisherRepository, PublisherTranslator publisherTranslator){
        this.publisherRepository = publisherRepository;
        this.publisherTranslator = publisherTranslator;
    }

    @Override
    public Publisher getEntity(Long id) {
        PublisherValueObject publisherValueObject = publisherRepository.find(id);
        Publisher publisherDomainObject = null;
        if (publisherValueObject != null){
            publisherDomainObject = publisherTranslator.convertToDomainObject(publisherValueObject);
            logger.info("Domain object with id = " + id + " was loaded.");
        } else {
            logger.warn("Domain object with id = " + id + " wasn't loaded.");
        }
        return publisherDomainObject;
    }
}