package persistent.bank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Statement {
    @Id
    @GeneratedValue
    private Long id;
    
    private long start;
    private long end;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getStart() {
        return start;
    }
    public void setStart(long start) {
        this.start = start;
    }
    public long getEnd() {
        return end;
    }
    public void setEnd(long end) {
        this.end = end;
    }


    
}