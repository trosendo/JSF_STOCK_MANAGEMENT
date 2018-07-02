package model;

public abstract class Entity {
    private long id;

    public void setID(long id) {
        this.id = id;
    }

    public long getID() {
        return id;
    }
}
