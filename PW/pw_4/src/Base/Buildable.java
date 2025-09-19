package Base;

import java.time.LocalDate;

public interface Buildable {
    final int lifespan = 25;

    String getAddress();
    String getType();
    int getFloorsCount();
    int getFlatCount();
    int getRepairsPeriod();
    LocalDate getLastRepairDate();
    LocalDate getConstructionDate();

    void setLastRepairDate(LocalDate newLastRepairDate);

    int addFloors(int addedFloors);

    boolean isOutOfLifespan();
    int getRemainingLifespan();

    int untilNextRepair();
    boolean needsRepair();

    void printInfo();
}