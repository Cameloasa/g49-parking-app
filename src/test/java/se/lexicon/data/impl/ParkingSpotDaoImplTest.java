package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotDaoImplTest {

    private ParkingSpotDaoImpl testObject;
    @BeforeEach
    public void setUp() {
        testObject = new ParkingSpotDaoImpl();
    }

    @Test
    public void testCreateParkingSpot(){

        ParkingSpot parkingSpot = new ParkingSpot(1,100);

        ParkingSpot actualValue = testObject.create(parkingSpot);
        ParkingSpot expectedValue = parkingSpot;

        assertEquals(expectedValue, actualValue);
        assertTrue(testObject.find(1).isPresent());

    }

    @Test
    public void testFindParkingSpotByAreaCode(){
        // Create some parking spots
        ParkingSpot spot1 = new ParkingSpot(1,100);
        ParkingSpot spot2 = new ParkingSpot(2, 200);
        ParkingSpot spot3 = new ParkingSpot(3, 100);
        testObject.create(spot1);
        testObject.create(spot2);
        testObject.create(spot3);
        // Invoke findByAreaCode with area code 100
        List<ParkingSpot> spotsWithAreaCode100 = testObject.findByAreaCode(100);
        assertEquals(2, spotsWithAreaCode100.size());
        assertTrue(spotsWithAreaCode100.contains(spot1));
        assertTrue(spotsWithAreaCode100.contains(spot3));

    }

    @Test
    public void testFindParkingSpot(){
        ParkingSpot spot1 = new ParkingSpot(1,100);
        ParkingSpot spot2 = new ParkingSpot(2, 200);
        ParkingSpot spot3 = new ParkingSpot(3, 100);
        testObject.create(spot1);
        testObject.create(spot2);
        testObject.create(spot3);

        Optional<ParkingSpot> foundSpot1 = testObject.find(1);
        Optional<ParkingSpot> foundSpot2 = testObject.find(2);
        Optional<ParkingSpot> foundSpot3 = testObject.find(3);

        assertTrue(foundSpot1.isPresent());
        assertEquals(spot1, foundSpot1.get());

        assertTrue(foundSpot2.isPresent());
        assertEquals(spot2, foundSpot2.get());

        assertTrue(foundSpot3.isPresent());
        assertEquals(spot3, foundSpot3.get());


    }

    @Test
    public void testFindAll(){
        ParkingSpot spot1 = new ParkingSpot(1,100);
        ParkingSpot spot2 = new ParkingSpot(2, 200);
        testObject.create(spot1);
        testObject.create(spot2);

        List<ParkingSpot> actualParkingSpots = testObject.findAll();
        List<ParkingSpot> expectedParkingSpots = Arrays.asList(spot1, spot2);

        assertEquals(actualParkingSpots,expectedParkingSpots);
    }

    @Test
    public void testRemoveExistingParkingSpot(){

        ParkingSpot parkingSpot = new ParkingSpot(1,100);
        testObject.create(parkingSpot);

        boolean actualResult = testObject.remove(1);

        assertTrue(true);
        assertFalse(testObject.find(1).isPresent());

    }

    @Test
    public void testOccupyParkingSpot() {

        ParkingSpot parkingSpot = new ParkingSpot(1,100);
        testObject.create(parkingSpot);
        testObject.occupyParkingSpot(1);

        Optional<ParkingSpot> foundParkingSpotOptional = testObject.find(1);

        assertTrue(foundParkingSpotOptional.isPresent());
        assertTrue(foundParkingSpotOptional.get().isOccupied());



    }

    @Test
    public void testVacateParkingSpot(){
        ParkingSpot parkingSpot = new ParkingSpot(1,100);
        testObject.create(parkingSpot);
        testObject.occupyParkingSpot(1);
        testObject.vacateParkingSpot(1);

        Optional<ParkingSpot> foundParkingSpotOptional = testObject.find(1);
        assertTrue(foundParkingSpotOptional.isPresent());
        assertFalse(foundParkingSpotOptional.get().isOccupied());


    }




}
