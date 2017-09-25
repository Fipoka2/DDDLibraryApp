package translator;

import DO.DomainObject;
import VO.ValueObject;

public interface Translator<V extends ValueObject, D extends DomainObject> {
    D convertToDomainObject(V value);
    V convertToValueObject(D value);
}