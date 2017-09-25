package repository;

import VO.LibraryCardValueObject;

public class LibraryCardRepository extends Repository<LibraryCardValueObject> {

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