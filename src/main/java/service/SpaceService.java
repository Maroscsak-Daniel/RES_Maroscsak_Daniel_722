package service;

import model.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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

    // Task 3: sort astronauts by experience and name
    public List<Astronaut> sortedAstronauts() {
        Comparator<Astronaut> cmp = Comparator
                .comparingInt(Astronaut::getExperienceLevel).reversed()
                .thenComparing(Astronaut::getName);
        return astronauts.stream().sorted(cmp).toList();
    }

    public List<Astronaut> reverseSortedAstronauts() {
        Comparator<Astronaut> cmp = Comparator
                .comparingInt(Astronaut::getExperienceLevel).reversed()
                .thenComparing(Astronaut::getName).reversed();
        return astronauts.stream().sorted(cmp).toList();
    }

    // Task 4: write astronauts to file
    public void writeAstronauts(Path out, List<Astronaut> list) {
        try {
            List<String> lines = list.stream().map(Astronaut::toString).toList();
            Files.write(out, lines);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write file " + out + ": " + e.getMessage(), e);
        }
    }
}
