package model;

public class MissionEvent {
    private int id;
    private int astronautId;
    private int day;
    private MissionEventType type;
    private int basePoints;

    public int getId() { return id; }
    public int getAstronautId() { return astronautId; }
    public int getDay() { return day; }
    public MissionEventType getType() { return type; }
    public int getBasePoints() { return basePoints; }

}
