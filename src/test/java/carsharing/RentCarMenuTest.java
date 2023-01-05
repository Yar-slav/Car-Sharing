package carsharing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;

import carsharing.dao.CustomerDao;
import carsharing.model.RentCar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RentCarMenuTest {

    @Mock
    private Menu menu;

    @Mock
    private CustomerDao customerDao;

    @Test
    void isCarRented() {
        RentCarMenu rentCarMenu = new RentCarMenu(customerDao, menu);
        RentCar rentCar = new RentCar("Car", "Company 1");

        doNothing().when(menu).customerMenu(1);

        boolean result = rentCarMenu.isCarRented(rentCar, 1);
        assertTrue(result);
    }

    @Test
    void isCarNotRented() {
        RentCarMenu rentCarMenu = new RentCarMenu(customerDao, menu);

        boolean result = rentCarMenu.isCarRented(null, 1);
        assertFalse(result);
    }

    @Test
    void isCarReturned() {
        RentCarMenu rentCarMenu = new RentCarMenu(customerDao, menu);
        RentCar rentCar = new RentCar("Car", "Company 1");

        doNothing().when(menu).customerMenu(1);

        boolean result = rentCarMenu.isCarReturned(rentCar, 1);
        assertTrue(result);
    }

    @Test
    void isCarNotReturned() {
        RentCarMenu rentCarMenu = new RentCarMenu(customerDao, menu);

        boolean result = rentCarMenu.isCarReturned(null, 1);
        assertFalse(result);
    }

    @Test
    void showMyRentedCar() {
        RentCarMenu rentCarMenu = new RentCarMenu(customerDao, menu);
        RentCar rentCar = new RentCar("Car", "Company 1");

        doNothing().when(menu).customerMenu(1);

        boolean result = rentCarMenu.showMyRentedCar(rentCar, 1);
        assertTrue(result);
    }

    @Test
    void showMyRentedCarFalse() {
        RentCarMenu rentCarMenu = new RentCarMenu(customerDao, menu);

        boolean result = rentCarMenu.showMyRentedCar(null, 1);
        assertFalse(result);
    }
}