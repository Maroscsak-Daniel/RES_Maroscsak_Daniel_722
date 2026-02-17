package app;

import model.Astronaut;
import model.MissionEvent;
import model.Supply;
import service.SpaceService;
import util.JsonUtil;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path tributesPath = Path.of("C:\\FMI Facultate shit\\UBB FMI Anul II\\Semestrul I\\PRACTIC MAP\\Practic_Maroscsak_Daniel_722\\data\\astronauts.json");
        Path eventsPath = Path.of("C:\\FMI Facultate shit\\UBB FMI Anul II\\Semestrul I\\PRACTIC MAP\\Practic_Maroscsak_Daniel_722\\data\\events.json");
        Path giftsPath = Path.of("C:\\FMI Facultate shit\\UBB FMI Anul II\\Semestrul I\\PRACTIC MAP\\Practic_Maroscsak_Daniel_722\\data\\supplies.json");

        List<Astronaut> astronauts = JsonUtil.readList(tributesPath, Astronaut.class);
        List<MissionEvent> events = JsonUtil.readList(eventsPath, MissionEvent.class);
        List<Supply> supplies = JsonUtil.readList(giftsPath, Supply.class);

        SpaceService service = new SpaceService(astronauts, events, supplies);

        // Task 1: Lesen Sie die Daten aus den JSON-Dateien astronauts.json, events.json und supplies.json und speichern Sie diese in Java-Listen.
        System.out.println("Task 1: Load data + count entities + Astronauts:");
        System.out.println("Astronauts loaded: " + service.getAstronauts().size());
        System.out.println("Events loaded: " + service.getEvents().size());
        System.out.println("Supplies loaded: " + service.getSupplies().size());
        for (Astronaut a : service.getAstronauts()) {
            System.out.println(a);
        }
        System.out.println("Task 1 completed.\n");

        //Task 2: Filtern nach spacecraft und Status.
        System.out.println("Task 2: Filtering on Spacecraft and Status:");
        Scanner sc = new Scanner(System.in);
        System.out.print("Input spacecraft: ");
        String d = sc.nextLine();
        for (Astronaut a : service.activeBySpacecraft(d)) {
            System.out.println(a);
        }
        System.out.println("Task 2 completed.\n");

        // Task 3: Sortieren der Astronauten nach skillLevel (absteigend) und dann nach name (aufsteigend).
        System.out.println("Task 3: Sorting of Astronauts: ");
        List<Astronaut> sorted = service.sortedAstronauts();
        for (Astronaut a : sorted) {
            System.out.println(a);
        }
        System.out.println("Task 3 completed.\n");

        // Task 4: Speichern der sortierten Astronauten (umgekehrt) in einer Textdatei.
        List<Astronaut> sorted2 = service.reverseSortedAstronauts();
        service.writeAstronauts(Path.of("astronauts_sorted.txt"), sorted2);
        System.out.println("Task 4 completed.\n");

    }
}