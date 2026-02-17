package service;

import model.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    // Task 5: first N computed points lines
    public List<String> firstNComputedPointsLines(int n) {
        return events.stream()
                .limit(n)
                .map(ev -> "Event " + ev.getId() + " -> raw=" + ev.getBasePoints()
                        + " -> computed=" + ev.computedPoints())
                .toList();
    }

    // Task 6: top 5 astronauts by risk score
    // Task 6
    public List<RankRow> topN(int n) {
        Map<Integer, Astronaut> byId = astronauts.stream()
                .collect(Collectors.toMap(Astronaut::getId, Function.identity()));

        Map<Integer, Integer> eventSumByAstronaut= events.stream()
                .collect(Collectors.groupingBy(
                        MissionEvent::getAstronautId,
                        Collectors.summingInt(MissionEvent::computedPoints)
                ));

        Map<Integer, Integer> supplySumByAstronaut= supplies.stream()
                .collect(Collectors.groupingBy(
                        Supply::getAstronautId,
                        Collectors.summingInt(Supply::getValue)
                ));

        List<RankRow> rows = new ArrayList<>();
        for (Astronaut a : astronauts) {
            int evSum = eventSumByAstronaut.getOrDefault(a.getId(), 0);
            int gSum = supplySumByAstronaut.getOrDefault(a.getId(), 0);
            rows.add(new RankRow(a.getName(), a.getSpacecraft(), evSum + gSum));
        }

        rows.sort(Comparator.comparingInt(RankRow::totalScore).reversed()
                .thenComparing(RankRow::name));

        return rows.stream().limit(n).toList();
    }

    public record RankRow(String name, String spacecraft, int totalScore) {}





    // Task 7: write report
    public void writeMissionReport(Path out) {
        EnumMap<MissionEventType, Long> counts = events.stream()
                .collect(Collectors.groupingBy(
                        MissionEvent::getType,
                        () -> new EnumMap<>(MissionEventType.class),
                        Collectors.counting()
                ));

        List<String> lines = Arrays.stream(MissionEventType.values())
                .map(t -> t + " -> " + counts.getOrDefault(t, 0L))
                .toList();

        try {
            Files.write(out, lines);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write report " + out + ": " + e.getMessage(), e);
        }
    }
}
