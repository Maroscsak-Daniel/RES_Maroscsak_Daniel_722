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

    public int computedPoints() {
        if (type == null) {
            throw new IllegalArgumentException("Mission event type is null");
        }

        return switch (type) {
            case EVA -> basePoints + 2 * day;
            case SYSTEM_FAILURE ->  basePoints - 3 - day;
            case SCIENCE ->   basePoints + (day % 4);
            case MEDICAL ->   basePoints - 2 * ( day % 3);
            case COMMUNICATION ->   basePoints + 5;

        };
    }
}
