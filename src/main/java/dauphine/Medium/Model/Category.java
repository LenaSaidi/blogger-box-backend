package dauphine.Medium.Model;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;


    public Category() {}

    public Category(UUID id,String name) {
        this.id = id; // Génère un ID unique
        this.name = name;
    }
    // Getters & Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
