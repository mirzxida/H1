package model;

public class Band extends BaseEntity {
    private String name;
    private String country;
    private String genre;

    public Band(int id, String name, String country, String genre){
        this.id = id;
        this.name = name;
        this.country = country;
        this.genre = genre;
    }

    @Override
    public boolean isValid() {
        return name != null && !name.isEmpty();
    }

    @Override
    public String getSummary() {
        return name + " (" + genre + ") ";
    }

    public String getName() {
        return name;
    }
}
