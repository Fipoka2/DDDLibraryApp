package translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DO.User;
import VO.UserValueObject;

public class UserTranslator implements Translator<UserValueObject, User> {

    protected Logger logger = LoggerFactory.getLogger(UserTranslator.class);

    @Override
    public User convertToDomainObject(UserValueObject value) {
        if (value == null){
            logger.warn("Value object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new User(value.id, value.fio, value.phoneNumber, value.address);
    }

    @Override
    public UserValueObject convertToValueObject(User domain) {
        if (domain == null){
            logger.warn("Domain object is null. Translation is not possible.");
            return null;
        }

        logger.info("Conversion was successful.");
        return new UserValueObject(domain.getId(), domain.getFio(),
                domain.getPhoneNumber(), domain.getAddress());
    }
}
