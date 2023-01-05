package carsharing.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import carsharing.DBConnection;
import carsharing.dao.Implementation.CustomerDaoImpl;
import carsharing.dto.Car;
import carsharing.dto.Company;
import carsharing.dto.Customer;
import carsharing.model.RentCar;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CustomerDaoImplTest extends Connection {

    @Test
    void createCustomer() {
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);

        assertEquals("Customer", customer.getName());
    }

    @Test
    void addCar() {
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);

        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        carDao.createCar("Car 1", company.getId());
        List<Car> allCars = carDao.getAllCars(company.getId());
        Car car = allCars.get(0);

        boolean result = customerDao.addCar(customer.getId(), company.getId(), car.getId());
        assertTrue(result);
    }

    @Test
    void returnedCar() {
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);

        boolean result = customerDao.returnedCar(customer.getId());
        assertTrue(result);
    }

    @Test
    void getRentedCar() {
        customerDao.createCustomer("Customer");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);

        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        carDao.createCar("Car 1", company.getId());
        List<Car> allCars = carDao.getAllCars(company.getId());
        Car car = allCars.get(0);

        customerDao.addCar(customer.getId(), company.getId(), car.getId());

        RentCar rentCarExcepted = new RentCar(car.getName(), company.getName());
        RentCar rentedCarResult = customerDao.getRentedCar(customer.getId());
        assertEquals(rentCarExcepted.getCarName(), rentedCarResult.getCarName());
        assertEquals(rentCarExcepted.getCompanyName(), rentedCarResult.getCompanyName());
    }

    @Test
    void CustomerDaoImplFail() throws SQLException {
        dbConnection = new DBConnection();
        customerDao = new CustomerDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> new CustomerDaoImpl(dbConnection));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void createCustomerFail() throws SQLException {
        dbConnection = new DBConnection();
        customerDao = new CustomerDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> customerDao.createCustomer("Customer 1"));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void addCarFail() throws SQLException {
        dbConnection = new DBConnection();
        customerDao = new CustomerDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> customerDao.addCar(1, 1, 1));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void returnedCarFail() throws SQLException {
        dbConnection = new DBConnection();
        customerDao = new CustomerDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> customerDao.returnedCar(1));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void getRentedCarFail() throws SQLException {
        dbConnection = new DBConnection();
        customerDao = new CustomerDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> customerDao.getRentedCar(1));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void getAllCustomersFail() throws SQLException {
        dbConnection = new DBConnection();
        customerDao = new CustomerDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> customerDao.getAllCustomers());

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }
}