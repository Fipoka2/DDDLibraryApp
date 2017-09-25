package loader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

import DO.DomainObject;
import repository.Repository;
import VO.ValueObject;


public abstract class Loader<D extends DomainObject> {

   // private Repository<ValueObject> repository;

    protected Logger logger = LoggerFactory.getLogger(Loader.class);

    public abstract D getEntity(Long id);


    public HashSet<D> getAllEntities(Repository<?> repository) {
        HashSet<D> entities = new HashSet<>();

        for (Object object : repository.getAll()){
            ValueObject valueObject = (ValueObject) object;
            entities.add(getEntity(valueObject.id));
        }

        logger.info("Domain objects were loaded.");
        return entities;
    }


}