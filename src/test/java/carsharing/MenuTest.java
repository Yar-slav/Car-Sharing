package carsharing;

import carsharing.dao.Connection;
import carsharing.dto.Company;
import carsharing.dto.Customer;
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

    @Test
    @ExpectSystemExit
    void managerMenuDefault() {
        String input = "incorrect\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        menu.managerMenu();
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
        menu.managerMenu();
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
        menu.managerMenu();
    }


    @Test
    @ExpectSystemExit
    void companyMenuDefault() {
        String input = "incorrect\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        menu.companyMenu(5);
    }

    @Test
    @ExpectSystemExit
    void companyMenuManagerCarListEmpty() {
        String input = "1\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        menu.companyMenu(company.getId());
    }

    @Test
    @ExpectSystemExit
    void companyMenuManagerCarList() {
        String input = "1\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);
        carDao.createCar("Car 1", company.getId());
        carDao.createCar("Car 2", company.getId());

        menu.companyMenu(company.getId());
    }

    @Test
    @ExpectSystemExit
    void companyMenuCreateCar() {
        String input = "2\nCar\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        menu.companyMenu(company.getId());
    }

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
        menu.customerMenu(customer.getId());
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
        menu.customerMenu(customer.getId());
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
        menu.customerMenu(customer.getId());
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
        menu.customerMenu(customer.getId());
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

        menu.customerMenu(customer.getId());
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

        menu.customerMenu(customer.getId());
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

        menu.customerMenu(customer.getId());
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
        menu.customerMenu(customer.getId());
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
        menu.customerMenu(customer.getId());
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
