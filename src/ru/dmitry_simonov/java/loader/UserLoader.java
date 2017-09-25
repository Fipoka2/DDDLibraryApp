package loader;

import DO.User;
import repository.UserRepository;
import translator.UserTranslator;
import VO.UserValueObject;

public class UserLoader extends Loader<User> {

    private UserRepository userRepository;

    private UserTranslator userTranslator;

    public UserLoader(UserRepository userRepository, UserTranslator userTranslator){
        this.userRepository = userRepository;
        this.userTranslator = userTranslator;
    }

    @Override
    public User getEntity(Long id) {
        UserValueObject userValueObject = userRepository.find(id);
        User userDomainObject = null;
        if (userValueObject != null){
            userDomainObject = userTranslator.convertToDomainObject(userValueObject);
            logger.info("Domain object with id = " + id + " was loaded.");
        } else {
            logger.warn("Domain object with id = " + id + " wasn't loaded.");
        }
        return userDomainObject;
    }
}
