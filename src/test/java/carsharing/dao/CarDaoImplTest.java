package carsharing.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

import carsharing.DBConnection;
import carsharing.dao.Implementation.CarDaoImpl;
import carsharing.dto.Car;
import carsharing.dto.Company;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CarDaoImplTest extends Connection{

    @Test
    void createCar() {
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        boolean car1 = carDao.createCar("Car 1", company.getId());
        boolean car2 = carDao.createCar("Car 2", company.getId());
        List<Car> allCars = carDao.getAllCars(company.getId());

        assertTrue(car1);
        assertTrue(car2);
        assertEquals("Car 1", allCars.get(0).getName());
        assertEquals("Car 2", allCars.get(1).getName());
    }

    @Test
    void createCarFail() throws SQLException {
        dbConnection = new DBConnection();
        carDao = new CarDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> carDao.createCar("Car 1",1));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void getAllCarsFail() throws SQLException {
        dbConnection = new DBConnection();
        carDao = new CarDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> carDao.getAllCars(1));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void CarDaoImplFail() throws SQLException {
        dbConnection = new DBConnection();
        carDao = new CarDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> new CarDaoImpl(dbConnection));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

}