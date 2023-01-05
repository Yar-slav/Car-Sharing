package carsharing.dao.Implementation;

import carsharing.DBConnection;
import carsharing.dao.CustomerDao;
import carsharing.dto.Customer;
import carsharing.model.RentCar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private final DBConnection dbConnection;

    public CustomerDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        try {
            String sql = "CREATE TABLE IF NOT EXISTS customer " +
                    "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL UNIQUE, " +
                    "rented_car_id INT, " +
                    "CONSTRAINT fk_rented_car FOREIGN KEY (rented_car_id) REFERENCES car(id))";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Exception");
        }
    }


    @Override
    public void createCustomer(String name) {
        try {
            String sql = "INSERT INTO customer (name) VALUES ('" + name + "')";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("Exception");
        }
    }


    @Override
    public boolean addCar(int customerId, int companyId, int carId) {
        try {
            String sql = "UPDATE customer SET rented_car_id = " + carId + " " +
                    "WHERE id = " + customerId + "";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException se) {
            throw new RuntimeException("Exception");
        }
    }

    @Override
    public boolean returnedCar(int customerId) {
        try {
            String sql = "UPDATE customer SET rented_car_id = null " +
                    "WHERE id = " + customerId + "";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException se) {
            throw new RuntimeException("Exception");
        }
    }

    @Override
    public RentCar getRentedCar(int customerId) {
        RentCar rentCar = new RentCar();
        try {
            String sql = "SELECT ca.name AS name, cu.id AS id, co.name AS companyName FROM customer cu " +
                    "LEFT JOIN car ca ON ca.id = cu.rented_car_id " +
                    "LEFT JOIN company co ON co.id = ca.company_id " +
                    "WHERE cu.id = " + customerId + "";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (
                    rs.next() &&
                            rs.getString("name") != null &&
                            rs.getString("companyName") != null
            ) {
                rentCar.setCarName(rs.getString("name"));
                rentCar.setCompanyName(rs.getString("companyName"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception");
        }
        return rentCar;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM customer";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id"), rs.getString("name")));
            }
        } catch (
                SQLException se) {
            throw new RuntimeException("Exception");
        }
        return customers;
    }
}
