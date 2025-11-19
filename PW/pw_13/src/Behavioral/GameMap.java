package Behavioral;

import java.util.*;

public class GameMap {
    private String currentLocation;
    private Set<String> discoveredLocations;
    private Map<String, List<String>> locationConnections;

    public GameMap() {
        this.currentLocation = "Начальная деревня";
        this.discoveredLocations = new HashSet<>();
        this.locationConnections = new HashMap<>();
        initializeMap();
    }

    // В класс GameMap добавьте:
    public List<String> getLocationConnections() {
        return locationConnections.get(currentLocation);
    }

    private void initializeMap() {
        discoveredLocations.add(currentLocation);

        locationConnections.put("Начальная деревня", Arrays.asList("Лес", "Горный перевал"));
        locationConnections.put("Лес", Arrays.asList("Начальная деревня", "Заброшенный замок"));
        locationConnections.put("Горный перевал", Arrays.asList("Начальная деревня", "Пещера дракона"));
        locationConnections.put("Заброшенный замок", Arrays.asList("Лес", "Тронный зал"));
        locationConnections.put("Пещера дракона", Arrays.asList("Горный перевал"));
        locationConnections.put("Тронный зал", Arrays.asList("Заброшенный замок"));
    }

    public boolean moveTo(String location) {
        if (locationConnections.get(currentLocation).contains(location)) {
            currentLocation = location;
            discoveredLocations.add(location);
            System.out.println("Игрок переместился в: " + location);
            return true;
        } else {
            System.out.println("Нельзя переместиться в " + location + " отсюда");
            return false;
        }
    }

    public void showMap() {
        System.out.println("\nКАРТА МИРА");
        System.out.println("Текущее местоположение: " + currentLocation);
        System.out.println("Открытые локации:");
        discoveredLocations.forEach(loc -> {
            System.out.println((loc.equals(currentLocation) ? "* " : "  ") + loc);
        });

        System.out.println("\nДоступные направления:");
        locationConnections.get(currentLocation).forEach(conn ->
                System.out.println("→ " + conn)
        );
        System.out.println("\n");
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public Set<String> getDiscoveredLocations() {
        return Collections.unmodifiableSet(discoveredLocations);
    }

    // В класс GameMap добавьте:
    public int getDiscoveredLocationsCount() {
        return discoveredLocations.size();
    }
}