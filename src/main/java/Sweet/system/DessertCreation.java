package sweet.system;

public class DessertCreation {
    private String title;
    private String description;

    public DessertCreation(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}