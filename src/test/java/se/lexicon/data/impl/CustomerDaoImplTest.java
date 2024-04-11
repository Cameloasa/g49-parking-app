package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Customer;
import se.lexicon.model.Vehicle;
import se.lexicon.model.VehicleType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDaoImplTest {

    private CustomerDaoImpl testObject;

    @BeforeEach
    public void setUp() {
        testObject = new CustomerDaoImpl();
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer(1001,"John Doe","123456");

        Customer actualValue = testObject.create(customer);
        Customer expectedValue = customer;

        assertEquals(expectedValue, actualValue);
        assertTrue(testObject.find(1001).isPresent());
    }

    @Test
    public void findById() {
        Customer customer = new Customer(1001, "John Doe", "123456");
        testObject.create(customer);

        Optional<Customer> actualValue = testObject.find(1001);
        assertTrue(actualValue.isPresent());
        assertEquals(customer, actualValue.get());

    }

    @Test
    public void testNonExistentCustomer() {
        assertFalse(testObject.find(9999).isPresent());

    }

    @Test
    public void testRemoveCustomer() {
        Customer customer = new Customer(1001, "John Doe", "123456");
        testObject.create(customer);

        boolean actualResult = testObject.remove(1001);
        assertTrue(actualResult);
        assertFalse(testObject.find(1001).isPresent());

    }

    @Test
    public void testRemoveNonExistentCustomer() {
        boolean actualResult = testObject.remove(9999);

        assertTrue(true);
        assertFalse(testObject.find(9999).isPresent());


    }

    @Test
    public void testFindAllCustomers() {
        Customer customer1 = new Customer(1001, "John Doe", "123456");
        Customer customer2 = new Customer(1002, "Jane Smith", "654321");
        testObject.create(customer1);
        testObject.create(customer2);

        List<Customer> actualCustomers = testObject.findAll();
        List<Customer> expectedCustomers = Arrays.asList(customer1, customer2);

        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    public void testFindAllCustomersEmptyList() {
        List<Customer> actualCustomers = testObject.findAll();
        assertTrue(actualCustomers.isEmpty());

    }

}
