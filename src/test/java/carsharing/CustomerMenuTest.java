package carsharing;

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

public class CustomerMenuTest extends Connection {

    @Test
    @ExpectSystemExit
    void customerMenuDefault() {
        String input = "incorrect\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);
        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void customerMenu_RentCar() {
        String input = "1\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);
        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void customerMenu_RentCarBadRequest() {
        String input = "1\nbadRequest\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);
        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void customerMenu_RentCarNotExistCompany() {
        String input = "1\n5\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");
        companyDao.createCompany("Company 3");
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);
        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void customerMenu_RentCar_CustomerCarList_EmptyCarList() {
        String input = "1\n1\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");
        companyDao.createCompany("Company 3");
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);

        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void customerMenu_RentCar_CustomerCarList_BedRequest() {
        String input = "1\n1\nbadRequest\n0\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");
        companyDao.createCompany("Company 3");
        List<Company> allCompany = companyDao.getAllCompany();
        carDao.createCar("Car 1", allCompany.get(0).getId());
        carDao.createCar("Car 2", allCompany.get(0).getId());
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);

        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void customerMenu_RentCar_CustomerCarList_RentCar() {
        String input = "1\n1\n1\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");
        companyDao.createCompany("Company 3");
        List<Company> allCompany = companyDao.getAllCompany();
        carDao.createCar("Car 1", allCompany.get(0).getId());
        carDao.createCar("Car 2", allCompany.get(0).getId());
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);

        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }


    @Test
    @ExpectSystemExit
    void customerMenuReturnedCar() {
        String input = "2\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);
        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void customerMenuMyRentCar() {
        String input = "3\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);
        menu.getCustomerMenu().customerMenu(customer.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void customerListBadRequest() {
        String input = "2\nbadRequest\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        customerDao.createCustomer("Customer 1");
        menu.mainMenu();
    }

    @Test
    @ExpectSystemExit
    void customerList() {
        String input = "2\n2\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        customerDao.createCustomer("Customer 1");
        customerDao.createCustomer("Customer 2");
        customerDao.createCustomer("Customer 3");
        menu.mainMenu();
    }

    @Test
    @ExpectSystemExit
    void customerListCustomerNotExist() {
        String input = "2\n5\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        customerDao.createCustomer("Customer");
        menu.mainMenu();
    }
}
