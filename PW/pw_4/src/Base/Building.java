package Base;

import java.time.LocalDate;
import java.time.Period;

public class Building implements Buildable {
    private String address;
    private String type;
    private int floorCount;
    private int flatCount;
    private int repairsPeriod;
    private LocalDate lastRepairDate;
    private LocalDate constructionDate;

    public Building(String address,
                    String type,
                    int floorCount,
                    int flatCount,
                    int repairsPeriod,
                    LocalDate lastRepairDate,
                    LocalDate constructionDate) {
        this.address = address;
        this.type = type;
        this.floorCount = floorCount;
        this.flatCount = flatCount;
        this.repairsPeriod = repairsPeriod;
        this.lastRepairDate = lastRepairDate;
        this.constructionDate = constructionDate;
    }

    public Building(String address,
                    String type,
                    int floorCount,
                    int flatCount,
                    int repairsPeriod,
                    LocalDate constructionDate) {
        this(address, type, floorCount, flatCount, repairsPeriod, null, constructionDate);
    }

    public Building(String address,
                    String type,
                    int floorCount,
                    int flatCount,
                    int repairsPeriod) {
        this(address, type, floorCount, flatCount, repairsPeriod, null, LocalDate.now());
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public int getFloorsCount() {
        return this.floorCount;
    }

    @Override
    public int getFlatCount() {
        return this.flatCount;
    }

    @Override
    public LocalDate getLastRepairDate() {
        return this.lastRepairDate;
    }

    @Override
    public LocalDate getConstructionDate() {
        return this.constructionDate;
    }

    @Override
    public int getRepairsPeriod() {
        return this.repairsPeriod;
    }

    @Override
    public void setLastRepairDate(LocalDate newLastRepairDate) {
        this.lastRepairDate = newLastRepairDate;
    }

    @Override
    public int addFloors(int addedFloors) {
        this.floorCount += addedFloors;
        return this.floorCount;
    }

    @Override
    public boolean isOutOfLifespan() {
        LocalDate endDate = this.constructionDate.plusYears(lifespan);
        return LocalDate.now().isAfter(endDate);
    }

    @Override
    public int getRemainingLifespan() {
        LocalDate endDate = this.constructionDate.plusYears(lifespan);
        LocalDate now = LocalDate.now();

        if (now.isAfter(endDate)) {
            return -1;
        }

        return Period.between(now, endDate).getYears();
    }

    public LocalDate getNextRepair() {
        return this.lastRepairDate != null ?
               this.lastRepairDate.plusYears(repairsPeriod) :
               this.constructionDate.plusYears(repairsPeriod);
    }

    @Override
    public int untilNextRepair() {
        LocalDate endDate = getNextRepair();
        LocalDate now = LocalDate.now();

        if (now.isAfter(endDate)) {
            return -1;
        }

        return Period.between(now, endDate).getYears();
    }

    @Override
    public boolean needsRepair() {
        LocalDate endDate = getNextRepair();
        return LocalDate.now().isAfter(endDate);
    }

    @Override
    public void printInfo() {
        System.out.printf("Base.Building address: %s\n" +
                        "Base.Building type: %s\n" +
                        "Count of floors: %d\n" +
                        "Count of flats: %d\n" +
                        "Period before major repairs: %d years\n" +
                        "Last repair date: %s\n" +
                        "Construction date: %s\n",
                        this.address,
                        this.type,
                        this.floorCount,
                        this.flatCount,
                        this.repairsPeriod,
                        this.lastRepairDate != null ? lastRepairDate.toString() : "Never repaired",
                        this.constructionDate.toString());
    }
}