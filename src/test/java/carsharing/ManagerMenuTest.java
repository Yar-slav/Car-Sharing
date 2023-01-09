package carsharing;

import carsharing.dao.Connection;
import carsharing.menu.Menu;
import com.ginsberg.junit.exit.ExpectSystemExit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class ManagerMenuTest extends Connection {

    @Test
    @ExpectSystemExit
    void managerMenuDefault() {
        String input = "incorrect\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        menu.getManagerMenu().managerMenu(menu);
    }

    @Test
    @ExpectSystemExit
    void managerMenuManagerCompanyListEmpty() {
        String input = "1\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        menu.getManagerMenu().managerMenu(menu);
    }

    @Test
    @ExpectSystemExit
    void managerMenuCreateCompany() {
        String input = "2\nCompany\nCompany 1\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company");
        menu.getManagerMenu().managerMenu(menu);
    }

    @Test
    @ExpectSystemExit
    void managerCompanyListBadRequest() {
        String input = "1\n1\nbadRequest\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        menu.mainMenu();
    }

    @Test
    @ExpectSystemExit
    void managerCompany() {
        String input = "1\n1\n2\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");
        companyDao.createCompany("Company 3");
        menu.mainMenu();
    }

    @Test
    @ExpectSystemExit
    void managerCompanyNotExist() {
        String input = "1\n1\n5\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        menu.mainMenu();
    }
}
