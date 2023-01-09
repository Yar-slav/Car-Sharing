package carsharing;

import carsharing.DBConnection;
import carsharing.dao.Connection;
import carsharing.dto.Company;
import carsharing.dto.Customer;
import carsharing.menu.Menu;
import com.ginsberg.junit.exit.ExpectSystemExit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class MenuTest extends Connection {

    @Test
    @ExpectSystemExit
    void mainMenuDefault() {
        String input = "incorrect\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        menu.mainMenu();
    }

    @Test
    @ExpectSystemExit
    void mainMenuToManagerMenu() {
        String input = "1\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        menu.mainMenu();
    }

    @Test
    @ExpectSystemExit
    void mainMenuToCustomerList() {
        String input = "2\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        menu.mainMenu();
    }

    @Test
    @ExpectSystemExit
    void mainMenuCreateExistCustomer() {
        String input = "3\nCustomer\nCustomer1\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        customerDao.createCustomer("Customer");
        menu.mainMenu();
    }
}
