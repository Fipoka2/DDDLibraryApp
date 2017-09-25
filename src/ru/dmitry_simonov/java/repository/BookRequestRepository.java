package repository;


import VO.BookRequestValueObject;


public class BookRequestRepository extends Repository<BookRequestValueObject> {

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