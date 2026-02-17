package model;

public class Astronaut {
    private int id;
    private String name;
    private String spacecraft;
    private AstronautStatus status;
    private int experienceLevel;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpacecraft() { return spacecraft; }
    public AstronautStatus getStatus() { return status; }
    public int getExperienceLevel() { return experienceLevel; }

    @Override
    public String toString() {
        return "[#" + id + "] " + name + " | " + spacecraft + " | " + status + " | exp=" + experienceLevel;
    }
}
