package se.lexicon.model;

public class ParkingSpot {
    private int spotNumber;
    private boolean occupied;
    private int areaCode;

    public ParkingSpot(int spotNumber, int areaCode) {
        this.spotNumber = spotNumber;
        this.areaCode = areaCode;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void occupy() {
        occupied = true;
    }

    public void vacate() {
        occupied = false;
    }

    // todo: add getDescription method with using StringBuilder
    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber=" + spotNumber +
                ", occupied=" + occupied +
                ", areaCode=" + areaCode +
                '}';
    }

}
