package carsharing.dao.Implementation;

import carsharing.DBConnection;
import carsharing.dao.CompanyDao;
import carsharing.dto.Company;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    private final DBConnection dbConnection;

    public CompanyDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        try {
//            String sqlDropCustomers = "DROP TABLE customer IF EXISTS";
//            PreparedStatement psDropCustomers = dbConnection.conn.prepareStatement(sqlDropCustomers);
//            psDropCustomers.executeUpdate();
//
//            String sqlDropCar = "DROP TABLE car IF EXISTS";
//            PreparedStatement psDropCar = dbConnection.conn.prepareStatement(sqlDropCar);
//            psDropCar.executeUpdate();
//
//            String sqlDropCompany = "DROP TABLE company IF EXISTS";
//            PreparedStatement psDropCompany = dbConnection.conn.prepareStatement(sqlDropCompany);
//            psDropCompany.executeUpdate();

            String sql = "CREATE TABLE IF NOT EXISTS company " +
                    "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL UNIQUE) ";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Exception");
        }
    }

    @Override
    public boolean createCompany(String name) {
        try {
            String sql = "INSERT INTO company (name) VALUES (?)";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
            return true;
        } catch (SQLException se) {
            throw new RuntimeException("Exception");
        }
    }

    @Override
    public List<Company> getAllCompany() {
        List<Company> companies = new ArrayList<>();
        try {
            String sql = "SELECT * FROM company";
            PreparedStatement ps = dbConnection.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                companies.add(new Company(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException se) {
            throw new RuntimeException("Exception");
        }
        return companies;
    }
}
