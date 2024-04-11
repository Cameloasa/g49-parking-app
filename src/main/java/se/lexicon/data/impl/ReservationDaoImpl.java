package se.lexicon.data.impl;

import se.lexicon.data.ReservationDao;
import se.lexicon.data.sequencer.CustomerSequencer;
import se.lexicon.data.sequencer.ReservationSequencer;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private List<Reservation> storage = new ArrayList<>();
    @Override
    public Reservation create(Reservation reservation) {
        String id = ReservationSequencer.nextId();
        reservation.setId(id);
        // Check if the reservation data is valid
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation data is null");
        }

        // Check if the reservation is a duplicate
        Optional<Reservation> existingReservationOptional = find(reservation.getId()) ;
        if (existingReservationOptional.isPresent()) {
            throw new IllegalArgumentException("Duplicate reservation found for spot number: " + reservation.getParkingSpot().getSpotNumber());
        }

        // Reserve the parking spot and associate it with the customer
        reservation.reserve();

        // Add the reservation to the storage
        storage.add(reservation);

        // Return the created reservation
        return reservation;
    }

    @Override
    public Optional<Reservation> find(String id) {
        for (Reservation reservation : storage){
            if (reservation.getId() == id);
            return Optional.of(reservation);
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(String id) {
        Optional<Reservation> optionalReservation = find(id);
        if (!optionalReservation.isPresent()) return false;
        storage.remove(optionalReservation.get());
        return true;
    }

    @Override
    public Reservation findByCustomerId(int customerId) {
        for (Reservation reservation : storage) {
            if (reservation.getCustomer().getId() == customerId) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public Reservation findByVehicleLicensePlate(String licensePlate) {
        for (Reservation reservation : storage) {
            if (reservation.getAssociatedVehicle().getLicensePlate().equals(licensePlate)) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public Reservation findByParkingSpotNumber(int spotNumber) {
        for (Reservation reservation : storage) {
            if (reservation.getParkingSpot().getSpotNumber() == spotNumber) {
                return reservation;
            }
        }
        return null;
    }


}
