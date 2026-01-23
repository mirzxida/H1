package model;

public abstract class BaseEntity {
    protected int id;

    public int getId() {
        return id;
    }

    public abstract boolean isValid();
    public abstract String getSummary();
}
