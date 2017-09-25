package repository;

import VO.UserValueObject;


public class UserRepository extends Repository<UserValueObject> {

    private static Long maxId = 0L;

    @Override
    public Long getMaxId(){
        return maxId;
    }
    @Override
    public void incMaxId(){
        maxId++;
    }
}
