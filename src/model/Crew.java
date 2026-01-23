package model;

public class Crew extends BaseEntity {
    private String fullName;
    private String role;
    private Integer managerId;

    public Crew(int id, String fullName, String role, Integer managerId){
        this.id = id;
        this.fullName = fullName;
        this.role = role;
        this.managerId = managerId;
    }

    @Override
    public boolean isValid() {
        return fullName != null && role != null;
    }

    public boolean hasManager(){
        return managerId != null;
    }

    @Override
    public String getSummary() {
        return fullName + " - " + role;
    }
}
