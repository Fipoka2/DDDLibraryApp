package DO;

/**
 * Created by Dmitry on 03.05.2017.
 */
public abstract class DomainObject {

    protected Long id;

    public Long getId(){
        return id;
    }

    protected void setId(Long id){
        this.id = id;
    }
}
