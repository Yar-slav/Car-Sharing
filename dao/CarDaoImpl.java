package carsharing.dao;

import carsharing.dto.Car;
import carsharing.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    public CarDaoImpl() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS car " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "company_id INT NOT NULL, " +
                    "CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES company(id))";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createCar(String name, int company_id) {
        try {
            String sql = "INSERT INTO car (name, company_id) VALUES ('"+name+"', '"+company_id+"')";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> getAllCars(int companyId) {
        List<Car> cars = new ArrayList<>();
        try {
            String sql = "SELECT ca.* FROM car ca " +
                    "LEFT JOIN customer cu ON ca.id = cu.rented_car_id " +
                    "WHERE cu.rented_car_id IS NULL AND ca.company_id = '"+companyId+"' ";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
