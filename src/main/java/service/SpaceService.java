package service;

import model.*;

import java.util.List;

public class SpaceService {
    private final List<Astronaut> astronauts;
    private final List<MissionEvent> events;
    private final List<Supply> supplies;

    public SpaceService(List<Astronaut> astronauts,
                        List<MissionEvent> events,
                        List<Supply> supplies) {
        this.astronauts = astronauts;
        this.events = events;
        this.supplies = supplies;
    }

    public List<Astronaut> getAstronauts() { return astronauts; }
    public List<MissionEvent> getEvents() { return events; }
    public List<Supply> getSupplies() { return supplies; }

    // Task 2: filter astronauts by spacecraft and status
    public List<Astronaut> activeBySpacecraft(String spacecraft) {
        return astronauts.stream()
                .filter(a -> a.getSpacecraft().equals(spacecraft)
                        && a.getStatus() == AstronautStatus.ACTIVE)
                .toList();
    }
}
