package carsharing.dao;

import carsharing.dto.Company;
import carsharing.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    public CompanyDaoImpl() {
        try {
//            String sqlDropCustomers = "DROP TABLE customer IF EXISTS";
//            PreparedStatement psDropCustomers = DBConnection.conn.prepareStatement(sqlDropCustomers);
//            psDropCustomers.executeUpdate();
//
//            String sqlDropCar = "DROP TABLE car IF EXISTS";
//            PreparedStatement psDropCar = DBConnection.conn.prepareStatement(sqlDropCar);
//            psDropCar.executeUpdate();
//
//            String sqlDropCompany = "DROP TABLE company IF EXISTS";
//            PreparedStatement psDropCompany = DBConnection.conn.prepareStatement(sqlDropCompany);
//            psDropCompany.executeUpdate();

            String sql = "CREATE TABLE IF NOT EXISTS company " +
                    "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL UNIQUE) ";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCompany(String name) {
        try {
            String sql = "INSERT INTO company (name) VALUES (?)";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public List<Company> getAllCompany() {
        List<Company> companies = new ArrayList<>();
        try {
            String sql = "SELECT * FROM company";
            PreparedStatement ps = DBConnection.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                companies.add(new Company(rs.getInt("id"), rs.getString("name")));
            }
        } catch (
                SQLException se) {
            se.printStackTrace();
        }
        return companies;
    }
}
