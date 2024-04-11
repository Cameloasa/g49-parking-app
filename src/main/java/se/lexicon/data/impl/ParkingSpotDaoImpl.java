package se.lexicon.data.impl;

import se.lexicon.data.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private List<ParkingSpot> storage = new ArrayList<>();

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        if(parkingSpot == null) throw new IllegalArgumentException("Parking spot Data is null");
        Optional <ParkingSpot> optionalParkingSpot = find(parkingSpot.getSpotNumber());
        if(optionalParkingSpot.isPresent()) throw new IllegalArgumentException("Parking spot Id is duplicate");
        storage.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public Optional<ParkingSpot> find(int spotNumber) {
        for (ParkingSpot parkingSpot : storage){
            if (parkingSpot.getSpotNumber() == spotNumber)
                return Optional.of(parkingSpot);
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(int spotNumber) {
        Optional<ParkingSpot> optionalParkingSpot = find(spotNumber);
        if (!optionalParkingSpot.isPresent()) return false;
        storage.remove(optionalParkingSpot.get());
        return true;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        List<ParkingSpot> spotsInArea = new ArrayList<>();
        for (ParkingSpot parkingSpot : storage){
            if (parkingSpot.getAreaCode() == areaCode){
                spotsInArea.add(parkingSpot);
            }
        }
        return spotsInArea;
    }

    @Override
    public void occupyParkingSpot(int spotNumber) {
        Optional<ParkingSpot> optionalParkingSpot = find(spotNumber);
        if (optionalParkingSpot.isPresent()){
            ParkingSpot parkingSpot = optionalParkingSpot.get();
            parkingSpot.occupy();
        } else {
            throw new IllegalArgumentException("Parking spot not found");
        }

    }

    @Override
    public void vacateParkingSpot(int spotNumber) {
        Optional<ParkingSpot> optionalParkingSpot = find(spotNumber);
        if (optionalParkingSpot.isPresent()){
            ParkingSpot parkingSpot = optionalParkingSpot.get();
            parkingSpot.vacate();
        } else {
            throw new IllegalArgumentException("Parking spot not found");
        }


    }
}
