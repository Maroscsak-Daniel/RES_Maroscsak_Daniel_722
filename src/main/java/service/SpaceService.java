package service;

import model.Astronaut;
import model.MissionEvent;
import model.Supply;

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
}
