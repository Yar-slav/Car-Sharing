package carsharing.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;

import carsharing.DBConnection;
import carsharing.dao.Implementation.CompanyDaoImpl;
import carsharing.dto.Company;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CompanyDaoImplTest extends Connection {

    @Test
    void createCompany() {
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        assertEquals("Company 1", company.getName());
    }

    @Test
    void CompanyDaoImplFail() throws SQLException {
        dbConnection = new DBConnection();
        companyDao = new CompanyDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> new CompanyDaoImpl(dbConnection));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void createCompanyFail() throws SQLException {
        dbConnection = new DBConnection();
        companyDao = new CompanyDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> companyDao.createCompany(eq("Company 1")));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void getAllCompanyFail() throws SQLException {
        dbConnection = new DBConnection();
        companyDao = new CompanyDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> companyDao.getAllCompany());

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

}