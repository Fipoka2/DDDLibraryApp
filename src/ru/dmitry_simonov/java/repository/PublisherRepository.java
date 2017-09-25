package repository;

import VO.PublisherValueObject;


public class PublisherRepository extends Repository<PublisherValueObject> {

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