package model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity
public abstract class Entity {
    private long entityID;

    public void setEntityID(long entityID) {
        this.entityID = entityID;
    }

    public long getEntityID() {
        return entityID;
    }

    @Id
    @GeneratedValue
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
