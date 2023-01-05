package carsharing.dao;

import carsharing.DBConnection;
import carsharing.dao.Implementation.CarDaoImpl;
import carsharing.dao.Implementation.CompanyDaoImpl;
import carsharing.dao.Implementation.CustomerDaoImpl;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Connection {

    protected DBConnection dbConnection = new DBConnection();
    protected CarDaoImpl carDao = new CarDaoImpl(dbConnection);
    protected CompanyDaoImpl companyDao = new CompanyDaoImpl(dbConnection);
    protected CustomerDaoImpl customerDao = new CustomerDaoImpl(dbConnection);

    @BeforeEach
    void init() throws SQLException {
        String sqlDropCustomers= "DELETE FROM customer";
        PreparedStatement psDropCustomers = dbConnection.conn.prepareStatement(sqlDropCustomers);
        psDropCustomers.executeUpdate();

        String sqlDropCars = "DELETE FROM car";
        PreparedStatement psDropCars = dbConnection.conn.prepareStatement(sqlDropCars);
        psDropCars.executeUpdate();

        String sqlDropCompanies = "DELETE FROM company";
        PreparedStatement psDropCompanies = dbConnection.conn.prepareStatement(sqlDropCompanies);
        psDropCompanies.executeUpdate();
    }

    @AfterEach
    void afterVoid() throws SQLException {
        String sqlDropCustomers= "DELETE FROM customer";
        PreparedStatement psDropCustomers = dbConnection.conn.prepareStatement(sqlDropCustomers);
        psDropCustomers.executeUpdate();

        String sqlDropCars = "DELETE FROM car";
        PreparedStatement psDropCars = dbConnection.conn.prepareStatement(sqlDropCars);
        psDropCars.executeUpdate();

        String sqlDropCompanies = "DELETE FROM company";
        PreparedStatement psDropCompanies = dbConnection.conn.prepareStatement(sqlDropCompanies);
        psDropCompanies.executeUpdate();
    }
}
