package repository;

import VO.BookValueObject;


public class BookRepository extends Repository<BookValueObject> {

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