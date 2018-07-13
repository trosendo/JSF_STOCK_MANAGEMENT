package model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class Entity<T extends Number> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T entityID;

    public void setEntityID(T entityID) {
        this.entityID = entityID;
    }

    public T getEntityID() {
        return entityID;
    }

    @Override
    public int hashCode() {
        return (getEntityID() != null)
                ? (getClass().getSimpleName().hashCode() + getEntityID().hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && getEntityID() != null
                && other.getClass().isAssignableFrom(getClass())
                && getClass().isAssignableFrom(other.getClass()))
                ? getEntityID().equals(((Entity<?>) other).getEntityID())
                : (other == this);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getEntityID());
    }

}
