package Base;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Building[] buildings = new Building[5];

        buildings[0] = new Building(
                "123 Base.Main Street, New York",
                "Office",
                25,
                0,
                15,
                LocalDate.of(2020, 5, 15),
                LocalDate.of(2005, 3, 10)
        );

        buildings[1] = new Building(
                "456 Oak Avenue, London",
                "Residential",
                10,
                40,
                20,
                LocalDate.of(2018, 8, 25),
                LocalDate.of(2000, 12, 1)
        );

        buildings[2] = new Building(
                "789 Mall Road, Paris",
                "Commercial",
                3,
                0,
                10,
                LocalDate.of(2010, 7, 1)
        );

        buildings[3] = new Building(
                "321 Factory Lane, Berlin",
                "Industrial",
                5,
                0,
                8
        );

        buildings[4] = new Building(
                "654 Park Boulevard, Tokyo",
                "Residential",
                15,
                60,
                25,
                LocalDate.of(2022, 3, 10),
                LocalDate.of(1995, 6, 20)
        );

        for (int i = 0; i < buildings.length; i++) {
            buildings[i].printInfo();
            System.out.println();
        }

        System.out.println("Buildings in need of renovation:");

        for (int i = 0; i < buildings.length; i++) {
            if (buildings[i].needsRepair()) {
                System.out.printf("The building is located at %s", buildings[i].getAddress());
            }
        }
    }
}
