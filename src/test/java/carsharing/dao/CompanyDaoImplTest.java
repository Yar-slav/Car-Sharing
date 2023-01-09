package carsharing.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

import carsharing.DBConnection;
import carsharing.dao.Implementation.CompanyDaoImpl;
import carsharing.dto.Company;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CompanyDaoImplTest extends Connection {

    @Test
    void createCompany_CheckingIfCompanyWasCreated() {
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        assertEquals("Company 1", company.getName());
    }

    @Test
    void companyDaoImpl_Should_ThrowException_When_DBConnectionClose() throws SQLException {
        dbConnection = new DBConnection();
        companyDao = new CompanyDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> new CompanyDaoImpl(dbConnection));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void createCompany_Should_ThrowException_When_DBConnectionClose() throws SQLException {
        dbConnection = new DBConnection();
        companyDao = new CompanyDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> companyDao.createCompany(eq("Company 1")));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void getAllCompany_Should_ThrowException_When_DBConnectionClose() throws SQLException {
        dbConnection = new DBConnection();
        companyDao = new CompanyDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> companyDao.getAllCompany());

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }

    @Test
    void ifCompanyAlreadyExist_ReturnTrueIfCompanyAlreadyExist() {
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        assertTrue(companyDao.ifCompanyAlreadyExist(company.getName()));
    }

    @Test
    void ifCompanyAlreadyExist_ReturnFalseIfCompanyNotExist() {
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");

        assertFalse(companyDao.ifCompanyAlreadyExist("Company 3"));
    }

    @Test
    void ifCompanyAlreadyExist_Should_ThrowException_When_DBConnectionClose() throws SQLException {
        dbConnection = new DBConnection();
        companyDao = new CompanyDaoImpl(dbConnection);
        dbConnection.conn.close();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> companyDao.ifCompanyAlreadyExist("Company"));

        dbConnection = new DBConnection();
        assertEquals("Exception", runtimeException.getMessage());
    }
}
