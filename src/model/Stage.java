package model;

public class Stage extends BaseEntity {
    private String stageName;
    private int capacity;

    public Stage(int id, String stageName, int capacity){
        this.id = id;
        this.stageName = stageName;
        this.capacity = capacity;
    }

    @Override
    public boolean isValid() {
        return capacity > 0;
    }

    @Override
    public String getSummary() {
        return stageName + " | capacity = " + capacity;
    }
}
