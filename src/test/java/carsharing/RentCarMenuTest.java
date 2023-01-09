package carsharing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import carsharing.RentCar;
import carsharing.dao.Connection;
import carsharing.dao.CustomerDao;
import carsharing.menu.CustomerMenu;
import carsharing.menu.Menu;
import carsharing.menu.RentCarMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RentCarMenuTest extends Connection {

    @Mock
    private Menu menu;
    @Mock
    private CustomerMenu customerMenu;
    @Mock
    private CustomerDao customerDao;

    @Test
    void isCarRented_IfCarRented_True() {
        RentCarMenu rentCarMenu = new RentCarMenu();
        RentCar rentCar = new RentCar("Car", "Company 1");

        when(menu.getCustomerMenu()).thenReturn(customerMenu);
        doNothing().when(customerMenu).customerMenu(1, menu);

        boolean result = rentCarMenu.isCarRented(rentCar, 1, menu);
        assertTrue(result);
    }

    @Test
    void isCarRented_IfCarNotRented_False() {
        RentCarMenu rentCarMenu = new RentCarMenu();
        boolean result = rentCarMenu.isCarRented(null, 1, menu);
        assertFalse(result);
    }

    @Test
    void isCarReturned_IfCarReturned_True() {
        RentCarMenu rentCarMenu = new RentCarMenu();
        RentCar rentCar = new RentCar("Car", "Company 1");

        when(menu.getCustomerDao()).thenReturn(customerDao);
        when(menu.getCustomerMenu()).thenReturn(customerMenu);
        doNothing().when(customerMenu).customerMenu(1, menu);

        boolean result = rentCarMenu.isCarReturned(rentCar, 1, menu);
        assertTrue(result);
    }

    @Test
    void isCarReturned_IfCarNotReturned_False() {
        RentCarMenu rentCarMenu = new RentCarMenu();
        boolean result = rentCarMenu.isCarReturned(null, 1, menu);
        assertFalse(result);
    }

    @Test
    void showMyRentedCar_IfIRentedCar_True() {
        RentCarMenu rentCarMenu = new RentCarMenu();
        RentCar rentCar = new RentCar("Car", "Company 1");

        when(menu.getCustomerMenu()).thenReturn(customerMenu);
        doNothing().when(customerMenu).customerMenu(1, menu);

        boolean result = rentCarMenu.showMyRentedCar(rentCar, 1, menu);
        assertTrue(result);
    }

    @Test
    void showMyRentedCar_IfINotRentedCar_False() {
        RentCarMenu rentCarMenu = new RentCarMenu();

        boolean result = rentCarMenu.showMyRentedCar(null, 1, menu);
        assertFalse(result);
    }
}